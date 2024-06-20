package com.example.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.member.service.MemberService;
import com.example.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberVO memberVO;

	@Override
	@GetMapping("/member/listMembers.do")
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

//	public String listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = (String) request.getAttribute("viewName");

		List membersList = memberService.listMembers();
//		ModelAndView mav = new ModelAndView(viewName);
		ModelAndView mav = new ModelAndView("/member/listMembers");

		mav.addObject("membersList", membersList);
		return mav;
	}

	@Override
	@PostMapping("/member/addMember.do")
	public ModelAndView addMember(@Valid @ModelAttribute("member") MemberVO member, BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
		
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("memberForm");
			mav.addObject("fieldErrors", bindingResult.getFieldErrors());
			return mav;
		}
		memberService.addMember(member);
		return new ModelAndView("redirect:/member/listMembers.do");
	}

	@Override
	@GetMapping("/member/removeMember.do")
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@PostMapping("/member/login.do")
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			session.setAttribute("sessionId", session.getId());

			String action = (String) session.getAttribute("action");
			session.removeAttribute("action");

			if (action != null) {
				mav.setViewName("redirect:" + action);
			} else {
				mav.setViewName("redirect:/member/listMembers.do");
			}
		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}

	@Override
	@GetMapping("/member/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
	
		return new ModelAndView("redirect:/member/listMembers.do");
	}
	
	@GetMapping("/member/modMember.do")
	public ModelAndView modMemberForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/member/modMember");
		return mav;
	}
	
	@Override
	@PostMapping("/member/modMember.do")
	public ModelAndView modMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		memberService.updateMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@PostMapping("/member/updateMember.do")
	public ModelAndView updateMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		memberService.updateMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@GetMapping("/member/*Form.do")
	public ModelAndView form(@RequestParam(value= "result", required=false) String result,
			@RequestParam(value= "action", required=false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);

		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}

	@GetMapping({"/","/main.do"})
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@ModelAttribute("loginStatus")
	public boolean getLoginStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("isLogOn") != null && (boolean) session.getAttribute("isLogOn");
    }
	
	@ModelAttribute("sessionId")
    public String getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (String) session.getAttribute("sessionId") : null;
    }
}