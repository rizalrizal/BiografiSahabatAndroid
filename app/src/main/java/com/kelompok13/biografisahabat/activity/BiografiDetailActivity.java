package com.kelompok13.biografisahabat.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.amulyakhare.textdrawable.TextDrawable;
import com.kelompok13.biografisahabat.R;
import com.kelompok13.biografisahabat.model.ResponseHapus;
import com.kelompok13.biografisahabat.model.ResponseHapusList;
import com.kelompok13.biografisahabat.model.ResponseSimpan;
import com.kelompok13.biografisahabat.model.ResponseSimpanList;
import com.kelompok13.biografisahabat.util.Constant;
import com.kelompok13.biografisahabat.util.api.BaseApiService;
import com.kelompok13.biografisahabat.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiografiDetailActivity extends AppCompatActivity {

    /*@BindView(R.id.ivTextDrawable)
    ImageView ivTextDrawable;*/
    @BindView(R.id.tvNamaBiografiDetail)
    TextView tvNamaBiografiDetail;
    @BindView(R.id.tvJulukanDetail)
    TextView tvJulukanDetail;
    @BindView(R.id.tvIsiDetail)
    TextView tvIsiDetail;
    @BindView(R.id.btnHapus)
    Button btnHapus;
    @BindView(R.id.btnUbah)
    Button btnUbah;
    ProgressDialog loading;
    private ArrayList<ResponseHapus> responsehapusList;
    String mId;
    String mNamaBiografi;
    String mJulukan;
    String mIsi;
    String mStatus;

    Context mContext;
    BaseApiService mApiService;

    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografi_detail);
        //getSupportActionBar().hide();
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        /**
         * Array List for Binding Data from JSON to this List
         */
        responsehapusList = new ArrayList<>();
        Intent intent = getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra(Constant.KEY_NAMA_SAHABAT));
        mId = intent.getStringExtra(Constant.KEY_ID_SAHABAT);
        mNamaBiografi = intent.getStringExtra(Constant.KEY_NAMA_SAHABAT);
        mJulukan = intent.getStringExtra(Constant.KEY_JULUKAN);
        mIsi = intent.getStringExtra(Constant.KEY_ISI);
        mStatus = intent.getStringExtra("status");

        tvNamaBiografiDetail.setText(mNamaBiografi);
        tvJulukanDetail.setText(mJulukan);
        tvIsiDetail.setText(mIsi);
        if(mStatus.equals("client")){

            btnUbah.setVisibility(View.INVISIBLE);
            btnHapus.setVisibility(View.INVISIBLE);
        }else {
            btnUbah.setVisibility(View.VISIBLE);
            btnHapus.setVisibility(View.VISIBLE);

        }

       /* String firstCharNamaBiografi = mNamaBiografi.substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaBiografi, getColor());
        ivTextDrawable.setImageDrawable(drawable);*/

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDeleteBiografi();
            }
        });
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ubahBiografi = new Intent(mContext, UbahBiografiActivity.class);
                ubahBiografi.putExtra("m", mId);
                ubahBiografi.putExtra("n", mNamaBiografi);
                ubahBiografi.putExtra("j", mJulukan);
                ubahBiografi.putExtra("i", mIsi);
                startActivity(ubahBiografi);
                finish();
            }
        });
    }



    private void requestDeleteBiografi(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.hapusRequest(mId)
                .enqueue(new Callback<ResponseHapusList>() {
                    @Override
                    public void onResponse(Call<ResponseHapusList> call, Response<ResponseHapusList> response) {
                        if (response.isSuccessful()){
                            String sukses = "sukses";
                            responsehapusList = response.body().getResponseHapus();
                            Log.i("", "onResponse: "+responsehapusList.get(0).getMessage().toString());
                            if(responsehapusList.get(0).getMessage().equals(sukses)){
                                loading.dismiss();
                                Toast.makeText(mContext, "Data Berhasil DiHapus", Toast.LENGTH_LONG).show();
                                finish();

                            }else{
                                loading.dismiss();
                                Toast.makeText(mContext, "Gagal Hapus Data", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal Hapus Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseHapusList> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
