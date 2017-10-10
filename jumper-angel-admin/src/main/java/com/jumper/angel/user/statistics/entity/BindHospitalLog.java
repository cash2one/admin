package com.jumper.angel.user.statistics.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BindHospitalLog {
    private Long id;

    private Long userId;

    private Long hospitalId;

    private Integer oprationStatus;

    private String mobileIp;

    private BigDecimal lng;

    private BigDecimal lat;

    private String mobileMac;

    private String versionName;

    private Integer mobileType;

    private String cause;

    private Integer firstBinding;

    private Date bindingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getOprationStatus() {
        return oprationStatus;
    }

    public void setOprationStatus(Integer oprationStatus) {
        this.oprationStatus = oprationStatus;
    }

    public String getMobileIp() {
        return mobileIp;
    }

    public void setMobileIp(String mobileIp) {
        this.mobileIp = mobileIp == null ? null : mobileIp.trim();
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getMobileMac() {
        return mobileMac;
    }

    public void setMobileMac(String mobileMac) {
        this.mobileMac = mobileMac == null ? null : mobileMac.trim();
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public Integer getMobileType() {
        return mobileType;
    }

    public void setMobileType(Integer mobileType) {
        this.mobileType = mobileType;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Integer getFirstBinding() {
        return firstBinding;
    }

    public void setFirstBinding(Integer firstBinding) {
        this.firstBinding = firstBinding;
    }

    public Date getBindingDate() {
        return bindingDate;
    }

    public void setBindingDate(Date bindingDate) {
        this.bindingDate = bindingDate;
    }
}