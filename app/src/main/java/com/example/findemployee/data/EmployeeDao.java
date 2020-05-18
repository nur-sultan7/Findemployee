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

    @Query("SELECT * FROM employees WHERE unique_id==:employee_id")
    Employee getEmployeeById(int employee_id);

    @Query("SELECT * FROM specialities_of_employees WHERE employee_id == :employee_id ")
    List<SpecialityOfEmployee> getSpecialitiesOfEmployee(int employee_id);

    @Insert
    long insertEmployee(Employee employee);

    @Insert
    void insertSpecialitiesOfEmployee(List<SpecialityOfEmployee> specialityOfEmployee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("DELETE FROM employees")
    void deleteAllEmployees();

    @Query("Delete FROM specialities")
    void deleteAllSpecialities();

    @Query("Delete from specialities_of_employees")
    void deleteAllSpecialitiesOfEmployees();

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    void insertSpeciality(Speciality speciality);

    @Query("SELECT * FROM specialities WHERE id==:id")
    Speciality getSpecialityById(int id);

    @Query("Select * from specialities where id in (:specialityIdList)")
    List<Speciality> getSpecialityList(List<Integer> specialityIdList);


}
