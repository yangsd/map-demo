package com.map.demo.bean;

import javax.persistence.*;

/**
 * 地理位置信息
 * Created by sdyang on 2016/6/30.
 */
@Entity
@Table(name="app_map_location")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Long id;

    @Column
    private String longitude;//经度

    @Column
    private String latitude;//纬度

    @Column
    private String icon;//图标

    @Column
    private String address;//地址

    @Column
    private String remark;//备注信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
