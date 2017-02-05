package com.savages.embryo.embryo.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 04-02-2017.
 */
public class Patient {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNPP() {
        return NPP;
    }

    public void setNPP(String NPP) {
        this.NPP = NPP;
    }

    public String getNMC() {
        return NMC;
    }

    public void setNMC(String NMC) {
        this.NMC = NMC;
    }

    public String getGen_disease() {
        return gen_disease;
    }

    public void setGen_disease(String gen_disease) {
        this.gen_disease = gen_disease;
    }

    private String name;
    private String mobile;
    private String city;
    private String NPP;
    private String NMC;
    private String gen_disease;
    private String DOC;



    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }
}
