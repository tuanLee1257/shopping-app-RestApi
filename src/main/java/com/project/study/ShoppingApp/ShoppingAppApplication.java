package com.project.study.ShoppingApp;

import com.project.study.ShoppingApp.models.user.Role;
import com.project.study.ShoppingApp.models.user.User;
import com.project.study.ShoppingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShoppingAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUsername("will");
//		user.setPassword(passwordEncoder.encode("1234"));
//		user.setRole(Role.USER);
//		userRepository.save(user);
//		System.out.println(user);
	}
}
