package com.example.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component("memberVO")
public class MemberVO {
	@NotEmpty(message = "아이디는 필수 항목입니다.")
	@Size(min = 3, max = 11, message = "아이디는 최소 3자에서 최대 11자여야 합니다.")
	private String id;

	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	@Size(min = 3, max = 11, message = "비밀번호는 최소 3자에서 최대 11자여야 합니다.")
	private String pwd;

	@NotEmpty(message = "이름은 필수 항목입니다.")
	@Size(min = 2, max = 11, message = "이름은 최소 2자에서 최대 11자여야 합니다.")
	private String name;

	@Email(message = "유효한 이메일 주소를 입력해주세요.")
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	private String email;
	
	private Date joinDate;

	public MemberVO() {
		System.out.println("MemberVO 생성자 생성");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
