package com.koushik.audiogpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class AudiogptApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata")); // workaround for timezone problem in spring boot
		SpringApplication.run(AudiogptApplication.class, args);
	}

}
