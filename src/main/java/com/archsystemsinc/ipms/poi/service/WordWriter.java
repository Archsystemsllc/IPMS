package com.archsystemsinc.ipms.poi.service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordWriter {

	private static Logger logger = Logger.getLogger("ExcelWriter");

	/**
	 * Writes the report to the output stream
	 */
	public static void write(final HttpServletResponse response,
			final XWPFDocument document) {

		System.out.println("Writing report to the stream");
		try {
			// Retrieve the output stream
			final ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			document.write(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (final Exception e) {
			logger.error("Unable to write report to the output stream");
		}
	}

}
