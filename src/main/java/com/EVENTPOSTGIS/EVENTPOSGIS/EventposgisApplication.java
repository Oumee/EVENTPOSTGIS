package com.EVENTPOSTGIS.EVENTPOSGIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventposgisApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventposgisApplication.class, args);
	}

}
