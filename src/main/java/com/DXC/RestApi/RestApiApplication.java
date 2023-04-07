package com.DXC.RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@ComponentScan("com.DXC.RestApi.*")
//@EntityScan("com.DXC.RestApi.Entity")
// @EnableJpaRepositories("com.Dxc.RestApi.Repository")
@SpringBootApplication
 @EnableSwagger2
public class RestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
