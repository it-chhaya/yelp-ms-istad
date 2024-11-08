package kh.edu.cstad.idenity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IdentityMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityMsApplication.class, args);
	}

}
