package com.nosqllab.result;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:40
 * @Description:
 **/
public class Result<T> {
	
	private int code;
	private String msg;
	private T data;
	
	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(CodeMsg codeMsg,T data){
		return new Result<T>(codeMsg, data);
	}

	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}

	public static  <T> Result<T> success(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}
	
	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}
	
	private Result(T data) {
		this.data = data;
	}
	
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private Result(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}

	private Result(CodeMsg codeMsg, T data) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
			this.data = data;
		}
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
