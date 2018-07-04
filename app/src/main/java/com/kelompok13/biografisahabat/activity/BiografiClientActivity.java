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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiografiClientActivity extends AppCompatActivity {
    @BindView(R.id.tvBelumBiografiClient)
    TextView tvBelumBiografiClient;
    @BindView(R.id.rvBiografiClient)
    RecyclerView rvBiografiClient;
    ProgressDialog loading;

    Context mContext;
    ArrayList<Biografi> biografiList;
    BiografiAdapter biografiAdapter;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografi_client);
        biografiList = new ArrayList<>();
        getSupportActionBar().setTitle("Biografi Sahabat");

        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        mContext = this;

        biografiAdapter = new BiografiAdapter(this, biografiList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvBiografiClient.setLayoutManager(mLayoutManager);
        rvBiografiClient.setItemAnimator(new DefaultItemAnimator());

        getDataBiografi();

    }

    private void getDataBiografi(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.tampilRequest().enqueue(new Callback<BiografiList>() {
            @Override
            public void onResponse(Call<BiografiList> call, Response<BiografiList> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    final ArrayList<Biografi> biografisahabat = response.body().getBiografi();
                    if (biografisahabat.get(0).getId().equals("")) {
                        tvBelumBiografiClient.setVisibility(View.VISIBLE);
                    } else {

                        rvBiografiClient.setAdapter(new BiografiAdapter(mContext, biografisahabat));
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

    private void initDataIntent(final List<Biografi> biografiList){
        rvBiografiClient.addOnItemTouchListener(
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
                        detailBiografi.putExtra("status", "client");
                        startActivity(detailBiografi);
                    }
                }));
    }
}
