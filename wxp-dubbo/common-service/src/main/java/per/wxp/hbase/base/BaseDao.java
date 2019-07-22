package per.wxp.hbase.base;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import per.wxp.hbase.constant.Names;
import per.wxp.hbase.constant.ValueConstant;
import per.wxp.hbase.retention.Column;
import per.wxp.hbase.retention.RowKey;
import per.wxp.hbase.retention.TableRef;
import per.wxp.hbase.utils.DateUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
/**
 * hbase
 */
public  abstract class BaseDao {

    private ThreadLocal<Connection> connHolder= new ThreadLocal<Connection>();
    private ThreadLocal<Admin> adminHolder= new ThreadLocal<Admin>();

    protected void start() throws  Exception{
        getConnection();
        getAdmin();
    }
    protected void end() throws Exception{
        Admin admin=getAdmin();
        if(admin!=null){
            admin.close();;
            adminHolder.remove();
        }
        Connection conn= getConnection();
        if(conn!=null){
            conn.close();
            connHolder.remove();
        }
    }

    protected synchronized Admin getAdmin() throws IOException {
        Admin admin= adminHolder.get();
        if(admin==null){
            admin=getConnection().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }
    protected synchronized Connection getConnection() throws IOException {
        Connection conn= connHolder.get();
        if(conn==null){
            Configuration conf= HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", "localhost");
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conn=ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }


    /**
     * 创建命名空间
     */
    protected void createNamespaceNS(String namespace) throws IOException {
        Admin admin=getAdmin();
        try {
            admin.getNamespaceDescriptor(namespace);
        }catch (NamespaceNotFoundException e){
//            e.printStackTrace();
            NamespaceDescriptor namespaceDescriptor=
                    NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor);
        }
    }

    /**
     * 创建表
     * 如果存在，先删除，后新建
     * @param name
     * @param family
     */
    protected void createTableXX(String name , String... family) throws Exception {
        createTableXX(name,null,null,family);
    }

    protected void createTableXX(String name ,String corProcessorClass , Integer regionCount,String... family) throws Exception {
        Admin admin=getAdmin();
        TableName tableName=TableName.valueOf(name);
        if(admin.tableExists(tableName)){
            deleteTable(name);
        }
        //创建表
        createTable(name,corProcessorClass,regionCount,family);
    }

    private void createTable(String name,String corProcessorClass ,Integer regionCount,String... family) throws Exception {
        Admin admin=getAdmin();
        TableName tableName=TableName.valueOf(name );
        TableDescriptorBuilder tableDescriptorBuilder=TableDescriptorBuilder.newBuilder(tableName);

        //列族描述器构造器
        ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder ;
        //获得列描述器
        ColumnFamilyDescriptor columnFamilyDescriptor;
        if(family ==null || family.length==0 ){
            family=new  String[1];
            family[0]= Names.CF_INFO.getValue();
        }

        for ( String columnFamily:family)  {
            columnFamilyDescriptorBuilder =
                    ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily));
            columnFamilyDescriptor=columnFamilyDescriptorBuilder.build();
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
        }
        // 增加协处理器
        if(null!=corProcessorClass&&!"".equals(corProcessorClass)){
            tableDescriptorBuilder.setCoprocessor(corProcessorClass);
        }

        TableDescriptor tableDescriptor=tableDescriptorBuilder.build();

