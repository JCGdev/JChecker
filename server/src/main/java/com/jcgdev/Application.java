package com.jcgdev.server;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	private static SpringApplication apiRest = new SpringApplication(Application.class);
	
	@Autowired
	private static ApplicationContext appContext;
	
	@Autowired
	private static ServerProperties serverProperties;
	
	
	public static void start(String args[]) {
		apiRest.run(args);
	}
	
	public static void start(String args[], int port) {
		apiRest.setDefaultProperties(Collections.singletonMap("server.port",
				  					 Integer.toString(port)));
		apiRest.run(args);
	}
	
	
	public static void stop(int code) {
		
		ExitCodeGenerator exitCode = new ExitCodeGenerator() {
			@Override
		    public int getExitCode() {
		        return code;
		    }
		};
		
		apiRest.exit(appContext, exitCode);
		
	}
		
	
	public static SpringApplication getSpringApplication() {
		return apiRest;
	}
	
	public static ServerProperties getServerProperties() {
		return serverProperties;
	}
	
}
