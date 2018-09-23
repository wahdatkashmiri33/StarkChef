package com.chef.emzah.starkchef.UI.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chef.emzah.starkchef.Adapters.RecipeIngredientAdapter;
import com.chef.emzah.starkchef.Adapters.StepAdapter;
import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredietFragment extends Fragment implements StepAdapter.OnitemclickListener{
    @BindView(R.id.stepsrecyclerview) RecyclerView recyclerViewSteps;
    @BindView(R.id.ingredientsrecyclerview) RecyclerView recyclerView;
List<Ingredient> ingredientList;
List<Step> stepList;
StepAdapter stepAdapter;
    RecipeIngredientAdapter adapter;
    public ItemClickListener itemClickListener;
    public IngredietFragment() {
    }

    public interface ItemClickListener{
        void OnClickednstep(int position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ingredients_fragment,container,false);

     ingredientList=getActivity().getIntent().getParcelableArrayListExtra("ingredientsList");
        ButterKnife.bind(this,view);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new RecipeIngredientAdapter(ingredientList,getContext());

        if (adapter.getItemCount()==0){
            Toast.makeText(getContext(), "kuch nhi ha adapter ma", Toast.LENGTH_SHORT).show();
        }
        else {
            recyclerView.setAdapter(adapter);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        stepList=getActivity().getIntent().getParcelableArrayListExtra("stepsList");
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerViewSteps.setLayoutManager(linearLayoutManager);
        recyclerViewSteps.setHasFixedSize(true);
        stepAdapter=new StepAdapter(stepList,this,getContext());
        recyclerViewSteps.setAdapter(stepAdapter);
        recyclerViewSteps.getAdapter().notifyDataSetChanged();


       return view;

    }

    @Override
    public void itemClick(int position) {
           //itemClickListener.OnClickednstep(position);
        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
        StepsFragment stepsFragment=new StepsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("positionsteps",position);
        stepsFragment.setArguments(bundle);
        Bundle stepslist=new Bundle();
        stepslist.putParcelableArrayList("steplist",new ArrayList<Parcelable>(stepList));
        stepsFragment.setArguments(stepslist);
        stepsFragment.setCurrentStep(position);

        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,stepsFragment)
                .addToBackStack(null).commit();


    }
}
