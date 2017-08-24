package com.archsystemsinc.ipms.util.webapp;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(final ModelMap model) {
//		return "login";
//
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(final ModelMap model) {
		return "login2";

	}


	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(final ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(final ModelMap model) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "logout";

	}

}
