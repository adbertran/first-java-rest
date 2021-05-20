package com.adb.dtos;

public class Engine extends AbstractJson {
    private Integer hp;
    private Double price;

    public Engine(Integer hp, Double price) {
        this.hp = hp;
        this.price = price;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
