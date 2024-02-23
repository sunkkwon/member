package com.javastudy.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.javastudy.member.dto.MemberDTO;
import com.javastudy.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

	// 생성자 주입 @RequiredArgsConstructor 가 한다.
	private final MemberService memberService;

	@GetMapping("/member/save")
	public String saveForm() {
		return "save";
	}

	@PostMapping("/member/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
//		System.out.println("save method");
//		System.out.println("MemberDTO: " + memberDTO);

		memberService.save(memberDTO);

		return "login";
	}

	@GetMapping("/member/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO result = memberService.login(memberDTO);

		if (result != null) {
//			System.out.println("login 성공");
			session.setAttribute("loginEmail", result.getEmail());
			return "main";
		} else {
//			System.out.println("login 실패");
			return "login";
		}
	}

	@GetMapping("/member/")
	public String findAll(Model model) {
		List<MemberDTO> memberDTOList = memberService.findAll();

		model.addAttribute("memberList", memberDTOList);

		return "list";
	}

	@GetMapping("/member/{id}")
	public String findById(@PathVariable(name = "id") Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO); // html 로 파라메터 넘길때 사용
		return "detail";
	}

	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String myEmail = (String) session.getAttribute("loginEmail");
		MemberDTO memberDTO = memberService.updateForm(myEmail);
		model.addAttribute("updateMember", memberDTO);
		return "update";
	}

	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);

		return "redirect:/member/" + memberDTO.getId();
	}

	@GetMapping("/member/delete/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		memberService.deleteById(id);
		return "redirect:/member/";
	}

	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@PostMapping("/member/email-check")
	public @ResponseBody String emailCheck(@RequestParam("email") String email) {
		System.out.println("email: " + email);
		String checkResult = memberService.emailCheck(email);

		return checkResult;
//		if(checkResult != null) {
//			return "ok";
//		} else {
//			return "no";
//			
//		}
	}

}
