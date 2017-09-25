package com.wildnettechnologies.recepieonmvp.Recepie.View;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wildnettechnologies.recepieonmvp.R;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecepieAdapter extends RecyclerView.Adapter<RecepieAdapter.RecepiesViewHolder> {
    private final Activity mActivity;
    private List<RecepieModel.Result> mRecepieModelResult = new ArrayList<>();

    public RecepieAdapter(Activity activity, List<RecepieModel.Result> mRecepieModelResult) {
        this.mRecepieModelResult = mRecepieModelResult;
        mActivity = activity;
    }

    @Override
    public RecepiesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recepie, viewGroup, false);
        return new RecepiesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecepiesViewHolder holder, int position) {
        final RecepieModel.Result recepie = mRecepieModelResult.get(position);
        holder.tvRecepie.setText(recepie.getTitle());
        holder.tvRecepieDetails.setText(recepie.getIngredients());
        Glide.with(mActivity).load(recepie.getThumbnail()).placeholder(mActivity.getResources().getDrawable(R.drawable.ic_launcher_background))
                .into(holder.ivPicture).onLoadFailed(new Exception(), mActivity.getResources().getDrawable(R.drawable.ic_launcher_background));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mRecepieModelResult.size();
    }

    class RecepiesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPicture)
        ImageView ivPicture;
        @BindView(R.id.tvRecepie)
        TextView tvRecepie;
        @BindView(R.id.tvRecepieDetails)
        TextView tvRecepieDetails;
        @BindView(R.id.cardViewRecepieItem)
        CardView cardViewRecepieItem;

        RecepiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
