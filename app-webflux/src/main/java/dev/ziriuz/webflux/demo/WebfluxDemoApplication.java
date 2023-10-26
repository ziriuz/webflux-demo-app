package dev.ziriuz.webflux.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxDemoApplication {

	public static void main(String[] args) {
		/*
		 To increase number of threads used by Event loop: System.setProperty("reactor.netty.ioWorkerCount", "48");
		 property can also be set as JVM parameter: -Dreactor.netty.ioWorkerCount=48
		 By default number of workers equals number of available CPU
		 It is not recommended to change this value
		 */
		SpringApplication.run(WebfluxDemoApplication.class, args);
	}

}
