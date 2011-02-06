package com.antares.commons.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.displaytag.tags.el.ExpressionEvaluator;

@SuppressWarnings("serial")
public class SubMenuTag extends TagSupport {

	private String href;
	private String label;
	private String top;
	private String style;

	public int doStartTag() throws JspException {
        ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);

		StringBuffer sb = new StringBuffer();
		String fhref = "#";
		String fstyle = "";
		String ftop1 = "";
		String ftop2 = " class='fly'";
		String ftop3 = "";
		String ftop4 = "";
		String ftop5 = "";
		if (href!=null && !"".equals(href))
			fhref = eval.evalString("href", href);
		if (style!=null && !"".equals(style))
			fstyle = " style='" + eval.evalString("style", style) + "'";
		if (top!=null && "true".equals(top))
		{
			ftop1 = " class='top'";
			ftop2 = " class='top_link'";
			ftop3 = "<span class='down'>";
			ftop4 = "</span>";
			ftop5 = " class='sub'";
		}
		sb.append("<li" + ftop1 + " " + fstyle + "><a " + fstyle + " href='" + fhref + "' id='products'" + ftop2 + ">" + ftop3 + eval.evalString("label", label) + ftop4 + "<!--[if gte IE 7]><!--></a><!--<![endif]--><!--[if lte IE 6]><table><tr><td><![endif]--><ul" + ftop5 + ">");
       
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
		sb.append("</ul><!--[if lte IE 6]></td></tr></table></a><![endif]--></li>");
        
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
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

}
