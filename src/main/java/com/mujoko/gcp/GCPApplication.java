package com.mujoko.gcp;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GCPApplication  implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
    private ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(GCPApplication.class, args);
	}
	

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class, 8080);
            System.out.printf("%s:%d", ip, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}