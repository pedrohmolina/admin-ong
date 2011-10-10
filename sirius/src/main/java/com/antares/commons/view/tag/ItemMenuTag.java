package com.antares.commons.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.displaytag.tags.el.ExpressionEvaluator;

import com.antares.commons.util.Utils;

@SuppressWarnings("serial")
public class ItemMenuTag extends TagSupport {

	private String label;
	private String labelKey;
	private String style;
	private String top;

	public int doStartTag() throws JspException {

		ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);
		String fstyle = "";
		if (style != null && !"".equals(style)) {
			fstyle = " style='" + eval.evalString("style", style) + "'";
		}

		StringBuffer sb = new StringBuffer();
		if (top != null && "true".equals(top)) {
			sb.append("<li class='top' " + fstyle + "><a " + fstyle + " href='");
		} else {
			sb.append("<li " + fstyle + "><a " + fstyle + " href='");
		}

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
		ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);

		StringBuffer sb = new StringBuffer();

		if (top != null && "true".equals(top)) {
			sb.append("' id='home' class='top_link'><span>" + eval.evalString("label", resolveLabel()) + "</span></a></li>");
		} else {
			sb.append("'>" + eval.evalString("label", resolveLabel()) + "</a></li>");
		}

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

	private String resolveLabel() {
		String label = "";
		if (labelKey != null) {
			label = Utils.getMessage(labelKey); 
		} else if (label != null) {
			label = this.label;
		}
		return label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getLabelKey() {
		return labelKey;
	}

	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}

}
