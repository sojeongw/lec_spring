package com.bs.lec17.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bs.lec17.member.Member;
import com.bs.lec17.member.dao.MemberDao;

// Service와 Component, Repository로 xml에 bean을 넣지 않고도 MemberController에 Autowired를 써서 사용할 수 있다.
//@Service	>> 서비스 객체에 사용하는 걸 추천 
//@Service("memService")
//@Component
//@Component("memService")
//@Repository
@Repository("memService")
public class MemberService implements IMemberService { // 인터페이스 구현

	@Autowired
	MemberDao dao;

	@Override
	public void memberRegister(String memId, String memPw, String memMail, String memPhone1, String memPhone2,
			String memPhone3) {
		System.out.println("memberRegister()");
		System.out.println("memId : " + memId);
		System.out.println("memPw : " + memPw);
		System.out.println("memMail : " + memMail);
		System.out.println("memPhone : " + memPhone1 + " - " + memPhone2 + " - " + memPhone3);

		// dao의 memberInsert에 보낸다. memberInsert를 찾아가보면?
		dao.memberInsert(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
	}

	@Override
	public Member memberSearch(String memId, String memPw) {
		System.out.println("memberSearch()");
		System.out.println("memId : " + memId);
		System.out.println("memPw : " + memPw);

		Member member = dao.memberSelect(memId, memPw);

		return member;
	}

	@Override
	public void memberModify() {

	}

	@Override
	public void memberRemove() {

	}

}