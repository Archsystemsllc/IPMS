/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.web.common.customtag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.archsystemsinc.ipms.sec.util.SecurityUtil;

/**
* The Custom Tag to Authorize the hyperlink to display
* @author Martin
* @version 0.2.1
*/
public class AnchorTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * The Hyperlink to display
	 */
	private String href;
	
	/**
	 * The css class
	 */
	private String cls;

	@Override
	public int doStartTag() {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() {
		if (SecurityUtil.hasPermission(href)) {
			try {
				JspWriter out = getBodyContent().getEnclosingWriter();
				out.print("<a");
				addAttribute(out, "href", href);
				addAttribute(out, "class", cls);
				out.print(">");
				out.print(getBodyContent().getString());
				out.println("</a>");
			} catch (IOException exp) {
				throw new RuntimeException(exp);
			}
		}
		return EVAL_PAGE;
	}
	/**
	 * Helper method to write the attributes on the hyperlink tag
	 * @param out The out object where it should print
	 * @param key The Key
	 * @param value The Value
	 * @throws IOException
	 */
	private void addAttribute(JspWriter out, String key, String value) throws IOException {
		if (value != null)
			out.print(" " + key + "=\"" + value + "\"");
	}

	/**
	 * Sets the value fo href
	 * @param href
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/** 
	 * Sets the value for css class
	 * @param cls The css class to set
	 */
	public void setClass(String cls) {
		this.cls = cls;
	}

}
