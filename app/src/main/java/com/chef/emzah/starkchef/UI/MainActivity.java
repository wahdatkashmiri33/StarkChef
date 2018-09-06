package com.chef.emzah.starkchef.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.chef.emzah.starkchef.Adapters.RecipeAdapter;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.Networking.Api;
import com.chef.emzah.starkchef.Networking.RetrofitClient;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.ViewModel.RecipeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   @BindView(R.id.recyclerviewrecipe) RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        settingLayoutmanager();
        setupViewmodel();

    }

    private void setupViewmodel() {
        RecipeViewModel viewModel= ViewModelProviders.of(this).get(RecipeViewModel.class);
        viewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                adapter=new RecipeAdapter(recipes,getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    private void settingLayoutmanager() {
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
