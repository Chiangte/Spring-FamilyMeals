package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Awan
 * @Description //TODO 
 * @Date 11:30 2018/11/21
 **/
@SpringBootApplication
@MapperScan(value = "com.example.dao")	// mapper接口 扫描包配置
public class DemoApplication {
	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		springApplication.setBannerMode(Banner.Mode.CONSOLE);
		springApplication.run(args);

	}

	/**
	 *  欢迎页面
	 * @return
	 */
	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
