package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordChecker {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("a"));
		System.out.println(encoder.encode("a"));
		System.out.println("$2a$10$xJ8Z8fqKcJ9vImSjC4o/xu5Q4R2YF.IEtMlKwsvZRiPh9XE0XngmK");
	}

}
