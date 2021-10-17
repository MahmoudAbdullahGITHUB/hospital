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
import com.example.ehdqsv2.HospitaType1Activity;
import com.example.ehdqsv2.HospitalActivity;
import com.example.ehdqsv2.R;

import java.util.ArrayList;

public class DepartmentsListAdapter extends RecyclerView.Adapter<DepartmentsListAdapter.HospitalsViewHolder> {

    private ArrayList<DepartmentModel.DataModel> departmentsList = new ArrayList<>();
    Context context;
    int hospitalId;

    public DepartmentsListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HospitalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.department_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalsViewHolder holder, int position) {
        holder.departmentTextView.setText(departmentsList.get(position).name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, HospitaType1Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("hospitalId", String.valueOf(hospitalId));
                bundle.putString("departmentId", departmentsList.get(position).id);
                intent.putExtras(bundle);
                context.startActivity(intent);

//                Intent myIntent = new Intent(context, HospitalActivity.class);
////                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(myIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return departmentsList.size();
    }


    public void setDepartmentsList( ArrayList<DepartmentModel.DataModel> hospitalsList){
        this.departmentsList = hospitalsList;
        notifyDataSetChanged();
    }


    public class HospitalsViewHolder extends RecyclerView.ViewHolder {
        TextView departmentTextView;
        CardView cardView;
        public HospitalsViewHolder(@NonNull View itemView) {
            super(itemView);

            departmentTextView = itemView.findViewById(R.id.department_item_name);
            cardView = itemView.findViewById(R.id.department_id);
        }
    }
}
