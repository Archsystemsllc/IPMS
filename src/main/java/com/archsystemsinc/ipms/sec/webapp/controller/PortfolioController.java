package com.archsystemsinc.ipms.sec.webapp.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PortfolioController {
	public PortfolioController() {
		super();
	}

	private final Log log = LogFactory.getLog(PrivilegeController.class);
	
	
	@RequestMapping(value = "/portfolio")
	public String portfolio(final Model model) {
		
		return "portfolio";
	}

}
