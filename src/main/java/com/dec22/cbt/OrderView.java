package com.dec22.cbt;

public class OrderView
{
    private String sellername;
    private String offername;
    private String unit;
    private int qty;
    private float orderamnt;

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public void setOffername(String offername) {
        this.offername = offername;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setOrderamnt(float orderamnt) {
        this.orderamnt = orderamnt;
    }

    public String getSellername() {
        return sellername;
    }

    public String getOffername() {
        return offername;
    }

    public String getUnit() {
        return unit;
    }

    public int getQty() {
        return qty;
    }

    public float getOrderamnt() {
        return orderamnt;
    }
}
