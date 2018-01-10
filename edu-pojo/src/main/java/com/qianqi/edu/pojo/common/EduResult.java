package com.qianqi.edu.pojo.common;

import java.io.Serializable;

public class EduResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int state;
	private String msg;
	private Object data;
	
	
	public static EduResult ok(String msg, Object data)
	{
		return new EduResult(200,msg,data);
	}
	public static EduResult err(String msg, Object data)
	{
		return new EduResult(400,msg,data);
	}
	public static EduResult build(int state,String msg, Object data)
	{
		return new EduResult(state,msg,data);
	}
	public EduResult()
	{
		
	}
	public EduResult(int state, String msg, Object data) {
		super();
		this.state = state;
		this.msg = msg;
		this.data = data;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
