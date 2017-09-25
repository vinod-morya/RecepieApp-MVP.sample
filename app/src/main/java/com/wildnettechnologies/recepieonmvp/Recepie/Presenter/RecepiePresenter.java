package com.wildnettechnologies.recepieonmvp.Recepie.Presenter;


import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;

public interface RecepiePresenter {

    void onResume();

    void onDestroy();

    void onSearchStarted(RecepieRequestModel mRecepieRequestModel);
}
