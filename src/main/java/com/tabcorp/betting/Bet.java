package com.tabcorp.betting;

public class Bet {
    private String type;
    private Integer entity;
    private Integer amount;

    public String getType() {
        return type;
    }

    public Integer getEntity() {
        return entity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEntity(Integer entity) {
        this.entity = entity;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
