package io.ex.notice;

import java.lang.reflect.Field;
import java.util.Map;

public class NoticeWithdrawEntity extends NoticeBaseEntity{

    private String id;//提币编号
    private String amount;//金额
    private String commandId;//打币钱包id
    private String currency;//币种
    //private String confirm;//是否已经确认
    private String fees;//请求手续费
    private String manageTime;//打币时间
    private String orderNo;//工单号
    private String realFee;//真实手续费
    private String remark;//备注
    private String status;//状态 1失败，2成功，3取消，4待核实
    private String submitTime;//提交时间
    private String toAddress;//接收地址
    private String txID;//交易号
    private String fromAddress;//发币地址

    @Override
    public StringBuilder getSignUrl(){
        StringBuilder sbl = new StringBuilder();
        sbl.append("?method=").append(method)
                .append("&id=").append(id)
                .append("&amount=").append(amount)
                .append("&commandId=").append(commandId)
                .append("&currency=").append(currency)
                .append("&fees=").append(fees)
                .append("&manageTime=").append(manageTime)
                .append("&orderNo=").append(orderNo)
                .append("&realFee=").append(realFee)
                .append("&remark=").append(remark)
                .append("&status=").append(status)
                .append("&submitTime=").append(submitTime)
                .append("&toAddress=").append(toAddress)
                .append("&txID=").append(txID)
                .append("&fromAddress=").append(fromAddress);
        return sbl;
    }

    public static NoticeWithdrawEntity build(Map<String, String> map){
        try {
            NoticeWithdrawEntity entity = NoticeWithdrawEntity.class.newInstance();

            Field[] fields = NoticeWithdrawEntity.class.getDeclaredFields();

            for(Field field : fields){
                field.set(entity, map.get(field.getName()));
            }
            entity.setMethod(map.get("method"));

            return entity;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getManageTime() {
        return manageTime;
    }

    public void setManageTime(String manageTime) {
        this.manageTime = manageTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRealFee() {
        return realFee;
    }

    public void setRealFee(String realFee) {
        this.realFee = realFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getTxID() {
        if(txID == null){txID = "";}
        return txID;
    }

    public void setTxID(String txID) {
        this.txID = txID;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
}
