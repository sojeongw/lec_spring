package com.bs.lec21.member.dao;

import java.util.Map;

import com.bs.lec21.member.Member;

public interface IMemberDao {
	// db를 연결하면 map을 사용하지 않으니 수정한다.

//	Map<String, Member> memberInsert(Member member);
	int memberInsert(Member member);

	Member memberSelect(Member member);

	Member memberUpdate(Member member);

	Map<String, Member> memberDelete(Member member);
}
