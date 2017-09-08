package com.archsystemsinc.ipms.poi.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.FinancialsUpload;
import com.archsystemsinc.ipms.sec.model.Forecast;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.LessonsLearned;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.MeetingMinutes;
import com.archsystemsinc.ipms.sec.model.OrganizationCategory;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.model.WBSBudget;
import com.archsystemsinc.ipms.sec.persistence.service.IActionItemService;
import com.archsystemsinc.ipms.sec.persistence.service.IForecastService;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationCategoryService;
import com.archsystemsinc.ipms.sec.persistence.service.ITaskService;
import com.archsystemsinc.ipms.sec.persistence.service.ILessonsLearnedService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingMinutesService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.IWBSBudgetService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.sec.webapp.controller.FileUpload;

/**
 * Service for processing Apache POI-based reports
 * 
 * @author Ramya Gaddam
 */
@Service
//@Transactional
public class UploadService {

	private static Logger logger = Logger.getLogger("UploadService");
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IProgramService programService;
	
	@Autowired
	private IIssueService issueService;
	
	@Autowired
	private ILessonsLearnedService lessonslearnedservice;
	
	@Autowired
	private IActionItemService actionitemservice;
	
	@Autowired
	private IMeetingService meetingservice;
	
	@Autowired
	private ITaskService taskservice;
	
	@Autowired
	private IForecastService forecastService;
	
	@Autowired
	private IOrganizationCategoryService orgcatService;
	
	@Autowired
	private IWBSBudgetService wbsbudgetService;
	
	@Autowired
	private IMeetingMinutesService meetingminutesService;
	
	private Workbook fileExcel;
	
	private Sheet firstFileSheet;
	
	private Map<String,String> excelColumnMap = new HashMap<String,String>();
	
	public final static String REDIRECT = "redirect:";
	
	public final static String FILE_UPLOAD_ERROR = "fileUploadError";
	
	public final static String FILE_UPLOAD_SUCCESS = "fileUploadSuccess";
	
	public final static String ERROR_UPLOAD_MISSING = "error.upload.empty";
	
	public final static String ERROR_UPLOAD_INVALID_FORMAT = "error.upload.invalid.format";
	
	public final static String ERROR_UPLOAD_INTERNAL_PROBLEM = "error.upload.internal.problem";
	
	public final static String ERROR_UPLOAD_MISSING_DATA = "error.upload.missing.data";
	
	public final static String SUCCESS_UPLOAD_MESSAGE = "success.import.document";
	
	public final static String ISSUES_VIEW = "/app/issues";
	
	public final static String ISSUES_UPLOAD = "/app/issues/upload";
	
	public final static String LESSONSLEARNED_UPLOAD = "/app/uploadlessonslearned";
	
	public final static String LESSONSLEARNED_VIEW = "/app/lessonslearneds";
	
	public final static String ACTIONITEMS_UPLOAD = "/app/uploadactionitems";
	
	public final static String ACTIONITEMS_VIEW = "/app/actionitems";
	
	public final static String MEETING_MINUTES_VIEW = "/app/meetingminutes";
	
	public final static String MEETING_MINUTES_UPLOAD = "/app/meetingminutesupload";
	
