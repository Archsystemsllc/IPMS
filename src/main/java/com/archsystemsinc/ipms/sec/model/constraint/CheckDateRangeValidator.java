package com.archsystemsinc.ipms.sec.model.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;

public class CheckDateRangeValidator implements ConstraintValidator<CheckDateRange,Object> {
	
	private ObjectTypeEnum objectTypeEnum;
	
	@Override
	public void initialize(CheckDateRange constraintAnnotation) {
		this.objectTypeEnum = constraintAnnotation.value();
		
	}	

    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

        boolean isValid = true;
    	if (object == null)
            return isValid;
        
        if (objectTypeEnum == ObjectTypeEnum.Program) {
        	Program program = (Program) object;
        	if (program.getStartDate()!=null&&program.getEndDate()!=null&&program.getEndDate().before(program.getStartDate()))
        		isValid = false;
            else
            	isValid = true;        	
        }
        
        if (objectTypeEnum == ObjectTypeEnum.Project) {
        	Project project = (Project) object;
        	if (project.getStartDate()!=null&&project.getEndDate()!=null&&project.getEndDate().before(project.getStartDate()))
        		isValid = false;
            else
            	isValid = true;        	
        }
        
        if (objectTypeEnum == ObjectTypeEnum.Issue) {
        	Issue issue = (Issue) object;
        	if (issue.getDateReported()!=null &&issue.getDueDate()!=null && issue.getDueDate().before(issue.getDateReported()))
        		isValid = false;
            else
            	isValid = true;        	
        }
       
        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(objectTypeEnum + " End date cannot be greater than the Start date!").addConstraintViolation();
        }
        
        return isValid;
        
    }

	
}