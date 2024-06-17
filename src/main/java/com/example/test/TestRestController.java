package com.example.test;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 제어의 역행 지원(4가지)
// @Controller, @RestController, @Component, @Service
@RestController
@RequestMapping("/api")
public class TestRestController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value="msg", required=false) String msg) {
		return msg;
	}
	
	@GetMapping("/hello2")
	public HashMap<String, String> hello3() {
		HashMap<String, String> map = new HashMap<>() {{
		put("이름", "홍길동");
		put("나이", "30");
		put("국적", "서울");
	}};
	return map;
	}
}
