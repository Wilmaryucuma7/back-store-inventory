package com.example;

import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRespository userRespository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			UserEntity userEntity = UserEntity.builder()
			.email("wil@gmail.com")
					.username("wil")
					.password(passwordEncoder.encode("1234"))
					.roles(new HashSet<>(Collections.singletonList(RoleEntity.builder().name(ERole.valueOf(ERole.ADMIN.name())).build()
                    ))).build();

			userRespository.save(userEntity);
		};
	}

}
