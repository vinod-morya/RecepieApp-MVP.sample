package com.wildnettechnologies.recepieonmvp.Recepie;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;

import java.util.List;

interface RecepieContracts
  {
	 interface RecepieRouter
		{
		  void showNextActivity();

		  void navigateTorecepieDetailModule(RecepieModel.Result result);
		}

	 interface RecepiePresenter
		{
		  void onSearchStarted(RecepieRequestModel mRecepieRequestModel);

		  void onWelcomeClicked();

		  void navigateToRowDetailModule(RecepieModel.Result result);
		}

	 interface RecepieInteractor
		{

		  void getRecepies(OnFinishedListener listener, RecepieRequestModel model);

		  interface OnFinishedListener
			 {
				void onRecepieReady(List<RecepieModel.Result> items, int pageNumber);

				void onRecepieFailed(String errorMessage);
			 }
		}

	 interface IRecepieView
		{

		  void showProgress();

		  void hideProgress();

		  void hideDefaultText();

		  void setItems(List<RecepieModel.Result> items, int pageNumber);
		}
  }