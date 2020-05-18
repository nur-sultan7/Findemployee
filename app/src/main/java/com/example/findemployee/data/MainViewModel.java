package com.example.findemployee.data;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {
    private static  EmployeesDatabase database;
    private LiveData<List<Employee>> employees;
    private List<SpecialityOfEmployee> specialitiesOfEmployeeList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database=EmployeesDatabase.getInstance(getApplication());
        employees=database.employeeDao().getAllEmployees();
    }


    public void insertSpecialityOfEmployee(List<SpecialityOfEmployee> specialityOfEmployee)
    {
        new InsertSpecialityOfEmployee().execute(specialityOfEmployee);
    }
    private static class InsertSpecialityOfEmployee extends AsyncTask<List<SpecialityOfEmployee>,Void, Void>
    {
        @Override
        protected Void doInBackground(List<SpecialityOfEmployee>... lists) {
            if (lists[0]!=null)
            database.employeeDao().insertSpecialitiesOfEmployee(lists[0]);
            return null;
        }
    }

    public List<Speciality> getSpecialitiesOfEmployeeListById(int employee_id)
    {
        try {
            return new GetSpecialitiesOfEmployeeTask().execute(employee_id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class GetSpecialitiesOfEmployeeTask extends AsyncTask<Integer,Void,List<Speciality>>
    {
        @Override
        protected List<Speciality> doInBackground(Integer... integers) {
            List<SpecialityOfEmployee> specialityOfEmployeeList = null;
            List<Integer> specialityIdList= new ArrayList<>();
            List<Speciality> specialityList=null;
            if (integers[0]!=null)
            {
                specialityOfEmployeeList=database.employeeDao().getSpecialitiesOfEmployee(integers[0]);
                for (SpecialityOfEmployee specialityOfEmployee: specialityOfEmployeeList )
                {
                    specialityIdList.add(specialityOfEmployee.getSpeciality_id());
                }
                specialityList=database.employeeDao().getSpecialityList(specialityIdList);

            }

            return specialityList;
        }
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
    public long insertEmployee(Employee employee) throws ExecutionException, InterruptedException {
        return new InsertEmployee().execute(employee).get();
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

    private static class InsertEmployee extends AsyncTask<Employee,Void,Long>
    {
        @Override
        protected Long doInBackground(Employee... employees) {
            if (employees[0]!=null)
            return database.employeeDao().insertEmployee(employees[0]);
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
            database.employeeDao().deleteAllSpecialitiesOfEmployees();
            database.employeeDao().deleteAllEmployees();
            database.employeeDao().deleteAllSpecialities();
            return null;
        }
    }

}
