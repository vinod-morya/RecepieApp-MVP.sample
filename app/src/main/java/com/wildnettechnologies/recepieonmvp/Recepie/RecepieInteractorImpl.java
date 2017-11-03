package com.wildnettechnologies.recepieonmvp.Recepie;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;
import com.wildnettechnologies.recepieonmvp.base.Service.ApiFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecepieInteractorImpl implements RecepieContracts.RecepieInteractor {

    public RecepieInteractorImpl() {
    }

    public void getRecepies(final OnFinishedListener finishedListener, final RecepieRequestModel model) {

        ApiFactory.getRecepieClient().getSearchedRecepies(model.getIngredients(), model.getRecepie(), model.getPage())
                .enqueue(new Callback<RecepieModel>() {
                    @Override
                    public void onResponse(Call<RecepieModel> call, Response<RecepieModel> response) {
                        RecepieModel result = response.body();
                        if (result != null)
                            finishedListener.onRecepieReady(result.getResults(), model.getPage());
                        else{
                            // Mocking a webservice on app.
                            ArrayList<RecepieModel.Result> recepies = new ArrayList<>();
                            for (int i = 0; i < 11; i++) {
                                RecepieModel.Result recepie = new RecepieModel.Result(("Recepie number : " + i), "", ("Recepie ingridients : " + i), "http://via.placeholder.com/150x150");
                                recepies.add(recepie);
                            }
                            finishedListener.onRecepieReady(recepies, model.getPage());
                        }
                    }

                    @Override
                    public void onFailure(Call<RecepieModel> call, Throwable t) {
                        try {
                            throw new InterruptedException("Error occured!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            // // Mocking a webservice
                            ArrayList<RecepieModel.Result> recepies = new ArrayList<>();
                            for (int i = 0; i < 11; i++) {
                                RecepieModel.Result recepie = new RecepieModel.Result(("Recepie number : " + i), "", ("Recepie ingridients : " + i), "http://via.placeholder.com/150x150");
                                recepies.add(recepie);
                            }
                            finishedListener.onRecepieReady(recepies, model.getPage());
                        }
                    }
                });
    }
}
