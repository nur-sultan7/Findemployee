package com.example.findemployee.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {
    private static  EmployeesDatabase database;
    private LiveData<List<Employee>> employees;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database=EmployeesDatabase.getInstance(getApplication());
        employees=database.employeeDao().getAllEmployees();
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public Speciality getSpeciality(int id)
    {
        try {
            return new GetSpecialityByIdTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Employee getEmployeeById(int id)
    {
        try {
            return new GetEmployeeTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteAll()
    {
        new DeleteAll().execute();
    }
    public void insertSpeciality(Speciality speciality)
    {
        new InsertSpeciality().execute(speciality);
    }
    public void insertEmployee(Employee employee)
    {
        new InsertEmployee().execute(employee);
    }
    public void deleteEmployee(Employee employee)
    {
        new DeleteEmployee().execute(employee);
    }

    private static class DeleteEmployee extends AsyncTask<Employee,Void,Void>
    {

        @Override
        protected Void doInBackground(Employee... employees) {
            if (employees[0]!=null)
                database.employeeDao().deleteEmployee(employees[0]);
            return null;
        }
    }

    private static class InsertEmployee extends AsyncTask<Employee,Void,Void>
    {

        @Override
        protected Void doInBackground(Employee... employees) {
            if (employees[0]!=null)
                database.employeeDao().insertEmployee(employees[0]);
            return null;
        }
    }

    private static class InsertSpeciality extends AsyncTask<Speciality,Void,Void>
    {

        @Override
        protected Void doInBackground(Speciality... specialities) {
            if (specialities!=null && specialities.length>0)
                database.employeeDao().insertSpeciality(specialities[0]);
            return null;
        }
    }
    private static class GetEmployeeTask extends AsyncTask<Integer,Void,Employee>
    {

        @Override
        protected Employee doInBackground(Integer... integers) {
            Employee employee=null;
            if (integers!=null && integers.length>0)
                employee=database.employeeDao().getEmployeeById(integers[0]);
            return employee;
        }
    }
    private static class GetSpecialityByIdTask extends AsyncTask<Integer, Void, Speciality> {

        @Override
        protected Speciality doInBackground(Integer... ids) {
            Speciality speciality=null;
            if (ids!=null && ids.length>0)
            {
                speciality=database.employeeDao().getSpecialityById(ids[0]);
            }
            return speciality;
        }
    }

    private static class DeleteAll extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            database.employeeDao().deleteAllEmployees();
            database.employeeDao().deleteAllSpecialities();
            return null;
        }
    }

}
