package io.ex.controller;

import io.ex.entity.BaseResponse;
import io.ex.entity.ResponseCode;
import io.ex.notice.*;
import io.ex.notice.config.ApiCallbackConfig;
import io.ex.notice.utils.v1.DesUtil;
import io.ex.notice.utils.v1.EncryDigestUtil;
import io.ex.notice.utils.v1.MapSort;
import io.ex.notice.utils.ip.IpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/***
 * v1版本回调案例，v1版本后续版本会废除不用
 */
@Controller
@RequestMapping("/bitbank/v1")
public class SystemV1Controller extends ApiCallbackConfig {

    private static final Log log = LogFactory.getLog(SystemV1Controller.class);
    //apiSecret(商户secret): 03a6e043-fa0b-40b5-9a8d-56efce99fe37
    //desSecret(desSecret): 48ed1594-2643-4ee0-aff8-298c2993a3ed
    //申请api callback生成下列密钥对
    private static final String MERCHANTS_API_SECRET = "03a6e043-fa0b-40b5-9a8d-56efce99fe37";
    private static final String MERCHANTS_DES_KEY = "48ed1594-2643-4ee0-aff8-298c2993a3ed";
    //IP白名单
    private static final List<String> WHITE_IPS = Arrays.asList("127.0.0.1", "192.168.1.104");

    private final static BaseResponse baseFailResponse = new BaseResponse(ResponseCode.FAIL);
    private final static BaseResponse baseSuccessResponse = new BaseResponse(ResponseCode.SUCCESS);


    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse callback(@RequestParam Map<String,String> params, @RequestHeader Map<String, String> headers, HttpServletRequest request){
        String ip = IpUtil.getIp(request);
        if(!WHITE_IPS.contains(ip)){
            log.error("Non-whitelisted IP address：" + ip);
            baseFailResponse.setMsg("Non-whitelisted IP address：" + ip);
            return baseFailResponse;
        }

        String method = params.get(APIConstants.METHOD);

        try {
            if(method.equalsIgnoreCase(APIConstants.CHARGE_METHOD)){
                String[] jiamiArr = new String[]{"accesskey", "method", "parameters"};
                Map<String,String> maps = new HashMap<String, String>();
                for(int i=0;i<jiamiArr.length;i++){
                    System.out.println("charge获取参数名：" + jiamiArr[i] + "\t内容：" + request.getParameter(jiamiArr[i]));
                    maps.put( jiamiArr[i], params.get(jiamiArr[i]) );
                }

                String paramString = toStringMap(maps);
                log.info("charge获取的params：" + paramString);

                String desSecrect = DesUtil.encrypt(MERCHANTS_API_SECRET, MERCHANTS_DES_KEY);
                log.info("charge的desSecrect:" + desSecrect);
                String screct = EncryDigestUtil.digest(desSecrect);
                String signMyself = EncryDigestUtil.hmacSign(paramString, screct);
                System.out.println("charge操作，接收到的参数加密的sign:" + signMyself);

                String sign = request.getParameter("sign");
                System.out.println("charge收到的sign:" + sign);

                int size = 0;
                String successIds = "";
                if(sign!=null && sign.equals(signMyself)){
                    //开始成功处理代码
                    //......
                    //结束成功处理代码
                    return baseSuccessResponse;
                }
            }else if(method.equalsIgnoreCase("download")){
                String sign = request.getParameter("sign");
                System.out.println("download收到的sign:" + sign);

                String[] jiamiArr = new String[]{"accesskey", "amount", "btcDownloadId", "commandId", "currency",
                        "confirm", "fees", "freezeId", "isDel", "managerName", "managerId",
                        "manageTime", "method", "orderNo", "partner_id", "payFee", "realFee", "remark",
                        "status", "submitTime", "toAddress", "userName"};
                Map<String,String> maps = new HashMap<String, String>();
                for(int i=0;i<jiamiArr.length;i++){
                    System.out.println("download获取参数名：" + jiamiArr[i] + "\t内容：" + request.getParameter(jiamiArr[i]));
                    maps.put( jiamiArr[i], request.getParameter(jiamiArr[i]) );
                }

                String paramString = toStringMap(maps);
                log.info("download获取的params：" + paramString);

                String desSecrect = DesUtil.encrypt(MERCHANTS_API_SECRET, MERCHANTS_DES_KEY);
                log.info("download的desSecrect:" + desSecrect);
                String screct = EncryDigestUtil.digest(desSecrect);
                String signMyself = EncryDigestUtil.hmacSign(paramString, screct);
                System.out.println("download提现操作，接收到的参数加密的sign:" + signMyself);

                if(sign!=null && sign.equals(signMyself)) { //签名验证通过
                    //开始成功处理代码
                    //......
                    //结束成功处理代码
                    return baseSuccessResponse;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baseFailResponse;
    }

    private NoticeBaseEntity getEntityByParameters(Map<String,String> params){
        String method = params.get(APIConstants.METHOD);
        if(method == null){
            log.error("param method is null");
            return null;
        }
        NoticeBaseEntity entity = null;
        if(method.equalsIgnoreCase(APIConstants.CHARGE_METHOD)){
            entity = NoticeDepositEntity.build(params);
        }else if(method.equalsIgnoreCase(APIConstants.WITHDRAW_SUCCESS_METHOD)){
            entity = NoticeWithdrawEntity.build(params);
        }else if(method.equalsIgnoreCase(APIConstants.WITHDRAW_CONFIRM_METHOD)){
            entity = NoticeWithdrawEntity.build(params);
        }
        return entity;
    }

    public String toStringMap(Map m){
        //按map键首字母顺序进行排序
        m = MapSort.sortMapByKey(m);

        StringBuilder sbl = new StringBuilder();
        for(Iterator<Map.Entry> i = m.entrySet().iterator(); i.hasNext();){
            Map.Entry e = i.next();
            Object o = e.getValue();
            String v = "";
            if(o == null){
                v = "";
            }else if(o instanceof String[]) {
                String[] s = (String[]) o;
                if(s.length > 0){
                    v = s[0];
                }
            }else{
                v=o.toString();
            }
            if(!e.getKey().equals("sign") && !e.getKey().equals("reqTime") && !e.getKey().equals("tx"))
//				try {
//					sbl.append("&").append(e.getKey()).append("=").append(URLEncoder.encode(v, "utf-8"));
//				} catch (UnsupportedEncodingException e1) {
//					e1.printStackTrace();
                sbl.append("&").append(e.getKey()).append("=").append(v);
//				}
        }
        String s = sbl.toString();
        if(s.length()>0){
            return s.substring(1);
        }
        return "";
    }
}
