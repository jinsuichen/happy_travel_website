package xyz.chenjinsui.travel.domain;

import java.io.Serializable;

/**
 * @author FengLing
 */
public class ResultInfo implements Serializable {

    /**
     * 后端返回结果正常为true，不正常为false
     */
    private boolean flag;


    /**
     * 后端返回的信息
     */
    private Object data;


    /**
     * 发生异常的错误信息
     */
    private String errorMsg;


    // 无参构造
    public ResultInfo(){
    }

    public ResultInfo(boolean flag){this.flag = flag;}



    public ResultInfo(boolean flag, String errorMsg){
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    //TODO 完善构造器
}
