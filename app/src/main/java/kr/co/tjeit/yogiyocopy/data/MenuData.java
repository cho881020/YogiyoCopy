package kr.co.tjeit.yogiyocopy.data;

import java.io.Serializable;

/**
 * Created by user on 2017-08-16.
 */

public class MenuData implements Serializable {

    private String imageURL;
    private String menuName;
    private int price;

    public MenuData() {
    }

    public MenuData(String imageURL, String menuName, int price) {
        this.imageURL = imageURL;
        this.menuName = menuName;
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
