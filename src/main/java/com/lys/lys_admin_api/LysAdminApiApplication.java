package com.lys.lys_admin_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lys.lys_admin_api.module.sys.mapper")

public class LysAdminApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LysAdminApiApplication.class, args);
	}

}
