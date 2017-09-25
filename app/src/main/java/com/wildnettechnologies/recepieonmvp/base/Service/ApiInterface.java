package com.wildnettechnologies.recepieonmvp.base.Service;

import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.base.Constants.AppConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * * Interface through which all the api calls will be performed
 */
public interface ApiInterface
  {

	 @FormUrlEncoded
	 @POST(AppConstants.PUPPY_API)
	 Call<RecepieModel> getSearchedRecepies(@Field("i") String ingredients, @Field("q") String query, @Field("p") int page);
  }

