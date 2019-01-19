package com.bs.lec17.member.service;

import com.bs.lec17.member.Member;

public interface IMemberService {
	// 이 인터페이스를 MemberSerivce
	void memberRegister(String memId, String memPw, String memMail, String memPhone1, String memPhone2,
			String memPhone3);

	Member memberSearch(String memId, String memPw);

	void memberModify();

	void memberRemove();
}
