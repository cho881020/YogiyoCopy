package kr.co.tjeit.yogiyocopy.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by user on 2017-08-03.
 */

public class OrderData implements Serializable {

    private StoreData orderStore;
    private Calendar orderDate;
    private String location;
    private int cost;

    public OrderData() {

    }

    public OrderData(StoreData orderStore, Calendar orderDate, String location, int cost) {
        this.orderStore = orderStore;
        this.orderDate = orderDate;
        this.location = location;
        this.cost = cost;
    }

    public StoreData getOrderStore() {
        return orderStore;
    }

    public void setOrderStore(StoreData orderStore) {
        this.orderStore = orderStore;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
