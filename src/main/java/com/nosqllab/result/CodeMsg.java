package com.nosqllab.result;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:40
 * @Description:
 **/
public class CodeMsg {

    private int code;
    private String msg;

    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg SERVICE_SELECT_OK = new CodeMsg(31, "返回数据");
    public static CodeMsg SERVICE_SELECT_ERROR = new CodeMsg(32, "无数据");
    public static CodeMsg SERVICE_INSERT_OK = new CodeMsg(41, "插入成功");
    public static CodeMsg SERVICE_UPLOAD_ERROR= new CodeMsg(411, "上传失败");
    public static CodeMsg SERVICE_OPENFILE_ERROR= new CodeMsg(421, "打开失败");
    public static CodeMsg SERVICE_INSERT_ERROR= new CodeMsg(42, "插入失败");



    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    /**
     * 返回带参数的错误码
     * @param args
     * @return
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }


}
