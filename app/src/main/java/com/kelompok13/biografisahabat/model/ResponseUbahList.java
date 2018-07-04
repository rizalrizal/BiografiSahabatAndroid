package com.kelompok13.biografisahabat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by DELLDURIO on 05/01/2018.
 */

public class ResponseUbahList {
    @SerializedName("ResponseUbah")
    @Expose
    private ArrayList<ResponseUbah> responseUbah = new ArrayList<ResponseUbah>();

    public ArrayList<ResponseUbah> getResponseUbah() {
        return responseUbah;
    }

    public void setResponseUbah(ArrayList<ResponseUbah> responseUbah) {
        this.responseUbah = responseUbah;
    }
}
