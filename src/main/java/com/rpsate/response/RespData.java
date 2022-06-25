package com.rpsate.response;

public class RespData {
    private Object data;
    private Integer code;
    private String msg;

    public RespData() {}

    public RespData(Integer code, Object data) {
        this.data = data;
        this.code = code;
        this.msg = RespCodeMsg.MSG.get(code);
    }

    public RespData(Integer code, Object data, String ...msg) {
        this.data = data;
        this.code = code;
        this.msg = String.format(RespCodeMsg.MSG.get(code), msg);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RespData{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
