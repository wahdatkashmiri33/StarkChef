package com.chef.emzah.starkchef.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder>  {

    private List<Step> steps;
    private Context context;
    private OnitemclickListener onitemclickListener;

    public List<Recipe>recipeList;
    public StepAdapter(List<Step> steps,OnitemclickListener onitemclickListener, Context context) {
        this.steps = steps;
        this.context = context;
        this.onitemclickListener=onitemclickListener;
    }

    public interface OnitemclickListener{
        void itemClick(int position);
    }
    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list,parent,false);
       return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StepViewHolder holder, final int position) {

        final Step step=steps.get(position);
        holder.stepsofRecipe.setText(position+1 + " . " + step.getShortDescription());

        holder.cardViewSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onitemclickListener.itemClick(position);





               // StepsFragment stepsFragment=new StepsFragment();
               // Bundle bundle=new Bundle();
               // bundle.putInt("positionsteps",position);
              //  stepsFragment.setArguments(bundle);

             // Intent intent=new Intent(context,Steps.class);
             // intent.putParcelableArrayListExtra("stepsList",new ArrayList<Parcelable>(steps));
             //   Log.d("positon test",""+position);
            // intent.putExtra("stepslist",position);
            //  context.startActivity(intent);
              //  Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (steps==null){
            return 0;
        }
        else {
          return steps.size();
        }
    }

    public class StepViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardviewSteps) CardView cardViewSteps;
        @BindView(R.id.stepsofrecipe) TextView stepsofRecipe;
        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
