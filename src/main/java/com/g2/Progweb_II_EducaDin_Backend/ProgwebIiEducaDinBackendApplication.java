package com.g2.Progweb_II_EducaDin_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication(
		scanBasePackages = {
				"com.g2.Progweb_II_EducaDin_Backend*",
				//Para funcionamento da Arquitetura
				"br.ueg.progweb2.arquitetura.*"}
)
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class },
		basePackages = {
				"com.g2.Progweb_II_EducaDin_Backend*",
				//Para funcionamento da Arquitetura
				"br.ueg.progweb2.arquitetura.*"}
)
public class ProgwebIiEducaDinBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgwebIiEducaDinBackendApplication.class, args);
	}

}
