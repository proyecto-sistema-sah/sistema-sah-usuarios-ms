package com.sistema.sah.usuarios;

import com.sistema.sah.seguridad.helper.configuration.SpringSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(
		basePackages = "com.sistema.sah"
)
@Import(SpringSecurityConfig.class)
@EnableScheduling
public class UsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}


}
