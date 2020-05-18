package com.example.findemployee.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.findemployee.R;
import com.example.findemployee.data.Speciality;
import com.example.findemployee.data.SpecialityOfEmployee;

import java.util.ArrayList;
import java.util.List;

public class SpecialityOfEmployeeAdapter extends Adapter<SpecialityOfEmployeeAdapter.ViewHolder>  {
    List<Speciality> specialityOfEmployeeList = new ArrayList<>();

    public List<Speciality> getSpecialityOfEmployeeList() {
        return specialityOfEmployeeList;
    }

    public void setSpecialityOfEmployeeList(List<Speciality> specialityOfEmployeeList) {
        this.specialityOfEmployeeList = specialityOfEmployeeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality_of_employee,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Speciality specialityOfEmployee = specialityOfEmployeeList.get(position);
        holder.textViewSpeciality.setText(specialityOfEmployee.getName());
    }

    @Override
    public int getItemCount() {
        return specialityOfEmployeeList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder
     {
         TextView textViewSpeciality;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             textViewSpeciality = itemView.findViewById(R.id.textViewSpeciality);
         }
     }
}
