package com.nosqllab.exception;


import com.nosqllab.result.CodeMsg;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:40
 * @Description:
 **/
public class GlobalException extends RuntimeException {

    private static final long servialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
