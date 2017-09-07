package com.archsystemsinc.ipms.sec.model;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonView;

/**
 * @author Ramz
 *
 */
public class BudgetTimeOutputJSON {

	@JsonView(Views.Public.class)
	List<String> label;

	@JsonView(Views.Public.class)
	List<String> data;
	
	@JsonView(Views.Public.class)
	BigDecimal maxBudget;
	
	@JsonView(Views.Public.class)
	String projectName;
	
	public BudgetTimeOutputJSON() {
		
	}

	public BudgetTimeOutputJSON(List<String> label, List<String> data) {
		super();
		this.label = label;
		this.data = data;
	}

	public List<String> getLabel() {
		return label;
	}

	public void setLabel(List<String> label) {
		this.label = label;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public BigDecimal getMaxBudget() {
		return maxBudget;
	}

	public void setMaxBudget(BigDecimal maxBudget) {
		this.maxBudget = maxBudget;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
