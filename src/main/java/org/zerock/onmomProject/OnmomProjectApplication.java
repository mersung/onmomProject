package org.zerock.onmomProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class OnmomProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnmomProjectApplication.class, args);
	}

}
