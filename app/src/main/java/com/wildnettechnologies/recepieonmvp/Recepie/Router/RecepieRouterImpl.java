package com.wildnettechnologies.recepieonmvp.Recepie.Router;

import android.content.Intent;
import android.os.Bundle;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.RecepieActivity;
import com.wildnettechnologies.recepieonmvp.RecepieDetail.RecepieDetailActivity;
import com.wildnettechnologies.recepieonmvp.base.Constants.AppConstants;

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
//		  Intent intent = new Intent(mRecepieActivity, RecepieDetailActivity.class);
//		  mRecepieActivity.startActivity(intent);
		}

	 @Override
	 public void navigateTorecepieDetailModule(RecepieModel.Result result)
		{
		  Intent intent = new Intent(mRecepieActivity, RecepieDetailActivity.class);
		  Bundle bundle = new Bundle();
		  bundle.putParcelable(AppConstants.USER_MODEL, result);
		  intent.putExtras(bundle);
		  mRecepieActivity.startActivity(intent);
		}
  }
