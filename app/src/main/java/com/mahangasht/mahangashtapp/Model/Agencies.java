package com.mahangasht.mahangashtapp.Model;

/**
 * Created by Amin on 2/20/2017.
 */

public class Agencies {
    private String AgencyId;
    private String AName;
    private String APhones;
    private String AMobile;
    private String AManager;
    private String AAddress;

    public Agencies(String agencyId, String AName, String APhones, String AMobile, String AManager, String AAddress) {
        AgencyId = agencyId;
        this.AName = AName;
        this.APhones = APhones;
        this.AMobile = AMobile;
        this.AManager = AManager;
        this.AAddress = AAddress;
    }

    public String getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(String agencyId) {
        AgencyId = agencyId;
    }

    public String getAName() {
        return AName;
    }

    public void setAName(String AName) {
        this.AName = AName;
    }

    public String getAPhones() {
        return APhones;
    }

    public void setAPhones(String APhones) {
        this.APhones = APhones;
    }

    public String getAMobile() {
        return AMobile;
    }

    public void setAMobile(String AMobile) {
        this.AMobile = AMobile;
    }

    public String getAManager() {
        return AManager;
    }

    public void setAManager(String AManager) {
        this.AManager = AManager;
    }

    public String getAAddress() {
        return AAddress;
    }

    public void setAAddress(String AAddress) {
        this.AAddress = AAddress;
    }
}
