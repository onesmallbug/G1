package ToolClasses;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class MyRequest extends HttpServletRequestWrapper {

	private Map<String, String> myParameter = new HashMap<String, String>();
	private HttpServletRequest request;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	public String getParameter(String paramName) {
		String value = null;

		if (myParameter.containsKey(paramName)) {
			value = myParameter.get(paramName);
		} else {
			value = request.getParameter(paramName);
		}
		
		return value;
	}

	public void setMyParameter(String key, String value) {

		myParameter.put(key, value);

	}

}