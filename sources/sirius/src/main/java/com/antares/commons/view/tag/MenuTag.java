package com.antares.commons.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

@SuppressWarnings("serial")
public class MenuTag extends TagSupport {

	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='multi-level'><ul class='menu'>");

		JspWriter writer = pageContext.getOut();
		try {
			writer.print(sb.toString());
		} catch (IOException e) {
			pageContext.setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
			MessageResources messages = MessageResources.getMessageResources("org.apache.struts.util.LocalStrings");
			throw new JspException(messages.getMessage("write.io", e.toString()));
		}

        return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("</ul></div>");

		JspWriter writer = pageContext.getOut();
		try {
			writer.print(sb.toString());
		} catch (IOException e) {
			pageContext.setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
			MessageResources messages = MessageResources.getMessageResources("org.apache.struts.util.LocalStrings");
			throw new JspException(messages.getMessage("write.io", e.toString()));
		}

		return super.doEndTag();
	}

}
