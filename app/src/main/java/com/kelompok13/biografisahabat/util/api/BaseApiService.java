package com.kelompok13.biografisahabat.util.api;

import com.kelompok13.biografisahabat.model.BiografiList;
import com.kelompok13.biografisahabat.model.ResponseHapusList;
import com.kelompok13.biografisahabat.model.ResponseLoginList;
import com.kelompok13.biografisahabat.model.ResponseSimpanList;
import com.kelompok13.biografisahabat.model.ResponseUbahList;


import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Query;


public interface BaseApiService {


    @GET("api.php?page=login")
    Call<ResponseLoginList> loginRequest(@Query("username") String username,
                                         @Query("password") String password);

    @GET("api.php?page=simpan")
    Call<ResponseSimpanList> simpanRequest(@Query("nama") String nama,
                                           @Query("julukan") String julukan,
                                           @Query("isi") String isi);
    @GET("api.php?page=ubah")
    Call<ResponseUbahList> ubahRequest(@Query("id") String id,
                                       @Query("nama") String nama,
                                       @Query("julukan") String julukan,
                                       @Query("isi") String isi);

    @GET("api.php?page=hapus")
    Call<ResponseHapusList> hapusRequest(@Query("id") String id);


    @GET("api.php?page=tampil")
    Call<BiografiList> tampilRequest();



}
