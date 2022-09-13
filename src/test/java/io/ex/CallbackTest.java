package io.ex;

import io.ex.notice.utils.encrypt.sha.Sha256Util;
import org.junit.jupiter.api.Test;

public class CallbackTest {

    @Test
    public void testPassphrase(){
        String passphrase = "12345678qwertyui";
        String apiKey = "7cd960272c664024bcfe178ff8d6d8a2";
        String shaPass = Sha256Util.getSHA256(passphrase + "_" + apiKey);

        System.out.println("sha256 passphrase:" + shaPass);
    }

    @Test
    public void testDepositCallback(){
        try {
            String signUrl = "";

            String timestamp = String.valueOf(System.currentTimeMillis());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(signUrl).append("&timestamp=").append(timestamp);

//            CallbackInfo callbackInfo = apiV2CallBackBiz.getSign(stringBuilder.toString(), Integer.parseInt(notifyRecord.getUserId()));
//
//            StringBuilder callbackUrl = new StringBuilder();
//            callbackUrl.append(callbackInfo.getUrl()).append(notifyRecord.getUrl());
//            Map<String, String> headers = new HashMap<>();
//            headers.put(HttpHeadersEnum.ACCESS_KEY.header(), callbackInfo.getCallKey());
//            headers.put(HttpHeadersEnum.ACCESS_TIMESTAMP.header(), timestamp);
//            headers.put(HttpHeadersEnum.ACCESS_SIGN.header(), callbackInfo.getSign());
//            headers.put(HttpHeadersEnum.ACCESS_PASSPHRASE.header(), callbackInfo.getPassphrase());
//
//            /** 采用 httpClient */
//            SimpleHttpParam param = new SimpleHttpParam(callbackUrl.toString());
//            param.setHeaders(headers);
//            param.setMethod(SimpleHttpUtils.HTTP_METHOD_POST);
//            param.setReadTimeout(10000);
//            param.setConnectTimeout(10000);
//            SimpleHttpResult result = SimpleHttpUtils.httpRequest(param);
//
//            notifyRecord.setNotifyTimes(notifyTimes + 1);
//            String successValue = notifyParam.getSuccessValue();
//
//            String responseMsg = "";
//            Integer responseStatus = result.getStatusCode();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
