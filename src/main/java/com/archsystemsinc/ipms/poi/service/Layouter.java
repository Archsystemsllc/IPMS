package com.archsystemsinc.ipms.poi.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import com.archsystemsinc.ipms.sec.util.GenericConstants;

/**
 * Builds the report layout.
 * 
 * @author Abdul Nissar
 */
public class Layouter {

	private static Logger logger = Logger.getLogger("Layouter");

	/**
	 * Builds the report layout.
	 * <p>
	 * This doesn't have any data yet. This is your template.
	 */
	public static void buildReport(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex,
			final String sheetName, final String[] coloumnNames) {
		// Set column widths
		for (short coloumnIndex = 0; coloumnIndex < coloumnNames.length; coloumnIndex++) {
			worksheet.setColumnWidth(coloumnIndex, 5000);
		}
		// Build the title and date headers
		buildTitle(worksheet, startRowIndex, startColIndex);
		// Build the column headers
		buildHeaders(worksheet, startRowIndex, startColIndex, sheetName);
	}

	/**
	 * Builds the report title and the date header
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 */
	public static void buildTitle(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex) {
		// Create font style for the report title
		final Font fontTitle = worksheet.getWorkbook().createFont();
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontTitle.setFontHeight((short) 280);

		// Create cell style for the report title
		final HSSFCellStyle cellStyleTitle = worksheet.getWorkbook()
				.createCellStyle();
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setWrapText(true);
		cellStyleTitle.setFont(fontTitle);

		// Create report title
		final HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
		rowTitle.setHeight((short) 500);
		final HSSFCell cellTitle = rowTitle.createCell(startColIndex);
		cellTitle.setCellValue("Report");
		cellTitle.setCellStyle(cellStyleTitle);

		// Create merged region for the report title
		worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

		// Create date header
		final HSSFRow dateTitle = worksheet
				.createRow((short) startRowIndex + 1);
		final HSSFCell cellDate = dateTitle.createCell(startColIndex);
		cellDate.setCellValue("This report was generated at " + new Date());
	}

