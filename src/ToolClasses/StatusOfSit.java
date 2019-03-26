package ToolClasses;

public class StatusOfSit{
	private String str;
	private String coller;
	private Integer type;
	
	public StatusOfSit(String str, String coller, Integer type){
		this.str = str;
		this.coller = coller;
		this.type = type;
	}

	public String getStr() {
		return str;
	}

	public String getColler() {
		return coller;
	}

	public Integer getType() {
		return type;
	}

}