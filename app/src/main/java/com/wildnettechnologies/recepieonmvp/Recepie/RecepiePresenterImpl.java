package com.wildnettechnologies.recepieonmvp.Recepie;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;

import java.util.List;

public class RecepiePresenterImpl implements RecepieContracts.RecepiePresenter, RecepieContracts.RecepieInteractor.OnFinishedListener
  {

	 private RecepieContracts.IRecepieView IRecepieView;
	 private RecepieContracts.RecepieInteractor recepieInteractor;
	 private RecepieContracts.RecepieRouter mRecepieRouter;

	 public RecepiePresenterImpl(RecepieContracts.IRecepieView IRecepieView)
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
	 public void navigateToRowDetailModule(RecepieModel.Result result)
		{
		  mRecepieRouter.navigateTorecepieDetailModule(result);
		}

	 @Override
	 public void onDestroy()
		{
		  mRecepieRouter.onDestroy();
		  IRecepieView = null;
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
