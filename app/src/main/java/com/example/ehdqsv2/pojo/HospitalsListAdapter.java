package com.example.ehdqsv2.pojo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ehdqsv2.AllDepartmentsActivity;
import com.example.ehdqsv2.AllHospitalsActivity;
import com.example.ehdqsv2.HospitaType1Activity;
import com.example.ehdqsv2.HospitalActivity;
import com.example.ehdqsv2.R;

import java.util.ArrayList;

public class HospitalsListAdapter extends RecyclerView.Adapter<HospitalsListAdapter.HospitalsViewHolder> {

    private ArrayList<HospitalModel.DataModel> hospitalsList = new ArrayList<>();
    Context context;

    public HospitalsListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HospitalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalsViewHolder holder, int position) {
        holder.hospitalTextView.setText(hospitalsList.get(position).name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(context, AllDepartmentsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("hospitalId", hospitalsList.get(position).id);
                intent.putExtras(bundle);
                context.startActivity(intent);

//                Intent myIntent = new Intent(context, AllDepartmentsActivity.class);
//
////                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(myIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalsList.size();
    }


    public void setHospitalsList(ArrayList<HospitalModel.DataModel> hospitalsList) {
        this.hospitalsList = hospitalsList;
        notifyDataSetChanged();
    }


    public class HospitalsViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalTextView;
        CardView cardView;

        public HospitalsViewHolder(@NonNull View itemView) {
            super(itemView);

            hospitalTextView = itemView.findViewById(R.id.hospital_item_name);
            cardView = itemView.findViewById(R.id.hospital_id);
        }
    }
}
