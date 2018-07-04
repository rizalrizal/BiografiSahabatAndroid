package com.kelompok13.biografisahabat.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kelompok13.biografisahabat.R;
import com.kelompok13.biografisahabat.model.ResponseLogin;
import com.kelompok13.biografisahabat.model.ResponseLoginList;
import com.kelompok13.biografisahabat.model.ResponseSimpan;
import com.kelompok13.biografisahabat.model.ResponseSimpanList;
import com.kelompok13.biografisahabat.util.SharedPrefManager;
import com.kelompok13.biografisahabat.util.api.BaseApiService;
import com.kelompok13.biografisahabat.util.api.UtilsApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBiografiActivity2 extends AppCompatActivity {

    @BindView(R.id.etJulukanBiografi)
    EditText etJulukanBiografi;
    @BindView(R.id.etNamaBiografi)
    EditText etNamaBiografi;
    @BindView(R.id.etIsiBiografi)
    EditText etIsiBiografi;
    @BindView(R.id.btnSimpanBiografi)
    Button btnSimpanBiografi;
    ProgressDialog loading;

    private ArrayList<ResponseSimpan> responsesimpanList;

    BaseApiService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_biografi_2);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        /**
         * Array List for Binding Data from JSON to this List
         */
        responsesimpanList = new ArrayList<>();
        btnSimpanBiografi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSimpanBiografi();
            }
        });
    }

    private void requestSimpanBiografi(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.simpanRequest(etNamaBiografi.getText().toString(), etJulukanBiografi.getText().toString(),etIsiBiografi.getText().toString())
                .enqueue(new Callback<ResponseSimpanList>() {
                    @Override
                    public void onResponse(Call<ResponseSimpanList> call, Response<ResponseSimpanList> response) {
                        if (response.isSuccessful()){
                            String sukses = "sukses";
                            responsesimpanList = response.body().getResponseSimpan();
                            Log.i("", "onResponse: "+responsesimpanList.get(0).getMessage().toString());
                            if(responsesimpanList.get(0).getMessage().equals(sukses)){
                                loading.dismiss();
                                Toast.makeText(mContext, "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
                                finish();

                            }else{
                                loading.dismiss();
                                Toast.makeText(mContext, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseSimpanList> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
