package com.zqx.pojo;


import java.util.Date;

public class Order {
    private Integer id;
    private Long order_no;
    private Integer user_id;
    private Integer shipping_id;
    private Double payment;
    private Integer payment_type;
    private Integer postage;
    private Integer status;
    private Date payment_time;//java.sql时间
    private Date send_time;
    private Date end_time;
    private Date close_time;
    private Date create_time;
    private Date update_time;

    public Order(Integer id, Long order_no, Integer user_id, Integer shipping_id, Double payment, Integer payment_type, Integer postage, Integer status, Date payment_time, Date send_time, Date end_time, Date close_time, Date create_time, Date update_time) {
        this.id = id;
        this.order_no = order_no;
        this.user_id = user_id;
        this.shipping_id = shipping_id;
        this.payment = payment;
        this.payment_type = payment_type;
        this.postage = postage;
        this.status = status;
        this.payment_time = payment_time;
        this.send_time = send_time;
        this.end_time = end_time;
        this.close_time = close_time;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Long order_no) {
        this.order_no = order_no;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(Integer shipping_id) {
        this.shipping_id = shipping_id;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Integer getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(Integer payment_type) {
        this.payment_type = payment_type;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
