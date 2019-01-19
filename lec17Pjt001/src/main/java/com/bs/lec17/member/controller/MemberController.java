package com.bs.lec17.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.lec17.member.Member;
import com.bs.lec17.member.service.MemberService;

/* html에 /member/memJoin이라고 사이에 경로가 추가된다면 controller로 똑같이 추가해줘야 한다.
하지만 member가 계속 중복된다면 보기가 힘들다. 이때 requestMapping을 써주면 중복된 내용을 생략할 수 있다. */
// 어노테이션엔 ; 안넣는 것 잊지 말기!!!
@Controller
@RequestMapping("/member")
public class MemberController {

// 이렇게 객체를 생성해서 써도 되지만 스프링을 이용하므로 굳이 이렇게 할 이유가 없다.
//	MemberService service = new MemberService();

//	@Autowired >> 이걸 써도 되고 
	@Resource(name = "memService") // >> 특정 이름을 지정할 때. 지정 안 해도 됨.
	// 서비스를 가져와서
	MemberService service;

	// value는 값이 하나면 생략할 수 있다. method는 get일때 생략할 수 있다.
	// html은 post인데 여기엔 post를 생략한다면 동일한 이름의 value를 가진 method를 찾아준다.
	// 하지만 알아보기 어려우므로 post를 꼭 써준다.

	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public String memJoin(Model model, HttpServletRequest request) {

//		만약 이렇게 변수를 get/set하는 객체를 넣어준다.
//		public String memJoin(Member member) {
//		아래의 긴 변수들을 다 생략할 수 있다. setter가 작용하기 떄문.
//		사용자가 html에 정보를 담아 날리면 setter 메소드가 html에 입력한 값을 객체에 있는 프로퍼티에 넣어준다.
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
//		String memMail = request.getParameter("memMail");
//		String memPhone1 = request.getParameter("memPhone1");
//		String memPhone2 = request.getParameter("memPhone2");
//		String memPhone3 = request.getParameter("memPhone3");

		// html form tag로 전달받은 데이터를 받아서
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		String memMail = request.getParameter("memMail");
		String memPhone1 = request.getParameter("memPhone1");
		String memPhone2 = request.getParameter("memPhone2");
		String memPhone3 = request.getParameter("memPhone3");

		// memberService에 있는 메소드에 값을 보낸다.
		service.memberRegister(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
//		다른 방법으로 하면 이렇게 getter/setter로 등록한다.
//		service.memberRegister(member.getMemId(), member.getMemPw(), member.getMemMail(), member.getMemPhone1(), member.getMemPhone2(), member.getMemPhone3());

//		다른 방법으로 하면 아래도 생략 가능하다.
		model.addAttribute("memId", memId);
		model.addAttribute("memPw", memPw);
		model.addAttribute("memMail", memMail);
		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);

		return "memJoinOk";
	}

	// form의 name 값과 실제 사용할 데이터를 적어줄 수도 있다.
	// public String memLogin(Model model, @RequestParam("memId", String memId),
	// @RequestParam("memPw", String memPw)) {
	// 그럼 아래의 내용을 생략할 수 있다.
	// String memId = request.getParameter("memId");
	// String memPw = request.getParameter("memPw");

	// @RequestParam((value="memId", required=true), String memId)
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(Model model, HttpServletRequest request) {

		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");

		Member member = service.memberSearch(memId, memPw);

		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "memLoginOk";
	}

}