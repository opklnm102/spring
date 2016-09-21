package controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Shop;
import logic.User;
import utils.WebConstants;

@Controller
public class LoginFormController {
	
	@Autowired
	private Shop shopService;
	
	@Autowired
	private Validator loginValidator;

	@RequestMapping(method=RequestMethod.GET)
	public String toLoginView(){
		return "loginform/login";
	}
	
	@ModelAttribute
	public User setUpForm(){
		return new User();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User user, BindingResult bindingResult, HttpSession session){
		
		this.loginValidator.validate(user, bindingResult);
		
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()){
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		
		try{
			//유저 정보 검색
			User loginUser = this.shopService.getUserIdAndPassword(user.getUserId(), user.getPassword());
			session.setAttribute(WebConstants.USER_KEY, loginUser);
			if(session.getAttribute(WebConstants.CART_KEY) == null){
				session.setAttribute(WebConstants.CART_KEY, this.shopService.getCart());;
			}
			
			//유저 확인시
			modelAndView.setViewName("loginform/loginSuccess");
			modelAndView.addObject("loginUser", loginUser);
			return modelAndView;
		}catch(EmptyResultDataAccessException e){
			//유저 미확인시
			bindingResult.reject("error.login.user");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}
}
