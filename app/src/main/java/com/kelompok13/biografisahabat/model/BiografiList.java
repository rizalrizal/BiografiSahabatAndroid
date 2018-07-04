package com.kelompok13.biografisahabat.model;

/**
 * Created by DELLDURIO on 03/01/2018.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BiografiList {
    @SerializedName("biografi")
    @Expose
    private ArrayList<Biografi> biografi = new ArrayList<Biografi>();

    public ArrayList<Biografi> getBiografi() {
        return biografi;
    }

    public void setBiografi(ArrayList<Biografi> biografi) {
        this.biografi = biografi;
    }
}
