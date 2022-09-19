package io.ex.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.ex.controller"})
public class MainApplication {

	public static void main(String[] args) {

		BitSpringApplication.run(MainApplication.class, args);
	}

}