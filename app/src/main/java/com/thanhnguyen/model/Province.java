package com.thanhnguyen.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ThanhNguyen on 4/28/2017.
 */

public class Province implements Serializable {
    private String provinceName;
    private ArrayList<Date> arrDate;

    public Province() {
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Province(String provinceName, ArrayList<Date> arrDate) {
        this.provinceName = provinceName;
        this.arrDate = arrDate;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public ArrayList<Date> getArrDate() {
        return arrDate;
    }

    public void setArrDate(ArrayList<Date> arrDate) {
        this.arrDate = arrDate;
    }
}
