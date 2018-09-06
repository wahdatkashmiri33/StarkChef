package com.chef.emzah.starkchef.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.Networking.Api;
import com.chef.emzah.starkchef.Networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
     private MutableLiveData<List<Recipe>> recipeList;

    //we will call this method in Mainactivity  to get the data
    public LiveData<List<Recipe>> getRecipes(){
        //if list is null
        if (recipeList==null){
            recipeList= new MutableLiveData<List<Recipe>>();
            //we will load it asynchronously from server in this method
            loadRecipes();
        }
        return recipeList;
    }

    private void loadRecipes() {
        //ccreating api interface
        Api api= RetrofitClient.getInstance().create(Api.class);


        //now making the call object
        //Here we are using the api method that we created inside the api interface
        retrofit2.Call<List<Recipe>> call=api.getRecipies();
        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                     recipeList.setValue(response.body());
                Log.d("network success","calling network");
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }
}
