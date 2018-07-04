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
import com.kelompok13.biografisahabat.model.ResponseSimpan;
import com.kelompok13.biografisahabat.model.ResponseSimpanList;
import com.kelompok13.biografisahabat.model.ResponseUbah;
import com.kelompok13.biografisahabat.model.ResponseUbahList;
import com.kelompok13.biografisahabat.util.Constant;
import com.kelompok13.biografisahabat.util.api.BaseApiService;
import com.kelompok13.biografisahabat.util.api.UtilsApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahBiografiActivity extends AppCompatActivity {
    @BindView(R.id.etJulukanBiografiUbah)
    EditText etJulukanBiografiUbah;
    @BindView(R.id.etNamaBiografiUbah)
    EditText etNamaBiografiUbah;
    @BindView(R.id.etIsiBiografiUbah)
    EditText etIsiBiografiUbah;
    @BindView(R.id.btnUbahBiografi)
    Button btnUbahBiografi;
    ProgressDialog loading;
    String mId;
    private ArrayList<ResponseUbah> responseubahList;

    BaseApiService mApiService;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_biografi);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra(Constant.KEY_NAMA_SAHABAT));
        mId = intent.getStringExtra("m");
        String mNamaBiografi = intent.getStringExtra("n");
        String mJulukan = intent.getStringExtra("j");
        String mIsi = intent.getStringExtra("i");

        etNamaBiografiUbah.setText(mNamaBiografi);
        etJulukanBiografiUbah.setText(mJulukan);
        etIsiBiografiUbah.setText(mIsi);
        mApiService = UtilsApi.getAPIService();
        /**
         * Array List for Binding Data from JSON to this List
         */
        responseubahList = new ArrayList<>();
        btnUbahBiografi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUbahBiografi();
            }
        });
    }
    private void requestUbahBiografi(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.ubahRequest(mId,etNamaBiografiUbah.getText().toString(), etJulukanBiografiUbah.getText().toString(),etIsiBiografiUbah.getText().toString())
                .enqueue(new Callback<ResponseUbahList>() {
                    @Override
                    public void onResponse(Call<ResponseUbahList> call, Response<ResponseUbahList> response) {
                        if (response.isSuccessful()){
                            String sukses = "sukses";
                            responseubahList = response.body().getResponseUbah();
                            Log.i("", "onResponse: "+responseubahList.get(0).getMessage().toString());
                            if(responseubahList.get(0).getMessage().equals(sukses)){
                                loading.dismiss();
                                Toast.makeText(mContext, "Data Berhasil Diubah", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(mContext, BiografiActivity.class);
                                finish();
                                startActivity(intent);


                            }else{
                                loading.dismiss();
                                Toast.makeText(mContext, "Gagal Mengubah Data", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal Mengubah Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUbahList> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
