package com.example.findemployee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findemployee.data.Employee;
import com.example.findemployee.data.MainViewModel;
import com.example.findemployee.data.Speciality;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewBirthDay;
    private TextView textViewSpeciality;
    private ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textViewFirstName= findViewById(R.id.textViewFirstName);
        textViewLastName = findViewById(R.id.textViewLastName);
        textViewBirthDay = findViewById(R.id.textViewBirthday);
        textViewSpeciality = findViewById(R.id.textViewSpeciality);
        imageViewAvatar = findViewById(R.id.imageViewAvatarEmployee);


        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (getIntent()!=null)
        {
            int id = getIntent().getIntExtra("id", 0);
            Employee employee = mainViewModel.getEmployeeById(id);
            textViewFirstName.setText(employee.getFirst_name());
            textViewLastName.setText(employee.getLast_name());
            textViewBirthDay.setText(employee.getBirthday());
            Speciality speciality = mainViewModel.getSpeciality(employee.getSpeciality_id());
            textViewSpeciality.setText(speciality.getName());
            Picasso.get().load(employee.getAvatar_url()).into(imageViewAvatar);
        }
    }
}
