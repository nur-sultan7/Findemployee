package com.example.findemployee.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findemployee.R;
import com.example.findemployee.data.Employee;

import java.util.ArrayList;

public class EmployeesAdapter  extends RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder>{

    private ArrayList<Employee> arrayList = new ArrayList<>();



    public void setArrayList(ArrayList<Employee> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = arrayList.get(position);
        holder.textViewFirstName.setText(employee.getFirst_name());
        holder.textViewLastName.setText(employee.getLast_name());
        String age;
        if (employee.getAge()>0)
        {
            age=String.valueOf(employee.getAge());
        }
        else
            age=holder.itemView.getContext().getString(R.string.no_data);
        holder.textViewAge.setText(age);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFirstName;
        TextView textViewLastName;
        TextView textViewAge;


        EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFirstName = itemView.findViewById(R.id.textViewItemFirstName);
            textViewLastName = itemView.findViewById(R.id.textViewtemLastName);
            textViewAge = itemView.findViewById(R.id.textViewItemAge);

        }
    }
}
