package com.wildnettechnologies.recepieonmvp.Recepie.Presenter;

import com.wildnettechnologies.recepieonmvp.Recepie.Interactor.RecepieInteractor;
import com.wildnettechnologies.recepieonmvp.Recepie.Interactor.RecepieInteractorImpl;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;
import com.wildnettechnologies.recepieonmvp.Recepie.RecepieActivity;
import com.wildnettechnologies.recepieonmvp.Recepie.Router.RecepieRouter;
import com.wildnettechnologies.recepieonmvp.Recepie.Router.RecepieRouterImpl;
import com.wildnettechnologies.recepieonmvp.Recepie.View.IRecepieView;

import java.util.List;

public class RecepiePresenterImpl implements RecepiePresenter, RecepieInteractor.OnFinishedListener
  {

	 private IRecepieView IRecepieView;
	 private RecepieInteractor recepieInteractor;
	 private RecepieRouter mRecepieRouter;

	 public RecepiePresenterImpl(IRecepieView IRecepieView)
		{
		  this.IRecepieView = IRecepieView;
		  this.recepieInteractor = new RecepieInteractorImpl();
		  this.mRecepieRouter = new RecepieRouterImpl((RecepieActivity) IRecepieView);
		}

	 @Override
	 public void onSearchStarted(RecepieRequestModel mRecepieRequestModel)
		{
		  IRecepieView.showProgress();
		  recepieInteractor.getRecepies(this, mRecepieRequestModel);
		}

	 @Override
	 public void onWelcomeClicked()
		{
		  mRecepieRouter.showNextActivity();
		}

	 @Override
	 public void onRecepieReady(List<RecepieModel.Result> items, int pageNumber)
		{
		  if(IRecepieView != null)
		  {
			 IRecepieView.setItems(items, pageNumber);
			 IRecepieView.hideProgress();
			 IRecepieView.hideDefaultText();
		  }
		}

	 @Override
	 public void onRecepieFailed(String errorMessage)
		{
		  IRecepieView.hideProgress();
		}
  }
