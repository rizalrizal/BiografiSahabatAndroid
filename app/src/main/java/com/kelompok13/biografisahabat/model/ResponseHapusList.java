package com.kelompok13.biografisahabat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by DELLDURIO on 05/01/2018.
 */

public class ResponseHapusList {
    @SerializedName("ResponseHapus")
    @Expose
    private ArrayList<ResponseHapus> responseHapus = new ArrayList<ResponseHapus>();

    public ArrayList<ResponseHapus> getResponseHapus() {
        return responseHapus;
    }

    public void setResponseSimpan(ArrayList<ResponseHapus> responseHapus) {
        this.responseHapus = responseHapus;
    }

}
