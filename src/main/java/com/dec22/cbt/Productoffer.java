package com.dec22.cbt;

import jakarta.persistence.*;

@Entity
@Table(name = "productoffers")
public class Productoffer {
    @Id
    @Column(name = "offerid", nullable = false, length = 10)
    private String id;


    private String username;

    @Column(name = "hscode", nullable = false, length = 10)
    private String hscode;

    @Column(name = "offername", length = 50)
    private String offername;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unit", length = 5)
    private String unit;

    @Column(name = "unitprice")
    private Float unitprice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getOffername() {
        return offername;
    }

    public void setOffername(String offername) {
        this.offername = offername;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Float unitprice) {
        this.unitprice = unitprice;
    }

}