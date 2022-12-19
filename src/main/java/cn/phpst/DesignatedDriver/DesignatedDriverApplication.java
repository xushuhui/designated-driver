package cn.phpst.DesignatedDriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class DesignatedDriverApplication {

	public static void main(String[] args) {
        System.out.println("Hello designated driver 111");

		SpringApplication.run(DesignatedDriverApplication.class, args);
	}

}
