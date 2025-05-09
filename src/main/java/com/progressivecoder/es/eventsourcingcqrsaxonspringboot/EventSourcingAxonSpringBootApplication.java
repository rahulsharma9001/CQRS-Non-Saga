package com.progressivecoder.es.eventsourcingcqrsaxonspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.progressivecoder.es.eventsourcingcqrsaxonspringboot"})
//@EntityScan(basePackages = {"com.progressivecoder.es.eventsourcingcqrsaxonspringboot.entities"})
public class EventSourcingAxonSpringBootApplication {

//	static {
//		// Suppress AxonIQ Console message
//		System.setProperty("disable-axoniq-console-message", "true");
//	}
	public static void main(String[] args) {
		SpringApplication.run(EventSourcingAxonSpringBootApplication.class, args);
	}

}

