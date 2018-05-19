package com.mahangasht.mahangashtapp;

import com.mahangasht.mahangashtapp.Model.Agencies;
import com.mahangasht.mahangashtapp.Model.ServiceType;

import java.util.ArrayList;

/**
 * Created by Amin on 2/26/2017.
 */

public class PublicMethods {

    //private static String ServisHost = "http://192.168.160.103:9090/RESTService.svc";
    private static String ServisHost = "http://84.241.45.223:9090/RESTService.svc";

    public PublicMethods() {

    }

    public static String getServisHost() {
        return ServisHost;
    }

}
