package com.archsystemsinc.ipms.sec.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.poi.service.DownloadService;
import com.archsystemsinc.ipms.sec.model.ModelIPMS;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;

import com.archsystemsinc.ipms.sec.persistence.service.IModelService;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationGroupService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

@Controller
public class ModelController extends AbstractController<ModelIPMS> {
	public ModelController() {
		super(ModelIPMS.class);
	}

	private final Log log = LogFactory.getLog(ModelController.class);

	@Autowired
	private IModelService service;

	@Autowired
	private IPrincipalService principalService;
	
	@Autowired
	private IProgramService programService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private DownloadService downloadService;

	@Override
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				GenericConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setLenient(false);
		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/models", method = RequestMethod.GET)
	public String ModelIpms(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final List<ModelIPMS> models = service.findAll();
		if(models != null) {
			Collections.sort((List<ModelIPMS>) models);
		}		
		model.addAttribute("models", models);
		return "models";
	}

	@RequestMapping(value = "/model/{id}")
	public String ModelIPMS(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final ModelIPMS modelIPMS = service.findOne(id);
		model.addAttribute("modelIPMS", modelIPMS);
		
			final String page = request.getParameter("page");
			if (page == null)
				model.addAttribute("page", "");
			else
				model.addAttribute("page", page);
		return "modelIPMS";
	}

	@RequestMapping(value = "/new-Model", method = RequestMethod.GET)
	public String newModel(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final ModelIPMS modelIPMS = new ModelIPMS();
		model.addAttribute("modelIPMS", modelIPMS);
		model.addAttribute("referenceData", referenceData());
		return "modelsAdd";
	}
	
	@RequestMapping(value = "/new-model/{programId}", method = RequestMethod.GET)
	public String newModelWithProgram(final Model model, @PathVariable("programId") final Long programId, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Program program = programService.findOne(programId);
		final ModelIPMS modelIPMS = new ModelIPMS();
		if(null!=program) {
			modelIPMS.setProgram(program);;
		}
		model.addAttribute("program", program);
		model.addAttribute("referenceData", referenceData());
		return "modelsAdd";
	}

	@RequestMapping(value = "/edit-model/{id}", method = RequestMethod.GET)
	public String editModel(@PathVariable("id") final Long id,
			final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final ModelIPMS modelIPMS = service.findOne(id);
		modelIPMS.setManagerId(modelIPMS.getManager().getId());
		model.addAttribute("modelIPMS", modelIPMS);
		model.addAttribute("referenceData", referenceData());
		return "modelsedit";
	}

	@RequestMapping(value = "/new-Model", method = RequestMethod.POST)
	public String addModel(
			@Valid @ModelAttribute("modelIPMS") final ModelIPMS modelIPMS,
			final BindingResult result, final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
System.out.println("testing");
		String returnView = "";
		final Principal manager = principalService.findOne(modelIPMS
				.getManagerId());

		final Program program=programService.findOne(modelIPMS.getProgramId());
		modelIPMS.setProgram(program);
		modelIPMS.setManager(manager);
		try{
		if (result.hasErrors()) {
			returnView = "modelsAdd";
			model.addAttribute("modelIPMS", modelIPMS);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(modelIPMS);
			model.addAttribute("success","success.model.created");
			returnView = "models";
		}	
		}
		catch(Exception e)
		{
			model.addAttribute("error","unique.model.modelName");
			returnView = "modelsAdd";
			model.addAttribute("modelIPMS", modelIPMS);
			model.addAttribute("referenceData", referenceData());	
		}
		return returnView;

	}

	@RequestMapping(value = "/edit-model", method = RequestMethod.POST)
	public String updateProgram(
			@Valid @ModelAttribute("modelIPMS") final ModelIPMS modelIPMS,
			final BindingResult result, final Model model,
			final HttpServletRequest request, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		String returnView = "";
		// using name as long --bad idea
		if(modelIPMS.getProgram().getId().equals(0L)) {
			modelIPMS.setProgram(null);
		}
		final Principal iprincipal = principalService.findOne(modelIPMS
				.getManagerId());
		modelIPMS.setManager(iprincipal);
		if (request.getParameter("btnAction").equalsIgnoreCase("Activate")) {
			modelIPMS.setActive(true);
			
		} else if (request.getParameter("btnAction").equalsIgnoreCase(
				"Deactivate")) {
			modelIPMS.setActive(false);

		} else if (request.getParameter("btnAction").equalsIgnoreCase(
				"End Model")) {
			System.out.println("Ending model " + modelIPMS.getId());
			modelIPMS.setEndDate(new Date(System.currentTimeMillis()));
		} 
		try{
		if (result.hasErrors()) {
			returnView = "modelsedit";
		} else {
			service.update(modelIPMS);
			returnView = "forward:model/" + modelIPMS.getId();
			model.addAttribute("success","success.modelIPMS.updated");
		}
		model.addAttribute("modelIPMS", modelIPMS);
		model.addAttribute("referenceData", referenceData());
		}catch(Exception e)
		{
			model.addAttribute("error","unique.modelIPMS.modelName");
			returnView = "modelsadd";
			model.addAttribute("modelIPMS", modelIPMS);
			model.addAttribute("referenceData", referenceData());	
		}
		
		return returnView;
	}

	@SuppressWarnings("unchecked")
	protected Map referenceData() {
		final Map referenceData = new HashMap();

		final Map<Integer, String> likelihoodList = new LinkedHashMap<Integer, String>();
		likelihoodList.put(0, "High");
		likelihoodList.put(1, "Medium");
		likelihoodList.put(2, "Low");
		referenceData.put("likelihoodList", likelihoodList);

		final List<Principal> list = principalService.findAll();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		referenceData.put("principalList", pList);
		
		List<Program> program = programService.findAll();
		
		final Map<Integer, String> programList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < program.size(); i++) {
			programList.put(program.get(i).getId().intValue(), program.get(i).getName());
		}
		
		referenceData.put("programList", programList);
		return referenceData;
	}

	@Override
	protected IService<ModelIPMS> getService() {
		return service;
	}

	@RequestMapping(value = "/models/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model,
			final java.security.Principal principal)
					throws ClassNotFoundException {
		logger.debug("Received request to download issues report as an XLS");
		final String sheetName = GenericConstants.MODELS;
		final String[] coloumnNames = { "1", "2", "3", "4", "5", "6" };
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List modelIPMS = service.findUserModels(currentUser);
		downloadService
				.downloadXLS(response, sheetName, modelIPMS, coloumnNames);
	}


}
