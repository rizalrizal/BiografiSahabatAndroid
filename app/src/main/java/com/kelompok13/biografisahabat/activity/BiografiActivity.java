package com.kelompok13.biografisahabat.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok13.biografisahabat.R;
import com.kelompok13.biografisahabat.adapter.BiografiAdapter;
import com.kelompok13.biografisahabat.model.Biografi;
import com.kelompok13.biografisahabat.model.BiografiList;
import com.kelompok13.biografisahabat.util.Constant;
import com.kelompok13.biografisahabat.util.RecyclerItemClickListener;
import com.kelompok13.biografisahabat.util.api.BaseApiService;
import com.kelompok13.biografisahabat.util.api.UtilsApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiografiActivity extends AppCompatActivity {

    @BindView(R.id.btnTambahBiografi)
    Button btnTambahBiografi;
    @BindView(R.id.btnRefresh)
    Button btnRefresh;
    @BindView(R.id.tvBelumBiografi)
    TextView tvBelumBiografi;
    @BindView(R.id.rvBiografi)
    RecyclerView rvBiografi;
    ProgressDialog loading;

    Context mContext;
    ArrayList<Biografi> biografiList;
    BiografiAdapter biografiAdapter;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografi);
        biografiList = new ArrayList<>();
        getSupportActionBar().setTitle("Biografi Sahabat");

        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        mContext = this;

        biografiAdapter = new BiografiAdapter(this, biografiList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvBiografi.setLayoutManager(mLayoutManager);
        rvBiografi.setItemAnimator(new DefaultItemAnimator());

        getDataBiografi();

        btnTambahBiografi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BiografiActivity.this, TambahBiografiActivity2.class));
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               finish();
                startActivity(getIntent());
            }
        });
    }

    public void getDataBiografi(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.tampilRequest().enqueue(new Callback<BiografiList>() {
            @Override
            public void onResponse(Call<BiografiList> call, Response<BiografiList> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    final ArrayList<Biografi> biografisahabat = response.body().getBiografi();
                    if (biografisahabat.get(0).getId().equals("")) {
                        tvBelumBiografi.setVisibility(View.VISIBLE);
                    } else {

                        rvBiografi.setAdapter(new BiografiAdapter(mContext, biografisahabat));
                        biografiAdapter.notifyDataSetChanged();

                        initDataIntent(biografisahabat);
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data Sahabat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BiografiList> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final ArrayList<Biografi> biografiList){
        rvBiografi.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = biografiList.get(position).getId();
                        String namasahabat = biografiList.get(position).getNama();
                        String namajulukan = biografiList.get(position).getJulukan();
                        String isi = biografiList.get(position).getIsi();

                        Intent detailBiografi = new Intent(mContext, BiografiDetailActivity.class);
                        detailBiografi.putExtra(Constant.KEY_ID_SAHABAT, id);
                        detailBiografi.putExtra(Constant.KEY_NAMA_SAHABAT, namasahabat);
                        detailBiografi.putExtra(Constant.KEY_JULUKAN, namajulukan);
                        detailBiografi.putExtra(Constant.KEY_ISI, isi);
                        detailBiografi.putExtra("status", "admin");
                        startActivity(detailBiografi);
                    }
                }));
    }
}
