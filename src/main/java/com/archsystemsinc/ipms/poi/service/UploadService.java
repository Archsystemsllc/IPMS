package com.archsystemsinc.ipms.poi.service;

import java.io.IOException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
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
	
	private Workbook fileExcel;
	
	private Sheet firstFileSheet;
	
	private Map<String,String> excelColumnMap = new HashMap<String,String>();
	
	public final static String REDIRECT = "redirect:";
	
	public final static String FILE_UPLOAD_ERROR = "fileUploadError";
	
	public final static String FILE_UPLOAD_SUCCESS = "fileUploadSuccess";
	
	public final static String ERROR_UPLOAD_MISSING = "error.upload.missing";
	
	public final static String ERROR_UPLOAD_INVALID_FORMAT = "error.upload.invalid.format";
	
	public final static String ERROR_UPLOAD_INTERNAL_PROBLEM = "error.upload.internal.problem";
	
	public final static String SUCCESS_UPLOAD_MESSAGE = "success.import.document";
	
	public final static String ISSUES_VIEW = "/app/issues";
	
	public final static String ISSUES_UPLOAD = "/app/issues/upload";
	
	
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
			fileExcel = WorkbookFactory.create(uploadItem.getFileData().getInputStream());
			firstFileSheet = fileExcel.getSheetAt(0);
			if(uploadItem == null || uploadItem.getFileData() == null || uploadItem.getFileData().getInputStream() ==null || uploadItem.getFileData().getSize() == 0)
			{
				redirectAttributes.addFlashAttribute(FILE_UPLOAD_ERROR, ERROR_UPLOAD_MISSING);
				return FILE_UPLOAD_ERROR;
			} else {
				if(GenericConstants.ISSUES.equalsIgnoreCase(typeOfUpload)){
					returnString = uploadIssues(uploadItem, redirectAttributes);
				}else if(GenericConstants.TASKS.equalsIgnoreCase(typeOfUpload)){
					returnString = uploadTasks(uploadItem, redirectAttributes);
				}else if(GenericConstants.LESSONS_LEARNED.equalsIgnoreCase(typeOfUpload)){
					returnString = uploadLessonsLearned(uploadItem, redirectAttributes);
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

	public String uploadTasks(FileUpload uploadItem, final RedirectAttributes redirectAttributes) {
		return "redirect:/app/tasks";
	}
	
	public String uploadLessonsLearned(FileUpload uploadItem, final RedirectAttributes redirectAttributes) {
		return "redirect:/app/LessonsLearned";
	}
	

	private void constructExcelColumnMap(String typeOfUpload) {
		switch(typeOfUpload) {
			case GenericConstants.ISSUES :
				excelColumnMap.put("name", "Name");
				excelColumnMap.put("summary", "Summary");
				excelColumnMap.put("description", "Description");
				excelColumnMap.put("dateReported", "Date Reported");
				excelColumnMap.put("dueDate", "Due Date");
		}
		
	}

	
}
