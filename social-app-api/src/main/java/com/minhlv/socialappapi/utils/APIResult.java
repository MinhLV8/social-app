package com.minhlv.socialappapi.utils;

import java.util.ArrayList;

public class APIResult {

	private int errorCode = 0;
	private String message = "";
	private Object data = null;
	private long timeStart = 0;
	private long timeEnd = 0;
	private long timeTotal = 0;
	public APIResult() {
		this.timeStart = System.currentTimeMillis();
	}

	public APIResult(Object data) {
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		this.timeEnd = System.currentTimeMillis();
		this.timeTotal = timeEnd - timeStart;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		this.timeEnd = System.currentTimeMillis();
		this.timeTotal = timeEnd - timeStart;
		this.data = new ArrayList<>();
	}

	public void setMessage(Exception exeption) {
		this.timeEnd = System.currentTimeMillis();
		this.timeTotal = timeEnd - timeStart;
		this.data = new ArrayList<>();

		this.message = exeption.getMessage();
		this.errorCode = 99;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		this.timeEnd = System.currentTimeMillis();
		this.timeTotal = timeEnd - timeStart;
	}

	public long getTimeTotal() {
		return timeTotal;
	}

	public void setTimeTotal(long timeTotal) {
		this.timeTotal = timeTotal;
	}

	public interface ERROR_CODE {
		int EMPTY_DATA = 1;
		int PARAM_INVALID = 2;
		int CHILD_EXISTS = 3;
		int INSERT_AUTH = 4;
		int SELECT_AUTH = 5;
		int NOT_EXISTS = 6;
		int REF_ILLEGAL = 7;
		int DATABASE_ERROR = 8;
		int DOITUONG_ILLEGAL = 9;
		int EXISTS = 10;
		int NOT_PERMISSTION = 11;
		int UNKNOW_ERROR = 99;
	}
}
