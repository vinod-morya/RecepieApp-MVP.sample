package com.wildnettechnologies.recepieonmvp.Recepie.Presenter;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;

public interface RecepiePresenter
  {

	 void onSearchStarted(RecepieRequestModel mRecepieRequestModel);

	 void onWelcomeClicked();

	 void navigateToRowDetailModule(RecepieModel.Result result);
  }
