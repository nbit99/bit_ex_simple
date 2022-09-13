package io.ex.controller;

import io.ex.entity.BaseResponse;
import io.ex.entity.ResponseCode;
import io.ex.notice.*;
import io.ex.notice.utils.encrypt.ecdsa.ECDSABase64Utils;
import io.ex.notice.utils.encrypt.sha.Sha256Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {

    private static final Log log = LogFactory.getLog(SystemController.class);

    private static final String apiKey = "7cd960272c664024bcfe178ff8d6d8a2";

    private static final String sha256Passphrase = "bf635d3c33bdbeeb552ddf3cc669b6887c11303086917dea223be8cfcc907fdc";

    private static final String publicKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEFfVaHiNFon/86NVJMd/6dapRHb7KCyLbRyjfBCxVeSqV3I4WeJ+m1Ftgneu/48p21U6uEdsCvoDK6qarV86wcA==";

    private final static BaseResponse baseFailResponse = new BaseResponse(ResponseCode.FAIL);
    private final static BaseResponse baseSuccessResponse = new BaseResponse(ResponseCode.SUCCESS);


    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse callback(@RequestParam Map<String,String> params, @RequestHeader Map<String, String> headers){
        for(Map.Entry<String, String> entity : params.entrySet()){
            log.debug("param key：" + entity.getKey() + ",value:" + entity.getValue());
        }
        for(Map.Entry<String, String> entity : headers.entrySet()){
            log.debug("header key：" + entity.getKey() + ",value:" + entity.getValue());
        }

        String sign = headers.get(HttpHeadersEnum.ACCESS_SIGN.header());
        String timestamp = headers.get(HttpHeadersEnum.ACCESS_TIMESTAMP.header());
        String passphrase = headers.get(HttpHeadersEnum.ACCESS_PASSPHRASE.header());

        if(!sha256Passphrase.equals(Sha256Util.getSHA256(passphrase + "_" + apiKey))){
            log.error("passphrase error!");
            return baseFailResponse;
        }

        log.info("sign：" + sign + ",timestamp:" + timestamp);

        String method = params.get(APIConstants.METHOD);

        NoticeBaseEntity entity = this.getEntityByParameters(params);

        if(entity == null){
            log.error("entity convert error!");
            return baseFailResponse;
        }
        StringBuilder signUrl = entity.getSignUrl().append("&").append(APIConstants.timestamp_key).append("=").append(timestamp);

        log.info("sign url:" + signUrl);

        try {
            boolean result = ECDSABase64Utils.verify(publicKey, sign, signUrl.toString());
            if(!result) {
                return baseFailResponse;
            }
            if(method.equalsIgnoreCase(APIConstants.CHARGE_METHOD)){
                NoticeDepositEntity depositEntity = (NoticeDepositEntity) entity;
                log.info("verify sign success，id:" + depositEntity.getId() + ",currency:" + depositEntity.getCurrency() + ",amount:" + depositEntity.getAmount());
                //开始成功处理代码
                //......
                //结束成功处理代码
                return baseSuccessResponse;
            }else if(method.equalsIgnoreCase(APIConstants.WITHDRAW_METHOD)){
                NoticeWithdrawEntity withdrawEntity = (NoticeWithdrawEntity) entity;
                log.info("verify sign success，id:" + withdrawEntity.getId() + ",currency:" + withdrawEntity.getCurrency() + ",amount:" + withdrawEntity.getAmount());
                //开始成功处理代码
                //......
                //结束成功处理代码
                return baseSuccessResponse;
            }else if(method.equalsIgnoreCase(APIConstants.WITHDRAW_CONFIRM_METHOD)){
                NoticeWithdrawEntity withdrawEntity = (NoticeWithdrawEntity) entity;
                log.info("verify sign success，id:" + withdrawEntity.getId() + ",currency:" + withdrawEntity.getCurrency() + ",amount:" + withdrawEntity.getAmount());
                //开始成功处理代码
                //......
                //结束成功处理代码
                return baseSuccessResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baseFailResponse;
    }

    private NoticeBaseEntity getEntityByParameters(Map<String,String> params){
        String method = params.get(APIConstants.METHOD);
        NoticeBaseEntity entity = null;
        if(method.equalsIgnoreCase(APIConstants.CHARGE_METHOD)){
            entity = NoticeDepositEntity.build(params);
        }else if(method.equalsIgnoreCase(APIConstants.WITHDRAW_METHOD)){
            entity = NoticeWithdrawEntity.build(params);
        }else if(method.equalsIgnoreCase(APIConstants.WITHDRAW_CONFIRM_METHOD)){
            entity = NoticeWithdrawEntity.build(params);
        }
        return entity;
    }

    @RequestMapping("/p1")
    public String page1(ModelMap modelMap){
        modelMap.put("k1", "v001");
        return "p1";
    }
}