	public final static String EVM_UPLOAD = "/app/uploadfinancial";

		
	
	
	/**
	 * * <pre>
	 * Processes the upload of Excel format. It does the following steps:
	 * 
	 * 1.Creates a new workbook from uploaded file
	 * 2.Verifies if the file uploaded is not null and not empty
	 * 3.Based on the type of upload, calls the specific upload function.
	 * 4.Handles common exceptions here.
	 * 5.Returns error string based on the upload function and adds attributes
	 * 	 which gives user friendly message.
	 * 6.If uploaded successfully, sends upload success message.
	 * 
	 *
	 * </pre>
	 */
	public String uploadXLS(FileUpload uploadItem, String typeOfUpload, final RedirectAttributes redirectAttributes) {
		String returnString = "";
		try {
			if(uploadItem == null || uploadItem.getFileData() == null || uploadItem.getFileData().getSize() == 0 || uploadItem.getFileData().getInputStream() ==null)
			{
				redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_MISSING);
				return FILE_UPLOAD_ERROR;
			} else {
				fileExcel = WorkbookFactory.create(uploadItem.getFileData().getInputStream());
				firstFileSheet = fileExcel.getSheetAt(0);
				//Checking if file contains atleast 1 row of data other than header in 1st row.
				if(firstFileSheet.getFirstRowNum()  < firstFileSheet.getLastRowNum())
				{
					if(GenericConstants.ISSUES.equalsIgnoreCase(typeOfUpload)){
						returnString = uploadIssues(uploadItem, redirectAttributes);
					}else if(GenericConstants.TASKS.equalsIgnoreCase(typeOfUpload)){
						returnString = uploadTasks(uploadItem, redirectAttributes);
					}else if(GenericConstants.LESSONS_LEARNED.equalsIgnoreCase(typeOfUpload)){
						returnString = uploadLessonsLearned(uploadItem, redirectAttributes);
					}else if(GenericConstants.MEETING_MINUTES.equalsIgnoreCase(typeOfUpload)){
						returnString = uploadMeetingMinutes(uploadItem, redirectAttributes);
					}else if(GenericConstants.ACTION_ITEMS.equalsIgnoreCase(typeOfUpload)){
						returnString = uploadActionItems(uploadItem, redirectAttributes);
					}	
				} else {
					redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_MISSING_DATA);
					return FILE_UPLOAD_ERROR;
				}
			} 
		} catch (InvalidFormatException | IOException e1) {
			redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_INVALID_FORMAT);
			return FILE_UPLOAD_ERROR;
		} catch (Exception e) {
			logger.debug(e.getStackTrace());
			redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_INTERNAL_PROBLEM);
			return FILE_UPLOAD_ERROR;
		}			
		return returnString;
	}
	

	public String uploadIssues(FileUpload uploadItem, final RedirectAttributes redirectAttributes) {
	
		boolean reqFieldsEmpty = false;
		if(uploadItem.getProgramId() == null) {
			redirectAttributes.addFlashAttribute("message", "Please select value for Program");
			reqFieldsEmpty = true;
		}
		if(uploadItem.getProjectId() == null) {
			redirectAttributes.addFlashAttribute("message", "Please select value for Project");
			reqFieldsEmpty = true;
		} 
		if(reqFieldsEmpty) {
			return REDIRECT + ISSUES_UPLOAD;
		}
		else {
			Project project = projectService.findOne(uploadItem.getProjectId());
			Program program = programService.findOne(uploadItem.getProgramId());
			try {
				Issue issue = null;
				constructExcelColumnMap(GenericConstants.ISSUES);
	          //I've Header and I'm ignoring header for that I've +1 in loop
				for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
					//System.out.println(r);
					  issue= new Issue();
		              Row ro=firstFileSheet.getRow(r);
		              issue.setName(ro.getCell(0).getStringCellValue());
		              issue.setSummary(ro.getCell(1).getStringCellValue());
		              issue.setDescription(ro.getCell(2).getStringCellValue());
		              issue.setDateReported(ro.getCell(3).getDateCellValue());
		              issue.setDueDate(ro.getCell(4).getDateCellValue());
	              }
	              //From code
	              //status - Pending by default		        
				  issue.setPriority("N/A");
				  issue.setProject(project);
				  issue.setProgram(program);
	              issueService.create(issue);
			
			}  catch(ConstraintViolationException ce) {
				Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
				for(ConstraintViolation<?> v: cv) {
					if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
					} else {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
					}	
					return REDIRECT + ISSUES_UPLOAD;
				}
			}
		}
		redirectAttributes.addFlashAttribute(FILE_UPLOAD_SUCCESS, SUCCESS_UPLOAD_MESSAGE);
		return REDIRECT + ISSUES_VIEW;
	}
	
	public String uploadMeetingMinutes(FileUpload uploadItem, final RedirectAttributes redirectAttributes) {
		
		boolean reqFieldsEmpty = false;
		if(uploadItem.getMeetingId() == null) {
			redirectAttributes.addFlashAttribute("message", "Please select value for Meeting");
			reqFieldsEmpty = true;
		}
		if(reqFieldsEmpty) {
			return REDIRECT + MEETING_MINUTES_UPLOAD;
		}
		else {
			try {
				MeetingMinutes minutes = null;
				constructExcelColumnMap(GenericConstants.MEETING_MINUTES);
	          //Ignoring header in the first row
				for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
					  minutes= new MeetingMinutes();
		              Row ro=firstFileSheet.getRow(r);
		              minutes.setName(ro.getCell(0).getStringCellValue());
		              minutes.setDiscussion(ro.getCell(1).getStringCellValue());
		              minutes.setScribe(ro.getCell(2).getStringCellValue());
		              minutes.setStartTime(ro.getCell(3).getStringCellValue());
		              minutes.setEndTime(ro.getCell(4).getStringCellValue());
	              }
	              //From code
				minutes.setMeetingid(uploadItem.getMeetingId());
	              meetingminutesService.create(minutes);
			
			}  catch(ConstraintViolationException ce) {
				// In this exception, we are mapping the property path to excel column header to display on UI informing the user that, one
				// of the data constraint has failed.
				
				Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
				for(ConstraintViolation<?> v: cv) {
					if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
					} else {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
					}	
					return REDIRECT + MEETING_MINUTES_UPLOAD;
				}
			} 
		}
		redirectAttributes.addFlashAttribute(FILE_UPLOAD_SUCCESS, SUCCESS_UPLOAD_MESSAGE);
		return REDIRECT + MEETING_MINUTES_VIEW;
	}
