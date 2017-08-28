package com.archsystemsinc.ipms.sec.util;

public final class GenericConstants {

	/**
	 * Constants <br/>
	 * - Constants Used across the application in various classes
	 */
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	public static final String ISSUES = "Issues";
	public static final String RISKS = "Risks";
	public static final String PROGRAMS = "Programs";
	public static final String PROJECTS = "Projects";
	public static final String MEETINGS = "Meetings";
	public static final String ACTION_ITEMS = "ActionItems";
	public static final String LESSONS_LEARNED = "LessonsLearned";
	public static final String TASKS = "Tasks";
	public static final String REQUIREMENTS = "Requirements";
	public static final String MEETING_MINUTES = "MeetingMinutes";
	
	
	/* Emailer Releated Constants */
	public static final String EMAIL_SMTP_HOST = "ProvideHost";	
	public static final String EMAIL_SMTP_PORT = "25";
	public static final String SMTP_CONNECTION_TIMEOUT = "3000";

	;
	
	private GenericConstants() {
		throw new AssertionError();
	}
}
