package com.archsystemsinc.ipms.poi.service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.LessonsLearned;
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.Requirement;
import com.archsystemsinc.ipms.sec.model.Risk;
import com.archsystemsinc.ipms.sec.model.Task;

/**
 * Service for processing Apache POI-based reports
 * 
 * @author Abdul Nissar
 */
@Service
@Transactional
public class DownloadService {

	private static Logger logger = Logger.getLogger("DownloadService");

	/**
	 * Processes the download for Excel format. It does the following steps:
	 * 
	 * <pre>
	 * 1. Create new workbook
	 * 2. Create new worksheet
	 * 3. Define starting indices for rows and columns
	 * 4. Build layout
	 * 5. Fill report
	 * 6. Set the HttpServletResponse properties
	 * 7. Write to the output stream
	 * </pre>
	 */
	// @SuppressWarnings("unchecked")
	public void downloadWord(final HttpServletResponse response,
			final String documentName,
			final Set<Object> exportList)
					throws ClassNotFoundException {
		logger.debug("Downloading Word Document");

		final XWPFDocument document = new XWPFDocument();

		if (documentName.equalsIgnoreCase("AgendaItems")) {
			for (final Object tempObject : exportList) {
				final MeetingAgendaItem meetingAgendaItem = (MeetingAgendaItem) tempObject;
				final XWPFParagraph paragraphOne = document.createParagraph();
				final XWPFRun paragraphOneRunOne = paragraphOne.createRun();
				paragraphOneRunOne.setText("Agenda Item: "
						+ meetingAgendaItem.getTopic());
				final XWPFParagraph paragraphTwo = document.createParagraph();
				final XWPFRun paragraphTwoRunOne = paragraphTwo.createRun();
				paragraphTwoRunOne.setText("Owner: "
						+ meetingAgendaItem.getMeetingAgendaOwner().getName());
				final XWPFParagraph paragraphThree = document.createParagraph();
				final XWPFRun paragraphThreeRunOne = paragraphThree.createRun();
				paragraphThreeRunOne.setText("Description: "
						+ meetingAgendaItem.getDescription());
			}
		} else if (documentName.equalsIgnoreCase("Minutes")) {
			for (final Object tempObject : exportList) {
				final MeetingAgendaItem meetingAgendaItem = (MeetingAgendaItem) tempObject;
				final XWPFParagraph paragraphOne = document.createParagraph();
				final XWPFRun paragraphOneRunOne = paragraphOne.createRun();
				paragraphOneRunOne.setText("Agenda Item: "
						+ meetingAgendaItem.getTopic());
				paragraphOneRunOne.setBold(true);
				final XWPFParagraph paragraphTwo = document.createParagraph();
				final XWPFRun paragraphTwoRunOne = paragraphTwo.createRun();
				paragraphTwoRunOne.setText("Owner: "
						+ meetingAgendaItem.getMeetingAgendaOwner().getName());
				final XWPFParagraph paragraphThree = document.createParagraph();
				final XWPFRun paragraphThreeRunOne = paragraphThree.createRun();
				paragraphThreeRunOne.setText("Description: "
						+ meetingAgendaItem.getDescription());
				final XWPFParagraph paragraphFour = document.createParagraph();
				final XWPFRun paragraphFourRunOne = paragraphFour.createRun();
				paragraphFourRunOne.setText("Discussion: "
						+ meetingAgendaItem.getDiscussion());
			}
		}


		// 6. Set the response properties
		final String fileName = documentName + ".docx";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-word");

		// 7. Write to the output stream
		// 7. Write to the output stream
		WordWriter.write(response, document);
	}

