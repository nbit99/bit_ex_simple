package io.ex.notice;

public class NoticeBaseEntity {

//    protected String baseUrl;
    protected String accessKey;//api key
    protected String method;//callback api method
    protected String sign;//消息签名

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
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
