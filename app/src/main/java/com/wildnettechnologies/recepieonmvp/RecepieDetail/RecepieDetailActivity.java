package com.wildnettechnologies.recepieonmvp.RecepieDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wildnettechnologies.recepieonmvp.R;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.base.Constants.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecepieDetailActivity extends AppCompatActivity
  {

	 @BindView(R.id.tvWelcomeText)
	 TextView mTvWelcomeText;
	 @BindView(R.id.tvTitle)
	 TextView mTvTitle;
	 @BindView(R.id.tvHref)
	 TextView mTvHref;
	 @BindView(R.id.tvIngredients)
	 TextView mtvIngredients;
	 private RecepieModel.Result mResultModel;

	 @Override
	 protected void onCreate(Bundle savedInstanceState)
		{
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_recepie_detail);
		  ButterKnife.bind(this);
		  if(getIntent().getExtras() != null)
		  {
			 mResultModel = getIntent().getExtras().getParcelable(AppConstants.USER_MODEL);
			 if(mResultModel != null)
			 {
				mTvTitle.setText(getString(R.string.title) + mResultModel.getTitle());
				mTvHref.setText(getString(R.string.href) + mResultModel.getHref());
				mtvIngredients.setText(getString(R.string.ingredients) + mResultModel.getIngredients());
				mTvWelcomeText.setVisibility(View.GONE);
			 }
		  }
		}
  }