	/**
	 * Processes the download for Excel format. It does the following steps:
	 * 
	 * <pre>
	 * 1. Create new workbook
	 * 2. Create new worksheet
	 * 3. Define starting indices for rows and columns
	 * 4. Build layout
	 * 5. Fill report
	 * 6. Set the HttpServletResponse properties
	 * 7. Write to the output stream
	 * </pre>
	 */
	// @SuppressWarnings("unchecked")
	public void downloadXLS(final HttpServletResponse response,
			final String sheetName,
			final List<Object> exportList,
			final String[] coloumnNames)
					throws ClassNotFoundException {
		logger.debug("Downloading Excel report");

		// 1. Create new workbook
		final HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		final HSSFSheet worksheet = workbook.createSheet(sheetName);

		// 3. Define starting indices for rows and columns
		final int startRowIndex = 0;
		final int startColIndex = 0;

		// 4. Build layout
		// Build title, date, and column headers
		Layouter.buildReport(worksheet, startRowIndex, startColIndex,
				sheetName, coloumnNames);

		// 5. Fill report
		FillManager.fillReport(worksheet, startRowIndex, startColIndex,
				exportList, sheetName);

		// 6. Set the response properties
		final String fileName = sheetName + "Report.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");

		// 7. Write to the output stream
		ExcelWriter.write(response, workbook);

	}
	/**
	 * Processes the download for Excel format. It does the following steps:
	 * 
	 * <pre>
	 * 1. Create new workbook
	 * 2. Create new worksheet
	 * 3. Define starting indices for rows and columns
	 * 4. Build layout
	 * 5. Fill report
	 * 6. Set the HttpServletResponse properties
	 * 7. Write to the output stream
	 * </pre>
	 */
	// @SuppressWarnings("unchecked")
	public void downloadPDF(final HttpServletResponse response,
			final String sheetName,
			final List<Object> exportList,
			final String[] coloumnNames)
					throws ClassNotFoundException {
		logger.debug("Downloading PDF report");

		downloadXLS(response,sheetName,exportList,coloumnNames);
	}


	@SuppressWarnings("rawtypes")
	public void buildReportsExcelDocument(final Map<String, Object> stringObjectMap,
			final HttpServletResponse httpServletResponse) throws Exception {
		//To change body of implemented methods use File | Settings | File Templates.
		final HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		int rowNum = 0;
		
		Collection collection = (Collection) stringObjectMap.get("risks");
		if(null!=collection) {
			final List<Risk> risks = new ArrayList<Risk>(collection);
			//create a wordsheet
			final HSSFSheet risksSheet = hssfWorkbook.createSheet("Risks");
			final String[] riskColumnNames = { "Risk Summary", "Id", "Impact", "Likelihood", "Status", "Action" };
	
			buildHeader(risksSheet, riskColumnNames);
			rowNum = 1;
			for (final Risk risk : risks) {
				//        create the row data
				final HSSFRow row = risksSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(risk.getRiskSummary());
				row.createCell(1).setCellValue(risk.getId());
				row.createCell(2).setCellValue(risk.getImpact());
				row.createCell(3).setCellValue(risk.getLikelihood());
				row.createCell(3).setCellValue(risk.getStatus());
				row.createCell(3).setCellValue(risk.getName());
			}
			risksSheet.autoSizeColumn(0);
			risksSheet.autoSizeColumn(1);
			risksSheet.autoSizeColumn(2);
			risksSheet.autoSizeColumn(3);
			risksSheet.autoSizeColumn(4);
			risksSheet.autoSizeColumn(5);
		}
		
		collection = (Collection) stringObjectMap.get("issues");
		if(collection!=null) {
			final List<Issue> issues = new ArrayList<Issue>(collection);
			//create a wordsheet
			final HSSFSheet issuesSheet = hssfWorkbook.createSheet("Issues");
			final String[] issueColumnNames = { "Name", "Id", "Description", "Status" };
	
			buildHeader(issuesSheet, issueColumnNames);
	
			for (final Issue issue : issues) {
				//        create the row data
				final HSSFRow row = issuesSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(issue.getName());
				row.createCell(1).setCellValue(issue.getId());
				row.createCell(2).setCellValue(issue.getDescription());
				row.createCell(3).setCellValue(issue.getStatus());
			}
			issuesSheet.autoSizeColumn(0);
			issuesSheet.autoSizeColumn(1);
			issuesSheet.autoSizeColumn(2);
			issuesSheet.autoSizeColumn(3);
		}
		
		collection = (Collection) stringObjectMap.get("actionItems");
		if(null!=collection) {
			final List<ActionItem> actionItems = new ArrayList<ActionItem>(collection);
			final HSSFSheet actionItemsSheet = hssfWorkbook.createSheet("ActionItems");
			final String[] actionsColumnNames = { "Id", "Summary", "AssignedTo", "Status" };
			buildHeader(actionItemsSheet, actionsColumnNames);
			rowNum = 1;
			for (final ActionItem actionItem : actionItems) {
				//        create the row data
				final HSSFRow row = actionItemsSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(actionItem.getId());
				row.createCell(1).setCellValue(actionItem.getSummary());
				row.createCell(2).setCellValue(actionItem.getAssignedTo().getName());
				row.createCell(3).setCellValue(actionItem.getStatus());
			}
		}
		
		collection = (Collection) stringObjectMap.get("tasks");
		if(collection != null) {
			final HSSFSheet tasksSheet = hssfWorkbook.createSheet("Tasks");
			final List<Task> tasks = new ArrayList<Task>(collection);
			final String[] tasksColumnNames = { "Id", "Description", "Created By", "Assgined To" , "Due Date" ,"Status" };
			buildHeader(tasksSheet, tasksColumnNames);
			rowNum = 1;
			for (final Task task : tasks) {
				//        create the row data
				final HSSFRow row = tasksSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(task.getId());
				row.createCell(1).setCellValue(task.getDescription());
				row.createCell(2).setCellValue(task.getCreatedBy().getName());
				row.createCell(3).setCellValue(task.getAssignedTo().getName());
				row.createCell(4).setCellValue(task.getDueDate());
				row.createCell(5).setCellValue(task.getStatus());
			}
		}
		
		collection = (Collection) stringObjectMap.get("requirements");
		if(collection!=null) {
			final HSSFSheet requirementsSheet = hssfWorkbook.createSheet("Requirements");
			final List<Requirement> requirements = new ArrayList<Requirement>(collection);
			final String[] requirementsColumnNames = { "Statement", "Id", "Type", "RiskLevel", "Priority"};
			buildHeader(requirementsSheet, requirementsColumnNames);
			rowNum = 1;
			for (final Requirement requirement : requirements) {
				//        create the row data
				final HSSFRow row = requirementsSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(requirement.getDescription());
				row.createCell(1).setCellValue(requirement.getId());
				row.createCell(2).setCellValue(requirement.getType());
				row.createCell(3).setCellValue(requirement.getRiskLevel());
				row.createCell(4).setCellValue(requirement.getPriority());
			}
		}
		
		collection = (Collection) stringObjectMap.get("lessonsLearned");
		if(collection != null) {
			final HSSFSheet lessonsLearnedSheet = hssfWorkbook.createSheet("LessonsLearned");
			final List<LessonsLearned> lessonsLearned = new ArrayList<LessonsLearned>(collection);
			final String[] lessonsLearnedColumnNames = { "Id", "Summary"};
			buildHeader(lessonsLearnedSheet, lessonsLearnedColumnNames);
			rowNum = 1;
			for (final LessonsLearned lesson : lessonsLearned) {
				//create the row data
				final HSSFRow row = lessonsLearnedSheet.createRow(rowNum++);
				row.createCell(0).setCellValue(lesson.getId());
				row.createCell(1).setCellValue(lesson.getSummary());
			}
		}
		
		final String fileName = "ProjectReport.xls";
		httpServletResponse.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		httpServletResponse.setContentType("application/vnd.ms-excel");

		ExcelWriter.write(httpServletResponse, hssfWorkbook);
	}


	private void buildHeader(final HSSFSheet sheet, final String[] colNames)
	{
		// Create cell style for the headers
		final HSSFCellStyle headerCellStyle = Layouter.createHeaderCellStyle(sheet);
		final HSSFRow header =  sheet.createRow(0);
		for(int i=0; i<colNames.length;i++)
		{
			final Cell cell = header.createCell(i);
			cell.setCellValue(colNames[i]);
			cell.setCellStyle(headerCellStyle);
		}
	}
}
