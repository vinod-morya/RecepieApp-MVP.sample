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

public class RecepieAdapter extends RecyclerView.Adapter<RecepieAdapter.RecepiesViewHolder> implements View.OnClickListener
  {
	 private final Activity mActivity;
	 @BindView(R.id.cardViewRecepieItem)
	 CardView mCardViewRecepieItem;
	 private List<RecepieModel.Result> mRecepieModelResult = new ArrayList<>();
	 private RecyclerViewClickListener mRecyclerViewClickListener;

	 public RecepieAdapter(Activity activity, List<RecepieModel.Result> mRecepieModelResult)
		{
		  this.mRecepieModelResult = mRecepieModelResult;
		  mActivity = activity;
		  mRecyclerViewClickListener = (RecyclerViewClickListener) activity;
		}

	 @Override
	 public RecepiesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
		{
		  View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recepie, viewGroup, false);
		  return new RecepiesViewHolder(v);
		}

	 @Override
	 public void onBindViewHolder(RecepiesViewHolder holder, int position)
		{
		  final RecepieModel.Result recepie = mRecepieModelResult.get(position);
		  holder.tvRecepie.setText(recepie.getTitle());
		  holder.tvRecepieDetails.setText(recepie.getIngredients());
		  holder.cardViewRecepieItem.setTag(recepie);
		  Glide.with(mActivity).load(recepie.getThumbnail()).placeholder(mActivity.getResources().getDrawable(R.drawable.ic_launcher_background)).into(holder.ivPicture).onLoadFailed(new Exception(),
				 mActivity.getResources().getDrawable(R.drawable.ic_launcher_background));
		  holder.cardViewRecepieItem.setOnClickListener(this);
		}

	 @Override
	 public long getItemId(int position)
		{
		  return position;
		}

	 @Override
	 public int getItemCount()
		{
		  return mRecepieModelResult.size();
		}

	 @Override
	 public void onClick(View v)
		{
		  mRecyclerViewClickListener.onCellClick((RecepieModel.Result)v.getTag());
		}

	 class RecepiesViewHolder extends RecyclerView.ViewHolder
		{
		  @BindView(R.id.ivPicture)
		  ImageView ivPicture;
		  @BindView(R.id.tvRecepie)
		  TextView tvRecepie;
		  @BindView(R.id.tvRecepieDetails)
		  TextView tvRecepieDetails;
		  @BindView(R.id.cardViewRecepieItem)
		  CardView cardViewRecepieItem;

		  RecepiesViewHolder(View itemView)
			 {
				super(itemView);
				ButterKnife.bind(this, itemView);
			 }
		}

	 public interface RecyclerViewClickListener
		{
		  void onCellClick(RecepieModel.Result tag);
		}
  }
