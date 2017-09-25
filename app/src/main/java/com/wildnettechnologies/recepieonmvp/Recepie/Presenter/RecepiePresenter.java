package com.wildnettechnologies.recepieonmvp.Recepie.Presenter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;

public interface RecepiePresenter {

    void onResume();

    void onDestroy();

    void onSearchStarted(RecepieRequestModel mRecepieRequestModel);
}
