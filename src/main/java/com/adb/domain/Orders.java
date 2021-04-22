package com.adb.domain;

import com.adb.dtos.Order;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {
    private Long orderId;
    private String siteId;
    private String itemId;
    private Long sellerId;
    private Long buyerId;
    private transient Timestamp dateCreated;
    private transient Timestamp lastUpdated;

    @Id
    @Column(name = "order_id", nullable = false)
    public Long getOrderId() {
        return orderId;

    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;

    }

    @Basic
    @Column(name = "site_id", nullable = false, length = 3)
    public String getSiteId() {
        return siteId;

    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;

    }

    @Basic
    @Column(name = "item_id", nullable = false, length = 50)
    public String getItemId() {
        return itemId;

    }

    public void setItemId(String itemId) {
        this.itemId = itemId;

    }

    @Basic
    @Column(name = "seller_id", nullable = false)
    public Long getSellerId() {
        return sellerId;

    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;

    }

    @Basic
    @Column(name = "buyer_id", nullable = false)
    public Long getBuyerId() {
        return buyerId;

    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;

    }


    @Basic
    @Column(name = "date_created", nullable = false, updatable = false)
    public Timestamp getDateCreated() {
        return dateCreated;

    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;

    }

    @Basic
    @Column(name = "last_updated", nullable = false)
    public Timestamp getLastUpdated() {
        return lastUpdated;

    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;

    }

    @PrePersist
    protected void onCreate() {
        this.lastUpdated = this.dateCreated = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = new Timestamp(new Date().getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Orders orders = (Orders) o;

        return Objects.equals(orderId, orders.orderId)
                && Objects.equals(siteId, orders.siteId)
                && Objects.equals(itemId, orders.itemId)
                && Objects.equals(sellerId, orders.sellerId)
                && Objects.equals(buyerId, orders.buyerId)
                && Objects.equals(dateCreated, orders.dateCreated)
                && Objects.equals(lastUpdated, orders.lastUpdated);

    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);

    }

    public static Orders createFrom(Order orderJson) {
        Orders order = new Orders();
        order.orderId = orderJson.getOrderId();
        order.siteId = orderJson.getSiteId();
        order.itemId = orderJson.getItemId();
        order.sellerId = orderJson.getSellerId();
        order.buyerId = orderJson.getBuyerId();
        //The fields dateCreated and lastUpdated are managed by Hibernate via annotations.

        return order;

    }

}