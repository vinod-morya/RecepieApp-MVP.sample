package com.wildnettechnologies.recepieonmvp.Recepie.Router;

import android.content.Intent;

import com.wildnettechnologies.recepieonmvp.Recepie.RecepieActivity;
import com.wildnettechnologies.recepieonmvp.RecepieDetail.RecepieDetailActivity;

public class RecepieRouterImpl implements RecepieRouter
  {

	 RecepieActivity mRecepieActivity;

	 public RecepieRouterImpl(RecepieActivity activity)
		{
		  mRecepieActivity = activity;
		}

	 @Override
	 public void showNextActivity()
		{
		  Intent intent = new Intent(mRecepieActivity, RecepieDetailActivity.class);
		  mRecepieActivity.startActivity(intent);
		}
  }