	/**
	 * Builds the column headers
	 * 
	 * @param worksheet
	 * @param startRowIndex
	 *            starting row offset
	 * @param startColIndex
	 *            starting column offset
	 */
	public static void buildHeaders(final HSSFSheet worksheet,
			final int startRowIndex, final int startColIndex,
			final String sheetName) {

		// Create cell style for the headers
		final HSSFCellStyle headerCellStyle = createHeaderCellStyle(worksheet);

		// Create the column headers
		final HSSFRow rowHeader = worksheet
				.createRow((short) startRowIndex + 2);
		rowHeader.setHeight((short) 500);

		if (sheetName.equalsIgnoreCase(GenericConstants.ISSUES)) {
			final HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
			cell1.setCellValue("Summary");
			cell1.setCellStyle(headerCellStyle);

			final HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
			cell2.setCellValue("Description");
			cell2.setCellStyle(headerCellStyle);

			final HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
			cell3.setCellValue("Date Reported");
			cell3.setCellStyle(headerCellStyle);

			final HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
			cell4.setCellValue("Priority");
			cell4.setCellStyle(headerCellStyle);

			final HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
			cell5.setCellValue("Status");
			cell5.setCellStyle(headerCellStyle);

			final HSSFCell cell6 = rowHeader.createCell(startColIndex + 5);
			cell6.setCellValue("Due Date");
			cell6.setCellStyle(headerCellStyle);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.PROGRAMS)) {

			final HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
			cell1.setCellValue("Program Name");
			cell1.setCellStyle(headerCellStyle);

			final HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
			cell2.setCellValue("Id");
			cell2.setCellStyle(headerCellStyle);

			final HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
			cell3.setCellValue("Manager");
			cell3.setCellStyle(headerCellStyle);

			final HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
			cell4.setCellValue("# of Issues");
			cell4.setCellStyle(headerCellStyle);

			final HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
			cell5.setCellValue("Start Date");
			cell5.setCellStyle(headerCellStyle);

			final HSSFCell cell6 = rowHeader.createCell(startColIndex + 5);
			cell6.setCellValue("End Date");
			cell6.setCellStyle(headerCellStyle);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.PROJECTS)) {

			final HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
			cell1.setCellValue("Project Name");
			cell1.setCellStyle(headerCellStyle);

			final HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
			cell2.setCellValue("Project Id");
			cell2.setCellStyle(headerCellStyle);

			final HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
			cell3.setCellValue("Project Manager");
			cell3.setCellStyle(headerCellStyle);

			final HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
			cell4.setCellValue("Program");
			cell4.setCellStyle(headerCellStyle);

			final HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
			cell5.setCellValue("# of Issues");
			cell5.setCellStyle(headerCellStyle);

			final HSSFCell cell6 = rowHeader.createCell(startColIndex + 5);
			cell6.setCellValue("Start Date");
			cell6.setCellStyle(headerCellStyle);

			final HSSFCell cell7 = rowHeader.createCell(startColIndex + 6);
			cell7.setCellValue("End Date");
			cell7.setCellStyle(headerCellStyle);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.MEETINGS)) {

			final HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
			cell1.setCellValue("Title");
			cell1.setCellStyle(headerCellStyle);

			final HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
			cell2.setCellValue("Organizer");
			cell2.setCellStyle(headerCellStyle);

			final HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
			cell3.setCellValue("Date");
			cell3.setCellStyle(headerCellStyle);

			final HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
			cell4.setCellValue("Time");
			cell4.setCellStyle(headerCellStyle);

			final HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
			cell5.setCellValue("Duration");
			cell5.setCellStyle(headerCellStyle);

			final HSSFCell cell6 = rowHeader.createCell(startColIndex + 5);
			cell6.setCellValue("Type");
			cell6.setCellStyle(headerCellStyle);

			final HSSFCell cell7 = rowHeader.createCell(startColIndex + 6);
			cell7.setCellValue("Status");
			cell7.setCellStyle(headerCellStyle);
			
			final HSSFCell cell8 = rowHeader.createCell(startColIndex + 7);
			cell8.setCellValue("Project");
			cell8.setCellStyle(headerCellStyle);
			
			final HSSFCell cell9 = rowHeader.createCell(startColIndex + 8);
			cell9.setCellValue("Model");
			cell9.setCellStyle(headerCellStyle);
			
		} else if (sheetName.equalsIgnoreCase(GenericConstants.ACTION_ITEMS)) {

			final HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
			cell1.setCellValue("Summary");
			cell1.setCellStyle(headerCellStyle);

			final HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
			cell2.setCellValue("AssignedTo");
			cell2.setCellStyle(headerCellStyle);

			final HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
			cell3.setCellValue("DueDate");
			cell3.setCellStyle(headerCellStyle);

			final HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
			cell4.setCellValue("Status");
			cell4.setCellStyle(headerCellStyle);

			final HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
			cell5.setCellValue("Priority");
			cell5.setCellStyle(headerCellStyle);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.LESSONS_LEARNED)) {

			final HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
			cell1.setCellValue("Summary");
			cell1.setCellStyle(headerCellStyle);

			final HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
			cell2.setCellValue("Impact");
			cell2.setCellStyle(headerCellStyle);

			final HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
			cell3.setCellValue("Recommendation");
			cell3.setCellStyle(headerCellStyle);

			final HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
			cell4.setCellValue("Success Factors");
			cell4.setCellStyle(headerCellStyle);

			final HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
			cell5.setCellValue("Areas Of Improvement");
			cell5.setCellStyle(headerCellStyle);

			final HSSFCell cell6 = rowHeader.createCell(startColIndex + 5);
			cell6.setCellValue("Isue");
			cell6.setCellStyle(headerCellStyle);
		} else if (sheetName.equalsIgnoreCase(GenericConstants.TASKS)) {

			final HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
			cell1.setCellValue("Name");
			cell1.setCellStyle(headerCellStyle);

			final HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
			cell2.setCellValue("Description");
			cell2.setCellStyle(headerCellStyle);

			final HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
			cell3.setCellValue("Program");
			cell3.setCellStyle(headerCellStyle);

			final HSSFCell cell4 = rowHeader.createCell(startColIndex + 3);
			cell4.setCellValue("Created By");
			cell4.setCellStyle(headerCellStyle);

			final HSSFCell cell5 = rowHeader.createCell(startColIndex + 4);
			cell5.setCellValue("Assigned To");
			cell5.setCellStyle(headerCellStyle);

			final HSSFCell cell6 = rowHeader.createCell(startColIndex + 5);
			cell6.setCellValue("Created Date");
			cell6.setCellStyle(headerCellStyle);
			
			final HSSFCell cell7 = rowHeader.createCell(startColIndex + 6);
			cell7.setCellValue("Due Date");
			cell7.setCellStyle(headerCellStyle);
			
			final HSSFCell cell8 = rowHeader.createCell(startColIndex + 7);
			cell8.setCellValue("Status");
			cell8.setCellStyle(headerCellStyle);
		}
	}

	public static HSSFCellStyle createHeaderCellStyle(final HSSFSheet worksheet)
	{
		final HSSFCellStyle headerCellStyle = worksheet.getWorkbook()
				.createCellStyle();
		// Create font style for the headers
		final Font font = worksheet.getWorkbook().createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		return headerCellStyle;
	}
}