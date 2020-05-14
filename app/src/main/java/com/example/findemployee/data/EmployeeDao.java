package com.example.findemployee.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employees")
    LiveData<List<Employee>> getAllEmployees();

    @Query("SELECT * FROM employees WHERE uniqueId==:employee_id")
    Employee getEmployeeById(int employee_id);

    @Insert
    void insertEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    void insertSpeciality(Speciality speciality);

    @Query("SELECT * FROM specialities WHERE id==:id")
    Speciality getSpecialityById(int id);


}