package com.archsystemsinc.ipms.sec.webapp.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.archsystemsinc.ipms.sec.model.Program;

public class ProgramValidator implements Validator{

	@Override
	public boolean supports(final Class clazz) {
		// just validate the Program instances
		return Program.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(final Object target, final Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managerName",
				"required.managerName", "Manager is required.");

		// final Program program = (Program) target;

		// if(!(cust.getPassword().equals(cust.getConfirmPassword()))){
		// errors.rejectValue("password", "notmatch.password");
		// }

		// if(cust.getFavFramework().length==0){
		// errors.rejectValue("favFramework", "required.favFrameworks");
		// }

		// if("NONE".equals(cust.getCountry())){
		// errors.rejectValue("country", "required.country");
		// }

	}

}