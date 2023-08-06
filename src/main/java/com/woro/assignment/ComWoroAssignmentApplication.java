package com.woro.assignment;

import com.woro.assignment.entity.User;
import com.woro.assignment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;

@SpringBootApplication
@AllArgsConstructor
public class ComWoroAssignmentApplication {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) throws Exception{
		SpringApplication.run(ComWoroAssignmentApplication.class, args);

	}

//	@PostConstruct
	public void insertOneUser()throws Exception {
		User user = new User(0, "Ashish", bCryptPasswordEncoder.encode("Ashish@123"), "ashish@gmail.com");
	User user1=	userRepository.save(user);
		System.out.println(user1);
	}
}
