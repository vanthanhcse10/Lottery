package com.thanhnguyen.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ThanhNguyen on 4/28/2017.
 */

public class Date implements Serializable{
    public String dateName;
    public String firstRate;
    public String twoRate;
    public String threeRate;
    public String fourRate;
    public String fiveRate;
    public String sixRate;
    public String sevenRate;
    public String eightRate;
    public String specialRate;

    public Date(String dateName, String firstRate, String twoRate,
                String threeRate, String fourRate, String fiveRate, String sixRate,
                String sevenRate, String eightRate, String specialRate) {
        this.dateName = dateName;
        this.firstRate = firstRate;
        this.twoRate = twoRate;
        this.threeRate = threeRate;
        this.fourRate = fourRate;
        this.fiveRate = fiveRate;
        this.sixRate = sixRate;
        this.sevenRate = sevenRate;
        this.eightRate = eightRate;
        this.specialRate = specialRate;
    }

    public Date() {
    }
}
