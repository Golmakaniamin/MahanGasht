package com.mahangasht.mahangashtapp.Model;

/**
 * Created by Amin on 2/20/2017.
 */

public class ServiceType {
    private String ServiceTypeId ;
    private String STName ;
    private String STItems ;

    public ServiceType(String serviceTypeId, String STName, String STItems) {
        ServiceTypeId = serviceTypeId;
        this.STName = STName;
        this.STItems = STItems;
    }

    public String getServiceTypeId() {
        return ServiceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        ServiceTypeId = serviceTypeId;
    }

    public String getSTName() {
        return STName;
    }

    public void setSTName(String STName) {
        this.STName = STName;
    }

    public String getSTItems() {
        return STItems;
    }

    public void setSTItems(String STItems) {
        this.STItems = STItems;
    }
}