        // 增加预分区
        if(null==regionCount || regionCount<=1){
            admin.createTable(tableDescriptor);
        }else{
            // 分区键
            byte[][] splitKeys=genSplitKeys(regionCount);
            admin.createTable(tableDescriptor,splitKeys);
        }

    }

    /**
     * 获取查询时候 starrow ,stoprow 集合
     * @return
     */
    protected List<String[]> getStartRowKeys(String tel,String start,String end){

        List<String[]> rowKeyss=new ArrayList<String[]>();
        String startTime=start.substring(0,6);
        String endTime=end.substring(0,6);

        Calendar startCal=Calendar.getInstance();
        startCal.setTime(DateUtil.parse(startTime,"yyyyMM"));

        Calendar endCal=Calendar.getInstance();
        endCal.setTime(DateUtil.parse(endTime,"yyyyMM"));

        while (startCal.getTimeInMillis()<=endCal.getTimeInMillis()){
            //当前时间
            String nowTime= DateUtil.format(startCal.getTime(),"yyyyMM");
            int regionNum=genRegionNumber(tel,nowTime);
            // 1_133_201803 1_133_201805
            String startRow=regionNum+"_"+tel+"_"+nowTime;
            String stopRow=startRow+"|";
            String[] rowkeys= {startRow,stopRow};
            rowKeyss.add(rowkeys);
            // 月份加 1
            startCal.add(Calendar.MONTH,1);
        }
        return rowKeyss;
    }
    /**
     * 计算分区号
     * @param tel
     * @param date
     * @return
     */
    protected int genRegionNumber(String tel,String date){
        // 1330123 4567 取后4位
        String userCode=tel.substring(tel.length()-4);

        // 20190210  -- 201902
        String yearMonth= date.substring(0,6);

        int userCodeHash=userCode.hashCode();
        int yearMonthHash=yearMonth.hashCode();

        //crc 校验采用 异或算法
        int crc =Math.abs( userCodeHash^yearMonthHash);

        // 取模
        int regionNum=crc%ValueConstant.REGION_COUNT;
        return regionNum;
    }
    /**
     * 生成分区键
     * @param regionCount
     * @return
     */
    private byte[][] genSplitKeys(Integer regionCount){
        int splitKeyCount=regionCount-1;
        byte[][] bs= new byte[splitKeyCount][];
        //分区键 0 |，1|，2|，3|，4|...
        //[-oo ,0|) [0|,1|),[1|,+oo]
        List<byte[]> bsList=new ArrayList<byte[]>();
        for (int i = 0; i <bsList.size() ; i++) {
            String splitKey= i+"|";
            bsList.add(Bytes.toBytes(splitKey));
        }
//        排序
//        Collections.sort(bsList,new Bytes.ByteArrayComparator());
        bsList.toArray();
        return bs;
    }

    protected void putData(String tableName,Put put) throws IOException {
        //获取表对象
        Connection conn=getConnection();
        Table table= conn.getTable(TableName.valueOf(tableName));
        //增加数据
        table.put(put);
        // 关闭表
        table.close();
    }

    protected void putData(String tableName,List<Put> puts) throws IOException {
        //获取表对象
        Connection conn=getConnection();
        Table table= conn.getTable(TableName.valueOf(tableName));
        //增加数据
        table.put(puts);
        // 关闭表
        table.close();

    }

    /**
     * 增加对象、自动封装对象数据
     * @param obj
     */
    protected void putData(Object obj) throws Exception {
        //反射
        Class clazz=obj.getClass();
        TableRef tableRef=(TableRef)clazz.getAnnotation(TableRef.class);
        String tableName=tableRef.value();
        String stringrowKey="";
        Field[] fd= clazz.getDeclaredFields();
        for (Field f:fd) {
            RowKey rowKey= f.getAnnotation(RowKey.class);
            if(rowKey!=null){
                f.setAccessible(true);
                stringrowKey=(String)f.get(obj);
                break;
            }
        }
        //table
        Connection conn=getConnection();
        Table table= conn.getTable(TableName.valueOf(tableName));
        //put
        Put put=new Put(Bytes.toBytes(stringrowKey));
        for (Field f:fd) {
            Column column= f.getAnnotation(Column.class);
            f.setAccessible(true);
            if(column !=null){
                String family=column.family();
                String colName=column.column();
                if(colName==null || "".equals(colName)){
                    colName=f.getName();
                }
                String value=(String )f.get(obj);
                put.addColumn(Bytes.toBytes(family),Bytes.toBytes(colName),Bytes.toBytes(value));
            }
        }
        //增加数据
        table.put(put);
        // 关闭表
        table.close();
    }

    protected void deleteTable (String name ) throws Exception{
        Admin admin=getAdmin();
        TableName tableName=TableName.valueOf(name );
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }


}
