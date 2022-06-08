package com.capstone.bhs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class BhsApplication {

	private static final Logger log = LoggerFactory.getLogger(BhsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BhsApplication.class, args);
		log.info("\n----------------------------------------------------------	\n\t"
				+ "Application BHS is running! 								  	\n\t"
				+ "Access URL: http://localhost:8080/ 			  				\n\t"
				+ "Swagger UI: http://localhost:8080/swagger-ui/ 				\n\t"
				+ "\n----------------------------------------------------------");
	}

//	@Bean
//	public TaskScheduler taskScheduler() {
//		final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//		scheduler.setPoolSize(10);
//		return scheduler;
//	}
}
