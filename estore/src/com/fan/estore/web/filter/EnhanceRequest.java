package com.fan.estore.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//这里没有用
//增强request的getParameter方法
public class EnhanceRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;

	public EnhanceRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	//对getParaameter增强
	@Override
	public String getParameter(String name) {
		String parameter = request.getParameter(name);//乱码
		try {
			parameter = new String(parameter.getBytes("iso8859-1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameter;
	}
}
