package com.example.findemployee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findemployee.adapters.SpecialityOfEmployeeAdapter;
import com.example.findemployee.data.Employee;
import com.example.findemployee.data.MainViewModel;
import com.example.findemployee.data.Speciality;
import com.example.findemployee.data.SpecialityOfEmployee;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewBirthDay;
    private TextView textViewSpeciality;
    private ImageView imageViewAvatar;
    private List<Speciality> listLiveData;
    private RecyclerView recyclerView;
    private SpecialityOfEmployeeAdapter specialityOfEmployeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textViewFirstName= findViewById(R.id.textViewFirstName);
        textViewLastName = findViewById(R.id.textViewLastName);
        textViewBirthDay = findViewById(R.id.textViewBirthday);
        textViewSpeciality = findViewById(R.id.textViewSpeciality);
        imageViewAvatar = findViewById(R.id.imageViewAvatarEmployee);
        recyclerView = findViewById(R.id.recyclerViewSpecialityOfEmployee);
        specialityOfEmployeeAdapter = new SpecialityOfEmployeeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(specialityOfEmployeeAdapter);



        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (getIntent()!=null)
        {
            int id = getIntent().getIntExtra("id", 0);
            Employee employee = mainViewModel.getEmployeeById(id);
            textViewFirstName.setText(employee.getFirst_name());
            textViewLastName.setText(employee.getLast_name());
            if (employee.getBirthday()!=null && !employee.getBirthday().equals("null"))
            textViewBirthDay.setText(employee.getBirthday());
            else
                textViewBirthDay.setText(getResources().getString(R.string.no_data));
            listLiveData = mainViewModel.getSpecialitiesOfEmployeeListById(id);
            specialityOfEmployeeAdapter.setSpecialityOfEmployeeList(listLiveData);
            if (employee.getAvatar_url()!=null && employee.getAvatar_url().length()>0)
            Picasso.get().load(employee.getAvatar_url()).into(imageViewAvatar);
            else
                imageViewAvatar.setImageResource(R.drawable.ic_interfaces);
        }
    }
}
