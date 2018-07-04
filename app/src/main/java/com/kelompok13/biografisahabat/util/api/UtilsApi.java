package com.kelompok13.biografisahabat.util.api;

public class UtilsApi {


    public static final String BASE_URL_API = "http://localhost/biografi/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
