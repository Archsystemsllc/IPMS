package com.archsystemsinc.ipms.poi.service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Writes the report to the output stream
 * 
 * @author Krams at {@link http://krams915@blogspot.com}
 */
public class ExcelWriter {

	private static Logger logger = Logger.getLogger("ExcelWriter");

	/**
	 * Writes the report to the output stream
	 */
	public static void write(final HttpServletResponse response,
			final HSSFWorkbook workbook) {

		System.out.println("Writing report to the stream");
		try {
			// Retrieve the output stream
			final ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			workbook.write(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (final Exception e) {
			logger.error("Unable to write report to the output stream");
		}
	}
}
