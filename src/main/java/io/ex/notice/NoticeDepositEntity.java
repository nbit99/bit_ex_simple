package io.ex.notice;

import java.lang.reflect.Field;
import java.util.Map;

public class NoticeDepositEntity extends NoticeBaseEntity{

    private String id;//充值编号
    private String isIn;//充值类型
    private String from;//来源地址
    private String to;//目标地址
    private String newAddress;//新编码地址
    private String txId;//交易号
    private String amount;//充币数量
    private String addTime;//添加时间
    private String confirmTime;//确认时间
    private String price;//币价
    private String wallet;//钱包
    private String confirmTimes;//确认次数
    private String remark;//备注
    private String sucConfirm;//是否已成功确认,录入账务信息
    private String currency;//币种
    private String orderNo;//订单号

    public NoticeDepositEntity() {}

    public NoticeDepositEntity(String accessKey) {
        super();
        this.method = "charge";
        this.accessKey = accessKey;
    }

//    public StringBuilder getNotifyUrl(){
//        StringBuilder sbl = new StringBuilder();
//        sbl.append(baseUrl).append(this.getSignUrl());
//
//        return sbl;
//    }

    @Override
    public StringBuilder getSignUrl(){
        StringBuilder sbl = new StringBuilder();
        sbl.append("?method=").append(method)
                .append("&id=").append(id)
                .append("&isIn=").append(isIn)
                .append("&from=").append(from)
                .append("&to=").append(to)
                .append("&newAddress=").append(newAddress)
                .append("&txId=").append(txId)
                .append("&amount=").append(amount)
                .append("&addTime=").append(addTime)
                .append("&confirmTime=").append(confirmTime)
                .append("&price=").append(price)
                .append("&wallet=").append(wallet)
                .append("&confirmTimes=").append(confirmTimes)
                .append("&remark=").append(remark)
                .append("&sucConfirm=").append(sucConfirm)
                .append("&currency=").append(currency)
                .append("&orderNo=").append(orderNo);
        return sbl;
    }

    public static NoticeDepositEntity build(Map<String, String> map){
        try {
            NoticeDepositEntity entity = NoticeDepositEntity.class.newInstance();

            Field[] fields = NoticeDepositEntity.class.getDeclaredFields();

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

    public String getIsIn() {
        return isIn;
    }

    public void setIsIn(String isIn) {
        this.isIn = isIn;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getConfirmTimes() {
        return confirmTimes;
    }

    public void setConfirmTimes(String confirmTimes) {
        this.confirmTimes = confirmTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSucConfirm() {
        return sucConfirm;
    }

    public void setSucConfirm(String sucConfirm) {
        this.sucConfirm = sucConfirm;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }



}
