package io.ex.notice;

public class NoticeBaseEntity {

//    protected String baseUrl;
    protected String callKey;//api key
    protected String method;//callback api method
    protected String sign;//消息签名

    public String getCallKey() {
        return callKey;
    }

    public void setCallKey(String callKey) {
        this.callKey = callKey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public StringBuilder getSignUrl(){
        return null;
    }
}