/***
 * this is a method for reading the xlsl file and for Exception handling
 * @param uploadItem
 * @param redirectAttributes
 * @return
 */
	public String uploadTasks(FileUpload uploadItem, final RedirectAttributes redirectAttributes) {
		boolean reqFieldsEmpty = false;
		if(uploadItem.getProgramId() == null) {
			redirectAttributes.addFlashAttribute("message", "Please select value for Program");
			reqFieldsEmpty = true;
		}
		if(uploadItem.getProjectId() == null) {
			redirectAttributes.addFlashAttribute("message", "Please select value for Project");
			reqFieldsEmpty = true;
		} 
		if(reqFieldsEmpty) {
			return "redirect:/app/tasks";
		}
		else {
			Project project = projectService.findOne(uploadItem.getProjectId());
			Program program = programService.findOne(uploadItem.getProgramId());
			try {
				Task task = null;
				constructExcelColumnMap(GenericConstants.TASKS);
	          //I've Header and I'm ignoring header for that I've +1 in loop
				for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
					  task= new Task();
		              Row ro=firstFileSheet.getRow(r);
		              task.setName(ro.getCell(0).getStringCellValue());
		              task.setDescription(ro.getCell(1).getStringCellValue());
		              task.setDueDate(ro.getCell(2).getDateCellValue());
		              task.setDateCreated(Calendar.getInstance().getTime());
	              }
	              //From code
				  task.setProject(project);
				  task.setProgram(program);
	              taskservice.create(task);
			
			}  catch(ConstraintViolationException ce) {
				Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
				for(ConstraintViolation<?> v: cv) {
					if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
					} else {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
					}	
					return "redirect:/app/tasks";
				}
			}
		}
		redirectAttributes.addFlashAttribute(FILE_UPLOAD_SUCCESS, SUCCESS_UPLOAD_MESSAGE);
		return "redirect:/app/tasks";
	
	}
	
	public String uploadLessonsLearned(FileUpload uploadItem, final RedirectAttributes redirectAttributes) {
		
		
		Issue issue = null;
		if(0!=uploadItem.getissueId()){
			issue = issueService.findOne(uploadItem.getissueId());
		}else {
			redirectAttributes.addFlashAttribute("issuerequirederror","NotNull.lessonsLearned.issueId");
			return "redirect:/app/uploadlessonslearned";
		}
		Meeting meeting = null;
		if(0!=uploadItem.getmeetingId()){
			meeting = meetingservice.findOne(uploadItem.getmeetingId());
		} else {
			redirectAttributes.addFlashAttribute("meetingrequirederror","NotNull.lessonsLearned.meetingId");
			return "redirect:uploadlessonslearned";
		} 
		try {
			LessonsLearned lessonslearned = null;
			constructExcelColumnMap(GenericConstants.LESSONS_LEARNED);
          //I've Header and I'm ignoring header for that I've +1 in loop
			for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
				//System.out.println(r);
				lessonslearned=new LessonsLearned();
	              Row ro=firstFileSheet.getRow(r);
	              lessonslearned.setDate(Calendar.getInstance().getTime());
	              lessonslearned.setName(ro.getCell(0).getStringCellValue());
	              lessonslearned.setSummary(ro.getCell(1).getStringCellValue());
	              lessonslearned.setImpact(ro.getCell(2).getStringCellValue());
	              lessonslearned.setRecommendation(ro.getCell(3).getStringCellValue());
	              lessonslearned.setAreasOfImprovement(ro.getCell(4).getStringCellValue());
	              lessonslearned.setSuccessFactors(ro.getCell(5).getStringCellValue());
	              lessonslearned.setIssue(issue);
	              lessonslearned.setMeeting(meeting);
	              
	              lessonslearnedservice.create(lessonslearned);
	            }
		}catch(ConstraintViolationException ce){
			Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
			for(ConstraintViolation<?> v: cv) {
				if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
					redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
				} else {
					redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
				}	
				return REDIRECT + LESSONSLEARNED_UPLOAD;
			}
		
	}
		redirectAttributes.addFlashAttribute(FILE_UPLOAD_SUCCESS, SUCCESS_UPLOAD_MESSAGE);
		return REDIRECT + LESSONSLEARNED_VIEW;	
		}
	public String uploadActionItems(FileUpload uploadItem, final RedirectAttributes redirectAttributes) {

		Issue issue = null;
		if(0!=uploadItem.getissueId()){
			issue = issueService.findOne(uploadItem.getissueId());
		}else {
			redirectAttributes.addFlashAttribute("issuerequirederror","NotNull.actionItem.issueId");
			return "redirect:uploadactionitems";
		}
		Meeting meeting = null;
		if(0!=uploadItem.getmeetingId()){
			meeting = meetingservice.findOne(uploadItem.getmeetingId());
		} else {
			redirectAttributes.addFlashAttribute("meetingrequirederror","NotNull.actionItem.meetingId");
			return "redirect:uploadactionitems";
		} 
		try {
			ActionItem actionitem = null;
			constructExcelColumnMap(GenericConstants.ACTION_ITEMS);
          //I've Header and I'm ignoring header for that I've +1 in loop
			for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
				actionitem = new ActionItem();
	              Row ro=firstFileSheet.getRow(r);
	              actionitem.setName(ro.getCell(0).getStringCellValue());
	              actionitem.setSummary(ro.getCell(1).getStringCellValue());
	              //actionitem.setStatus(ro.getCell(2).getStringCellValue());
	              actionitem.setDateCreated(ro.getCell(2).getDateCellValue());
	              actionitem.setDueDate(ro.getCell(3).getDateCellValue());
	              actionitem.setPriority("N/A");	
	              	              
	           actionitemservice.create(actionitem);
	            }
		}catch(ConstraintViolationException ce){
			Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
			for(ConstraintViolation<?> v: cv) {
				if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
					redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
				} else {
					redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
				}	
				return REDIRECT + ACTIONITEMS_UPLOAD;
			}
		
	}
		redirectAttributes.addFlashAttribute(FILE_UPLOAD_SUCCESS, SUCCESS_UPLOAD_MESSAGE);
		return REDIRECT + ACTIONITEMS_VIEW;	
	}
	
	public String uploadXLS(FinancialsUpload uploadItem, final RedirectAttributes redirectAttributes) {
		String returnString = "";
		try {
						
			if(uploadItem.getProjectId()== 0){
				redirectAttributes.addFlashAttribute("projectrequirederror","NotNull.financial.projectId");
				return "redirect:/app/uploadfinancial";
			}else {
				if(uploadItem.getForecastFileData().getSize()!=0){
					returnString = uploadForecast(uploadItem,redirectAttributes);
				}
				if(uploadItem.getOrganizationcategoryFileData().getSize()!= 0){
					returnString = uploadOrganizatinCategory(uploadItem,redirectAttributes);
				}
				if(uploadItem.getWbsFileData().getSize()!=0){
					returnString = uploadWBSBudget(uploadItem,redirectAttributes);
				}
				if(uploadItem.getForecastFileData().getSize()==0 && uploadItem.getOrganizationcategoryFileData().getSize()==0 && uploadItem.getWbsFileData().getSize()==0) {
					redirectAttributes.addFlashAttribute("fileUploadNull", "error.upload.missing");
					return REDIRECT + EVM_UPLOAD;
				}
			}
		}
		catch (Exception e) {
			logger.debug(e.getStackTrace());
			System.out.println(e.getStackTrace());
			System.out.println("error");
			redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_INTERNAL_PROBLEM);
			return FILE_UPLOAD_ERROR;
		}
		return returnString;
	}

	public String uploadForecast(FinancialsUpload uploadItem,final RedirectAttributes redirectAttributes) {
		
			Project project = projectService.findOne(uploadItem.getProjectId());
			final String currentUser = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			try {
				fileExcel = WorkbookFactory.create(uploadItem.getForecastFileData().getInputStream());
				firstFileSheet = fileExcel.getSheetAt(0);
				if(firstFileSheet.getFirstRowNum()  < firstFileSheet.getLastRowNum())
				{
				Forecast forecast = null;
			 for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
					forecast= new Forecast();
		              Row ro=firstFileSheet.getRow(r);
		              forecast.setTotalAmount(ro.getCell(0).getNumericCellValue());
		              forecast.setStartAmount(ro.getCell(1).getNumericCellValue());
		              forecast.setSpentAmount(ro.getCell(2).getNumericCellValue());
		              forecast.setLeftAmount(ro.getCell(3).getNumericCellValue());
		              forecast.setDate(ro.getCell(4).getDateCellValue());
		              forecast.setDatecreated(ro.getCell(5).getDateCellValue());
		              forecast.setDateupdated(ro.getCell(6).getDateCellValue());
           			  forecast.setProject(project);
				  forecastService.create(forecast);
			 }
			}  
				else {
					redirectAttributes.addFlashAttribute("forecastUploadError", "forecast.upload.error");
					return REDIRECT + EVM_UPLOAD ;
				}
			}catch(ConstraintViolationException ce) {
				Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
				for(ConstraintViolation<?> v: cv) {
					if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
					} else {
						redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
					}	
				 return REDIRECT + EVM_UPLOAD;
				}
			 }catch (InvalidFormatException | IOException e1) {
				 System.out.println("Error in I/O");
					redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_INVALID_FORMAT);
					return FILE_UPLOAD_ERROR;
				} 
		redirectAttributes.addFlashAttribute("forecastUploadSuccess", "forecast.upload.success");
		 return REDIRECT + EVM_UPLOAD;
		
	}
	public String uploadOrganizatinCategory(FinancialsUpload uploadItem,
			RedirectAttributes redirectAttributes) {
		
		Project project = projectService.findOne(uploadItem.getProjectId());
		 try {
			 	//Principal resource = principalService.findOne(2);
				fileExcel = WorkbookFactory.create(uploadItem.getOrganizationcategoryFileData().getInputStream());
				firstFileSheet = fileExcel.getSheetAt(0);
				if(firstFileSheet.getFirstRowNum()  < firstFileSheet.getLastRowNum())
				{
				OrganizationCategory orgcat= null;
				for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
					orgcat = new OrganizationCategory();
		              Row ro=firstFileSheet.getRow(r);
		                orgcat.setRate(ro.getCell(0).getNumericCellValue());
		                orgcat.setCategory(ro.getCell(1).getStringCellValue());
		                orgcat.setDateupdated(ro.getCell(2).getDateCellValue());
		                orgcat.setDatecreated(ro.getCell(3).getDateCellValue());
				        orgcat.setProject(project);
				     //   orgcat.setResourceId(resource);
		              orgcatService.create(orgcat);
				}      
			}
			else {
					redirectAttributes.addFlashAttribute("orgcatUploadError", "orgcat.upload.error");
					return REDIRECT + EVM_UPLOAD ;
				}
		 	}catch(ConstraintViolationException ce) {
	  				Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
					for(ConstraintViolation<?> v: cv) {
						if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
							redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
						} else {
							redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
						}	
					 return REDIRECT + EVM_UPLOAD;
					}
				 }catch (InvalidFormatException | IOException e1) {
						redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_INVALID_FORMAT);
						return FILE_UPLOAD_ERROR;
					} 
				redirectAttributes.addFlashAttribute("orgcatUploadSuccess", "orgcat.upload.success");
				return REDIRECT + EVM_UPLOAD;
			}
		
	public String uploadWBSBudget(FinancialsUpload uploadItem,final RedirectAttributes redirectAttributes) {
		try {
		//Project project = projectService.findOne(uploadItem.getProjectId());
		//Task task = taskService.findOne(26);
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
			fileExcel = WorkbookFactory.create(uploadItem.getWbsFileData().getInputStream());
			firstFileSheet = fileExcel.getSheetAt(0);
			if(firstFileSheet.getFirstRowNum()  < firstFileSheet.getLastRowNum())
			{
			WBSBudget wbsbudget = null;
			for(int r=firstFileSheet.getFirstRowNum()+1;r<=firstFileSheet.getLastRowNum();r++){
				wbsbudget = new WBSBudget();
				Row ro=firstFileSheet.getRow(r);
				wbsbudget.setDatecreated(ro.getCell(0).getDateCellValue());
				wbsbudget.setDateupdated(ro.getCell(1).getDateCellValue());
              // wbsbudget.setTask(task);
				wbsbudgetService.create(wbsbudget);
			}
		  } else {
				redirectAttributes.addFlashAttribute("wbsUploadError", "wbs.upload.error");
				return REDIRECT + EVM_UPLOAD ;
			}
		   
		}catch(ConstraintViolationException ce) {
			Set<ConstraintViolation<?>> cv = ce.getConstraintViolations();
			for(ConstraintViolation<?> v: cv) {
				if(v.getPropertyPath() == null || v.getPropertyPath().toString().isEmpty()) {
					redirectAttributes.addFlashAttribute("message", "Constraint Violation ! Please resolve : " +v.getMessage());
				} else {
					redirectAttributes.addFlashAttribute("message", "Constraint Violation for " +excelColumnMap.get(v.getPropertyPath().toString()) + " ! Please resolve : " + v.getMessage());
				}	
				return REDIRECT + EVM_UPLOAD;
		   }
		}catch (InvalidFormatException | IOException e1) {
		redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_INVALID_FORMAT);
		return FILE_UPLOAD_ERROR;
	} 
		
	redirectAttributes.addFlashAttribute("wbsUploadSuccess", "wbs.upload.success");
	return REDIRECT + EVM_UPLOAD;
	} 
	
	/**
	 * This method constructs a map between Java entity and Excel file template Header based on the type of upload.
	 *
	 * @param typeOfUpload
	 */
	private void constructExcelColumnMap(String typeOfUpload) {
		switch(typeOfUpload) {
		
			case GenericConstants.ISSUES :
				excelColumnMap.put("name", "Name");
				excelColumnMap.put("summary", "Summary");
				excelColumnMap.put("description", "Description");
				excelColumnMap.put("dateReported", "Date Reported");
				excelColumnMap.put("dueDate", "Due Date");
				
			case GenericConstants.LESSONS_LEARNED :
				excelColumnMap.put("name", "Name");
				excelColumnMap.put("summary", "Summary");
				excelColumnMap.put("impact", "Impact");
				excelColumnMap.put("recommendation", "Recommendation");
				excelColumnMap.put("areasOfImprovement", "Areas of Improvement");
				excelColumnMap.put("successFactors","Success Factors");
				
			case GenericConstants.ACTION_ITEMS :
				excelColumnMap.put("name", "Name");
				excelColumnMap.put("summary", "Summary");
				excelColumnMap.put("dateCreated", "Date Created");
				excelColumnMap.put("dueDate", "Due Date");
				
			case GenericConstants.MEETING_MINUTES :
				excelColumnMap.put("name", "Name");
				excelColumnMap.put("discussion", "Discussion");
				excelColumnMap.put("scribe", "Scribe");
				excelColumnMap.put("startTime", "Start Time");
				excelColumnMap.put("end_time", "End Time");
				
			case GenericConstants.TASKS :
				excelColumnMap.put("name", "Name");
				excelColumnMap.put("summary", "Summary");
				excelColumnMap.put("description", "Description");
				excelColumnMap.put("dateReported", "Date Reported");
				excelColumnMap.put("dueDate", "Due Date");
		}
	}		
}
