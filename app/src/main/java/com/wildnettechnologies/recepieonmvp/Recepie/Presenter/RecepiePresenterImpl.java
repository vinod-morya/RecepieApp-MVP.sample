
package com.wildnettechnologies.recepieonmvp.Recepie.Presenter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;
import com.wildnettechnologies.recepieonmvp.Recepie.View.IRecepieView;

import java.util.List;

public class RecepiePresenterImpl implements RecepiePresenter, RecepieInteractor.OnFinishedListener {

    private IRecepieView IRecepieView;
    private RecepieInteractor recepieInteractor;
    private RecyclerView mRecyclerView;

    public RecepiePresenterImpl(IRecepieView IRecepieView) {
        this.IRecepieView = IRecepieView;
        this.recepieInteractor = new RecepieInteractorImpl();
    }

    @Override
    public void onResume() {
        if (IRecepieView != null) {
        }
    }

    @Override
    public void onDestroy() {
        IRecepieView = null;
    }

    @Override
    public void onSearchStarted(RecepieRequestModel mRecepieRequestModel) {
        IRecepieView.showProgress();
        recepieInteractor.getRecepies(this, mRecepieRequestModel);
    }

    @Override
    public void onRecepieReady(List<RecepieModel.Result> items, int pageNumber) {
        if (pageNumber == 1) {
//            loading = false;
        }
        if (IRecepieView != null) {
            IRecepieView.setItems(items, pageNumber);
            IRecepieView.hideProgress();
            IRecepieView.hideDefaultText();
        }
    }

    public IRecepieView getIRecepieView() {
        return IRecepieView;
    }


}
