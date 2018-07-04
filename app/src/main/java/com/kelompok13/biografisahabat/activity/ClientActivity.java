package com.kelompok13.biografisahabat.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kelompok13.biografisahabat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientActivity extends AppCompatActivity {
    @BindView(R.id.btnLihatBiografiClient)
    Button btnLihatBiografiClient;
    @BindView(R.id.btnAboutClient)
    Button btnAboutClient;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        mContext =this;
        ButterKnife.bind(this);
        btnLihatBiografiClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, BiografiClientActivity.class));
            }
        });
        btnAboutClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AboutActivity.class));
            }
        });
    }
}
