package jbr.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jbr.springmvc.model.User;
/* Controller 제작 순서 ↓
 * @Controller를 이용해서 클래스를 생성한다.
 * @RequestMapping을 이용해, view의 요청 경로 지정한다.
 * 요청 처리 메소드(로직) 구현한다.
 * 뷰 이름 리턴한다. */
@Controller
public class RegistrationController {
	@Autowired
	public UserService userService;
	
	// @RequestMapping를 사용해 view의  요청 경로를 지정한다.
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		// data와 view를 동시에 설정이 가능하다.
		ModelAndView mav = new ModelAndView("register"); // view의 경로
		mav.addObject("user", new User()); // view로  보낼 data 값
		
		// ModelAndView 객체를 반환
		return mav; 
	}
	
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	@ModelAttribute("user") User user) {
		userService.register(user);
		
		// ModelAndView 객체를 새로 생성하여 반환 (이렇게  view 부분을 임의대로 설정가능하다는 점에서, data부분만 설정이 가능한 Model객체와는 차이가 있다.)
		return new ModelAndView("welcome", "firstname", user.getFirstname());
	}

}
