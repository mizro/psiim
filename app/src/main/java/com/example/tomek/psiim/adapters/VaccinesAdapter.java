package com.example.tomek.psiim.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomek.psiim.R;
import com.example.tomek.psiim.Vaccine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 2016-10-22.
 */
public class VaccinesAdapter extends RecyclerView.Adapter<VaccinesAdapter.VaccinesViewHolder> {


    private ArrayList<Vaccine> vaccines;


    public VaccinesAdapter() {
        this.vaccines = new ArrayList<>();
    }

    public static class VaccinesViewHolder extends RecyclerView.ViewHolder {

        android.content.Context context;
        CardView cvVaccines;
        TextView tvVaccineType;
        TextView tvVaccineNextdate;


        public VaccinesViewHolder(View view) {
            super(view);
            context = view.getContext();
            tvVaccineType = (TextView) view.findViewById(R.id.tv_vaccine_type);
            tvVaccineNextdate = (TextView) view.findViewById(R.id.tv_vaccine_nextdate);
            cvVaccines = (CardView) view.findViewById(R.id.cv_vaccines);
        }

    }

    @Override
    public VaccinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vaccines, parent, false);

        return new VaccinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VaccinesViewHolder holder, int position) {
        final Vaccine vaccine = vaccines.get(position);
        holder.tvVaccineType.setText(vaccine.getType());
        holder.tvVaccineNextdate.setText(vaccine.getNextdate());


//        holder.cvAnimals.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(holder.context, ShowAnimalDetails.class);
//                intent.putExtra(ShowAnimalDetails.ANIMAL_NAME, vaccine.getName());
//                holder.context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return vaccines.size();
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines.clear();
        this.vaccines.addAll(vaccines);
        notifyDataSetChanged();
    }
}


