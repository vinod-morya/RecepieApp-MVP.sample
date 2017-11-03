package com.wildnettechnologies.recepieonmvp.Recepie.Router;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;

public interface RecepieRouter
  {
	 void showNextActivity();

	 void navigateTorecepieDetailModule(RecepieModel.Result result);
  }
