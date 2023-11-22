package com.oneteam.dormease;

//import com.oneteam.dormease.s3uploader.S3UploaderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
@SpringBootApplication
public class DormEaseApplication {

//	public static final String APPLICATION_LOCATIONS = 	"spring.config.location=" +
//														"classpath:application.yml," +
//														"classpath:aws.yml";

	public static void main(String[] args) {
		SpringApplication.run(DormEaseApplication.class, args);
	}
//        new SpringApplicationBuilder(S3UploaderService .class)
//                .properties(APPLICATION_LOCATIONS)
//                .run(args);
}
