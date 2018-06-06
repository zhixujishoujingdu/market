package com.zqx.pojo;

import java.util.Date;

public class OrderItem {
    private Integer id;
    private Long order_no;
    private Integer user_id;
    private Integer product_id;
    private String product_name;
    private String product_image;
    private Double current_price;
    private Integer quantity;
    private Double total_price;
    private Date create_time;
    private Date update_time;

    public OrderItem(Integer id, Long order_no, Integer user_id, Integer product_id, String product_name, String product_image, Double current_price, Integer quantity, Double total_price, Date create_time, Date update_time) {
        this.id = id;
        this.order_no = order_no;
        this.user_id = user_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.current_price = current_price;
        this.quantity = quantity;
        this.total_price = total_price;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public OrderItem() {
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

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public Double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Double current_price) {
        this.current_price = current_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
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
