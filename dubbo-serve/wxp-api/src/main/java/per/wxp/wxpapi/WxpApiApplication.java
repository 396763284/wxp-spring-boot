package per.wxp.wxpapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@SpringBootApplication
@EnableDubbo
public class WxpApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxpApiApplication.class, args);
	}

}
