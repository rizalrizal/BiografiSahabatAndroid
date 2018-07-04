package com.kelompok13.biografisahabat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.kelompok13.biografisahabat.R;
import com.kelompok13.biografisahabat.model.Biografi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BiografiAdapter extends RecyclerView.Adapter<BiografiAdapter.BiografiHolder> {

    Context mContext;
    ArrayList<Biografi> biografiList;

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

    public BiografiAdapter(Context context, ArrayList<Biografi> biografiListParameter) {
        this.mContext = context;
        biografiList = biografiListParameter;
    }

    @Override
    public BiografiAdapter.BiografiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_biografi, parent, false);
        return new BiografiHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BiografiAdapter.BiografiHolder holder, int position) {
        final Biografi biografi = biografiList.get(position);
        holder.tvNamaSahabat.setText(biografi.getNama());
        holder.tvNamaJulukan.setText(biografi.getJulukan());

        String namaSahabat = biografi.getNama();
        String firstCharNamaBiografi = namaSahabat.substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaBiografi, getColor());
        holder.ivTextDrawable.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return biografiList.size();
    }

    public class BiografiHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivTextDrawable)
        ImageView ivTextDrawable;
        @BindView(R.id.tvNamaSahabat)
        TextView tvNamaSahabat;
        @BindView(R.id.tvJulukanSahabat)
        TextView tvNamaJulukan;


        public BiografiHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
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
}
