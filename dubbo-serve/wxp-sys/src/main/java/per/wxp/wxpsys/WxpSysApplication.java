package per.wxp.wxpsys;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class WxpSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxpSysApplication.class, args);
	}

}
