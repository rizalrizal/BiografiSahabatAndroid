package com.kelompok13.biografisahabat.model;

/**
 * Created by DELLDURIO on 03/01/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Biografi {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("julukan")
    @Expose
    private String julukan;
    @SerializedName("isi")
    @Expose
    private String isi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJulukan() {
        return julukan;
    }

    public void setJulukan(String julukan) {
        this.julukan = julukan;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
