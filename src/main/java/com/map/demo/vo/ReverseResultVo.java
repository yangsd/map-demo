package com.map.demo.vo;

/**
 * Created by sdyang on 2016/7/1.
 */
public class ReverseResultVo {

    private int status;//返回结果状态值， 成功返回0

    private ReverseVo result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ReverseVo getResult() {
        return result;
    }

    public void setResult(ReverseVo result) {
        this.result = result;
    }
}
