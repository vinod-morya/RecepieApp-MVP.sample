package com.wildnettechnologies.recepieonmvp.Recepie.Interactor;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;

import java.util.List;

public interface RecepieInteractor
  {

	 void getRecepies(OnFinishedListener listener, RecepieRequestModel model);

	 interface OnFinishedListener
		{
		  void onRecepieReady(List<RecepieModel.Result> items, int pageNumber);
		  void onRecepieFailed(String errorMessage);
		}
  }
