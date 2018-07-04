package com.kelompok13.biografisahabat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELLDURIO on 04/01/2018.
 */

public class ResponseLoginList {
    @SerializedName("ResponseLogin")
    @Expose
    private ArrayList<ResponseLogin> responseLogin = new ArrayList<ResponseLogin>();

    public ArrayList<ResponseLogin> getResponseLogin() {
        return responseLogin;
    }

    public void setResponseLogin(ArrayList<ResponseLogin> responseLogin) {
        this.responseLogin = responseLogin;
    }
}
