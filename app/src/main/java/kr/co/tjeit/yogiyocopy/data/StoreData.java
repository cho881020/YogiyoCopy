package kr.co.tjeit.yogiyocopy.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-02.
 */

public class StoreData implements Serializable {

    private String imagePath;
    private String storeName;
    private float avgRating;
    private int openTime;
    private int closeTime;
    private int minCost;
    private boolean isCesco;
    private String corpNumber; // 사업자등록번호 204-28"-"86905
    private String corpName; // 상호명
    private List<String> reviews = new ArrayList<>();

    public StoreData() {
    }


    public StoreData(String imagePath, String storeName, float avgRating, int openTime, int closeTime, int minCost, boolean isCesco, String corpNumber, String corpName) {
        this.imagePath = imagePath;
        this.storeName = storeName;
        this.avgRating = avgRating;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minCost = minCost;
        this.isCesco = isCesco;
        this.corpNumber = corpNumber;
        this.corpName = corpName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public int getOpenTime() {
        return openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public boolean isCesco() {
        return isCesco;
    }

    public void setCesco(boolean cesco) {
        isCesco = cesco;
    }

    public String getCorpNumber() {
        return corpNumber;
    }

    public void setCorpNumber(String corpNumber) {
        this.corpNumber = corpNumber;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }


    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}
