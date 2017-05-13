package edu.uestc.msstudio.panorama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.annotations.ApiIgnore;

@SpringBootApplication
@ApiIgnore
public class PanoramaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanoramaApplication.class, args);
	}
}
