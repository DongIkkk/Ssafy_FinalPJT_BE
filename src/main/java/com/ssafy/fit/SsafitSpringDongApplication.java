package com.ssafy.fit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ssafy.fit.model.dao")
public class SsafitSpringDongApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsafitSpringDongApplication.class, args);
	}

}
