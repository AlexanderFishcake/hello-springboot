package com.kh.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @SpringBootConfiguration
 * 		springboot 관련설정
 * @EnableAutoConfiguration
 * 		application-context를 관리(context가 단 한개)
 * @ComponentScan : 현재 실행클래스가 속한 base-package 기준으로 annotation 활성화
 *
 */
@SpringBootApplication
public class HelloSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringbootApplication.class, args);
	}

}
