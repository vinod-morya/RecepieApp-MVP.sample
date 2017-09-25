package com.wildnettechnologies.recepieonmvp.Recepie.View;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;

import java.util.List;

public interface IRecepieView {

    void showProgress();

    void hideProgress();

    void hideDefaultText();

    void showDefaultText();

    void setItems(List<RecepieModel.Result> items, int pageNumber);

    void showMessage(String message);

    void startSearchText(String sreachText);
}
