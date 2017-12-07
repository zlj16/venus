/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: Store.java
 * Author:   v_xueyinhao01
 * Date:     2013年11月15日 上午11:46:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.meidusa.venus.hello.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * 经销商店铺实体<br>
 * 〈功能详细描述〉
 * 
 * @author v_xueyinhao01
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Store implements Serializable {

    /**
     * 序列化自动生成的ID
     */
    private static final long serialVersionUID = -475697551655796844L;
    /**
     * 店铺id 主键
     */
    private Long storeId;
    /**
     * 经销商id
     */
    private Long dealerId;
    /**
     * 店铺编号
     */
    private String storeCode;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺地址
     */
    private String storeUrl;
    /**
     * 店铺简介
     */
    private String storeDesc;
    /**
     * 店铺负责人
     */
    private String storeOwner;
    /**
     * 开业状态 1正常运营,2已注销
     */
    private Integer storeStatus;
    /**
     * 开业日期
     */
    private Timestamp openningDate;
    /**
     * 停业日期
     */
    private Timestamp closingDate;
    /**
     * 经营品牌id
     */
    private Long brandId;
    /**
     * 经营品牌名称
     */
    private String brandName;
    /**
     * 店铺负责人电话
     */
    private String storeOwnerPhone;
    /**
     * 经销商服务对应 多个用逗号分割 1车载咨询服务,2汽车美容,3整车营销,4二手车回收,5零配件销售,6金融服务
     */
    private String serviceScope;
    /**
     * 富文本id
     */
    private Long rtfId;
    /**
     * 积分
     */
    private Integer point;
    
    /**
     * 初始化数据时的行号
     */
    private Integer row;

    public Store() {

    }

    public Store(Long storeId) {
        this.storeId = storeId;
    }


    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public Integer getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

    public Date getOpenningDate() {
        return openningDate;
    }

    public void setOpenningDate(Timestamp openningDate) {
        this.openningDate = openningDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Timestamp closingDate) {
        this.closingDate = closingDate;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStoreOwnerPhone() {
        return storeOwnerPhone;
    }

    public void setStoreOwnerPhone(String storeOwnerPhone) {
        this.storeOwnerPhone = storeOwnerPhone;
    }

    public String getServiceScope() {
        return serviceScope;
    }

    public void setServiceScope(String serviceScope) {
        this.serviceScope = serviceScope;
    }

    public Long getRtfId() {
        return rtfId;
    }

    public void setRtfId(Long rtfId) {
        this.rtfId = rtfId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
        result = prime * result + ((storeStatus == null) ? 0 : storeStatus.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Store other = (Store) obj;
        if (brandId == null) {
            if (other.brandId != null) {
                return false;
            }
        } else if (!brandId.equals(other.brandId)) {
            return false;
        }
        if (storeStatus == null) {
            if (other.storeStatus != null) {
                return false;
            }
        } else if (!storeStatus.equals(other.storeStatus)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Store [storeId=" + storeId + ", dealerId=" + dealerId + ", storeCode=" + storeCode + ", storeName="
                + storeName + ", storeUrl=" + storeUrl + ", storeDesc=" + storeDesc + ", storeOwner=" + storeOwner
                + ", storeStatus=" + storeStatus + ", openningDate=" + openningDate + ", closingDate=" + closingDate
                + ", brandId=" + brandId + ", brandName=" + brandName + ", storeOwnerPhone=" + storeOwnerPhone
                + ", serviceScope=" + serviceScope + ", rtfId=" + rtfId + ", point=" + point + "]";
    }

    
}