package com.archsystemsinc.ipms.poi.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;

import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.LessonsLearned;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Requirement;
import com.archsystemsinc.ipms.sec.model.Risk;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.util.GenericConstants;

public class FillManager {

	/**
	 * Fills the report with content
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 * @param datasource
	 *            the data source
	 */
	public static void fillReport(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {

		if (sheetName.equalsIgnoreCase(GenericConstants.ISSUES)) {
			fillIssuesReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.RISKS)) {
			fillRisksReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.PROGRAMS)) {
			fillProgramsReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);

		} else if (sheetName.equalsIgnoreCase(GenericConstants.PROJECTS)) {
			fillProjectsReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);

		} else if (sheetName.equalsIgnoreCase(GenericConstants.MEETINGS)) {
			fillMeetingsReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);

		} else if (sheetName.equalsIgnoreCase(GenericConstants.ACTION_ITEMS)) {
			fillActionItemsReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.LESSONS_LEARNED)) {
			fillLessonsLearnedReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.TASKS)) {
			fillTasksReport(worksheet, startRowIndex, startColIndex,
					datasource, sheetName);
		}
	}

	public static void fillTasksReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<Task> taskList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < taskList.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(taskList.get(i - 2).getId());
			cell3.setCellStyle(bodyCellTextStyle);

			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(taskList.get(i - 2).getDescription());
			cell2.setCellStyle(bodyCellTextStyle);

			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(taskList.get(i - 2).getCreatedBy().getName());
			cell4.setCellStyle(bodyCellTextStyle);
			
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(taskList.get(i - 2).getAssignedTo().getName());
			cell5.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell7 = row.createCell(startColIndex + 6);
			cell7.setCellValue(taskList.get(i - 2).getDueDate());
			cell7.setCellStyle(bodyCellDateStyle);

			// Retrieve the Due Date value
			final HSSFCell cell8 = row.createCell(startColIndex + 7);
			cell8.setCellValue(taskList.get(i - 2).getStatus());
			cell8.setCellStyle(bodyCellTextStyle);
		}
	}

	public static void fillLessonsLearnedReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<LessonsLearned> lessonsLearnedList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < lessonsLearnedList
				.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the Summary value
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(lessonsLearnedList.get(i - 2).getSummary());
			cell1.setCellStyle(bodyCellTextStyle);

			// Retrieve the Description value
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(lessonsLearnedList.get(i - 2).getImpact());
			cell2.setCellStyle(bodyCellTextStyle);

			// Retrieve the Date Reported value
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(lessonsLearnedList.get(i - 2)
					.getRecommendation());
			cell3.setCellStyle(bodyCellTextStyle);

			// Retrieve the Priority value
			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(lessonsLearnedList.get(i - 2)
					.getSuccessFactors());
			cell4.setCellStyle(bodyCellTextStyle);

			// Retrieve the Status value
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(lessonsLearnedList.get(i - 2)
					.getAreasOfImprovement());
			cell5.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell6 = row.createCell(startColIndex + 5);
			cell6.setCellValue(lessonsLearnedList.get(i - 2).getIssue()
					.getName());
			cell6.setCellStyle(bodyCellTextStyle);
		}
	}

	public static void fillActionItemsReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<ActionItem> actionItemsList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < actionItemsList
				.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the Summary value
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(actionItemsList.get(i - 2).getSummary());
			cell1.setCellStyle(bodyCellTextStyle);

			// Retrieve the Assigned To value
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(actionItemsList.get(i - 2).getAssignedTo()
					.getName());
			cell2.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(actionItemsList.get(i - 2).getDueDate());
			cell3.setCellStyle(bodyCellDateStyle);

			// Retrieve the Status value
			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(actionItemsList.get(i - 2).getStatus());
			cell4.setCellStyle(bodyCellTextStyle);

			// Retrieve the Priority value
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(actionItemsList.get(i - 2).getPriority());
			cell5.setCellStyle(bodyCellTextStyle);
		}
	}

	public static void fillIssuesReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<Issue> issuesList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < issuesList.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the Summary value
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(issuesList.get(i - 2).getSummary());
			cell1.setCellStyle(bodyCellTextStyle);

			// Retrieve the Description value
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(issuesList.get(i - 2).getDescription());
			cell2.setCellStyle(bodyCellTextStyle);

			// Retrieve the Date Reported value
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(issuesList.get(i - 2).getDateReported());
			cell3.setCellStyle(bodyCellDateStyle);

			// Retrieve the Priority value
			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(issuesList.get(i - 2).getPriority());
			cell4.setCellStyle(bodyCellTextStyle);

			// Retrieve the Status value
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(issuesList.get(i - 2).getStatus());
			cell5.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell6 = row.createCell(startColIndex + 5);
			cell6.setCellValue(issuesList.get(i - 2).getDueDate());
			cell6.setCellStyle(bodyCellDateStyle);
		}
	}
	
	public static void fillRequirementsReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<Requirement> requirementsList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < requirementsList.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(requirementsList.get(i - 2).getSummary());
			cell1.setCellStyle(bodyCellTextStyle);

			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(requirementsList.get(i - 2).getId());
			cell2.setCellStyle(bodyCellTextStyle);

			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(requirementsList.get(i - 2).getType());
			cell3.setCellStyle(bodyCellDateStyle);

			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(requirementsList.get(i - 2).getRiskLevel());
			cell4.setCellStyle(bodyCellTextStyle);

			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(requirementsList.get(i - 2).getPriority());
			cell5.setCellStyle(bodyCellTextStyle);

		}
	}

	public static void fillProgramsReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<Program> programList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < programList.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the Summary value
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(programList.get(i - 2).getName());
			cell1.setCellStyle(bodyCellTextStyle);

			// Retrieve the Description value
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(programList.get(i - 2).getId());
			cell2.setCellStyle(bodyCellTextStyle);

			// Retrieve the Date Reported value
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(programList.get(i - 2).getManager().getName());
			cell3.setCellStyle(bodyCellDateStyle);

			// Retrieve the Priority value
			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(programList.get(i - 2).getIssues().size());
			cell4.setCellStyle(bodyCellTextStyle);

			// Retrieve the Status value
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(programList.get(i - 2).getStartDate());
			cell5.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell6 = row.createCell(startColIndex + 5);
			cell6.setCellValue(programList.get(i - 2).getEndDate());
			cell6.setCellStyle(bodyCellDateStyle);
		}
	}

	public static void fillProjectsReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<Project> projectList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < projectList.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the Summary value
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(projectList.get(i - 2).getName());
			cell1.setCellStyle(bodyCellTextStyle);

			// Retrieve the Description value
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(projectList.get(i - 2).getId());
			cell2.setCellStyle(bodyCellTextStyle);

			// Retrieve the Date Reported value
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(projectList.get(i - 2).getManager().getName());
			cell3.setCellStyle(bodyCellDateStyle);

			// Retrieve the Priority value
			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(projectList.get(i - 2).getProgram().getName());
			cell4.setCellStyle(bodyCellTextStyle);

			// Retrieve the Status value
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(projectList.get(i - 2).getIssues().size());
			cell5.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell6 = row.createCell(startColIndex + 5);
			if(projectList.get(i - 2).getStartDate()!=null){
				cell6.setCellValue(projectList.get(i - 2).getStartDate());
			} else {
				cell6.setCellValue("");
			}
			cell6.setCellStyle(bodyCellDateStyle);

			// Retrieve the Due Date value
			final HSSFCell cell7 = row.createCell(startColIndex + 6);
			if(projectList.get(i - 2).getEndDate()!=null){
			cell7.setCellValue(projectList.get(i - 2).getEndDate());
			}else{
				cell7.setCellValue("");
			}
			cell7.setCellStyle(bodyCellDateStyle);
		}
	}

	public static void fillMeetingsReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<Meeting> meetingList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < meetingList.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the Summary value
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
			cell1.setCellValue(meetingList.get(i - 2).getTitle());
			cell1.setCellStyle(bodyCellTextStyle);

			// Retrieve the Description value
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(meetingList.get(i - 2).getOrganizer().getName());
			cell2.setCellStyle(bodyCellTextStyle);

			// Retrieve the Date Reported value
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(meetingList.get(i - 2).getDate());
			cell3.setCellStyle(bodyCellDateStyle);

			// Retrieve the Priority value
			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(meetingList.get(i - 2).getTime());
			cell4.setCellStyle(bodyCellTextStyle);

			// Retrieve the Status value
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(meetingList.get(i - 2).getDuration());
			cell5.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell6 = row.createCell(startColIndex + 5);
			cell6.setCellValue(meetingList.get(i - 2).getTypeString());
			cell6.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell7 = row.createCell(startColIndex + 6);
			cell7.setCellValue(meetingList.get(i - 2).getStatus());
			cell7.setCellStyle(bodyCellTextStyle);

			// Retrieve the Project Name value
			final HSSFCell cell8 = row.createCell(startColIndex + 7);
			if(meetingList.get(i - 2).getProject() != null && meetingList.get(i - 2).getProject().getName() != null) {
				cell8.setCellValue(meetingList.get(i - 2).getProject().getName());
			} else {
				cell8.setCellValue("");
			}
			cell8.setCellStyle(bodyCellTextStyle);
			
			// Retrieve the Model name value
			final HSSFCell cell9 = row.createCell(startColIndex + 8);
			if(meetingList.get(i - 2).getProgram() != null && meetingList.get(i - 2).getProgram().getName() != null) {
				cell9.setCellValue(meetingList.get(i - 2).getProgram().getName());
			} else {
				cell9.setCellValue("");
			}
			cell9.setCellStyle(bodyCellTextStyle);
		}
	}
	

	public static void fillRisksReport(final HSSFSheet worksheet,
			int startRowIndex, final int startColIndex,
			final List<Object> datasource, final String sheetName) {
		// Row offset
		startRowIndex += 2;
		final List<Risk> riskList = (List) datasource;
		// Create cell style for the body
		final HSSFCellStyle bodyCellTextStyle = worksheet.getWorkbook()
				.createCellStyle();
		final HSSFCellStyle bodyCellDateStyle = worksheet.getWorkbook()
				.createCellStyle();

		final CreationHelper createHelper = worksheet.getWorkbook()
				.getCreationHelper();

		bodyCellTextStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellTextStyle.setWrapText(true);

		bodyCellDateStyle.setDataFormat(createHelper.createDataFormat()
				.getFormat("m/d/yy"));

		// Create body
		for (int i = startRowIndex; i + startRowIndex - 2 < riskList.size() + 2; i++) {
			// Create a new row

			final HSSFRow row = worksheet.createRow((short) i + 1);

			// Retrieve the Summary value
			final HSSFCell cell1 = row.createCell(startColIndex + 0);
		    cell1.setCellValue(riskList.get(i - 2).getRiskSummary());
			cell1.setCellStyle(bodyCellTextStyle);

			// Retrieve the Description value
			final HSSFCell cell2 = row.createCell(startColIndex + 1);
			cell2.setCellValue(riskList.get(i - 2).getId());
			cell2.setCellStyle(bodyCellTextStyle);

			// Retrieve the Date Reported value
			final HSSFCell cell3 = row.createCell(startColIndex + 2);
			cell3.setCellValue(riskList.get(i - 2).getImpact());
			cell3.setCellStyle(bodyCellDateStyle);

			// Retrieve the Priority value
			final HSSFCell cell4 = row.createCell(startColIndex + 3);
			cell4.setCellValue(riskList.get(i - 2).getLikelihood());
			cell4.setCellStyle(bodyCellTextStyle);

			// Retrieve the Status value
			final HSSFCell cell5 = row.createCell(startColIndex + 4);
			cell5.setCellValue(riskList.get(i - 2).getStatus());
			cell5.setCellStyle(bodyCellTextStyle);

			// Retrieve the Due Date value
			final HSSFCell cell6 = row.createCell(startColIndex + 5);
			cell6.setCellValue(riskList.get(i - 2).getName());
			cell6.setCellStyle(bodyCellDateStyle);

	
		}
	}

}
