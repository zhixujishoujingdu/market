package com.zqx.pojo;

import java.util.Date;

public class Shipping {
    private Integer id;
    private Integer user_id;
    private String receive_name;
    private String receive_phone;
    private String receive_mobile;
    private String receive_province;
    private String receive_city;
    private String receive_district;
    private String receive_zip;
    private Date create_time;
    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getReceive_name() {
        return receive_name;
    }

    public void setReceive_name(String receive_name) {
        this.receive_name = receive_name;
    }

    public String getReceive_phone() {
        return receive_phone;
    }

    public void setReceive_phone(String receive_phone) {
        this.receive_phone = receive_phone;
    }

    public String getReceive_mobile() {
        return receive_mobile;
    }

    public void setReceive_mobile(String receive_mobile) {
        this.receive_mobile = receive_mobile;
    }

    public String getReceive_province() {
        return receive_province;
    }

    public void setReceive_province(String receive_province) {
        this.receive_province = receive_province;
    }

    public String getReceive_city() {
        return receive_city;
    }

    public void setReceive_city(String receive_city) {
        this.receive_city = receive_city;
    }

    public String getReceive_district() {
        return receive_district;
    }

    public void setReceive_district(String receive_district) {
        this.receive_district = receive_district;
    }

    public String getReceive_zip() {
        return receive_zip;
    }

    public void setReceive_zip(String receive_zip) {
        this.receive_zip = receive_zip;
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
