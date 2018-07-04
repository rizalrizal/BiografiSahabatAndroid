package com.kelompok13.biografisahabat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by DELLDURIO on 05/01/2018.
 */

public class ResponseSimpanList {
    @SerializedName("ResponseSimpan")
    @Expose
    private ArrayList<ResponseSimpan> responseSimpan = new ArrayList<ResponseSimpan>();

    public ArrayList<ResponseSimpan> getResponseSimpan() {
        return responseSimpan;
    }

    public void setResponseSimpan(ArrayList<ResponseSimpan> responseSimpan) {
        this.responseSimpan = responseSimpan;
    }
}
