package com.example.findemployee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import com.example.findemployee.adapters.EmployeesAdapter;
import com.example.findemployee.data.Employee;
import com.example.findemployee.data.MainViewModel;
import com.example.findemployee.data.Speciality;
import com.example.findemployee.utils.JSONUtils;
import com.example.findemployee.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<JSONArray> {

    private static final int LOADER_ID = 145;
    private LoaderManager loaderManager;
    private EmployeesAdapter employeesAdapter;
    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    private boolean firstTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewEmployees);
        employeesAdapter = new EmployeesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(employeesAdapter);
        loaderManager = androidx.loader.app.LoaderManager.getInstance(this);
        loaderManager.restartLoader(LOADER_ID,null,this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        LiveData<List<Employee>> listLiveData = mainViewModel.getEmployees();
        listLiveData.observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
               if (!firstTime)
               {
                   employeesAdapter.setArrayList(employees);
                   firstTime=true;
               }
            }
        });

    }


    @NonNull
    @Override
    public Loader<JSONArray> onCreateLoader(int id, @Nullable Bundle args) {
        NetworkUtils.AsyncJSONLoader jsonLoader = new NetworkUtils.AsyncJSONLoader(this);
        return jsonLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<JSONArray> loader, JSONArray data) {
        try {
            ArrayList<Employee> employeeArrayList = JSONUtils.getArrayJSON(data, mainViewModel);
            if (employeeArrayList!=null && employeeArrayList.size()>0)
            {
                employeesAdapter.setArrayList(employeeArrayList);
            }
            loaderManager.destroyLoader(LOADER_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<JSONArray> loader) {

    }
}
