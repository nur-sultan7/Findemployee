package com.example.findemployee.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "specialities_of_employees")
public class SpecialityOfEmployee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int speciality_id;
    private int employee_id;

    public SpecialityOfEmployee(int id, int speciality_id, int employee_id) {
        this.id = id;
        this.speciality_id = speciality_id;
        this.employee_id = employee_id;
    }

    @Ignore
    public SpecialityOfEmployee(int speciality_id, int employee_id) {
        this.speciality_id = speciality_id;
        this.employee_id = employee_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(int speciality_id) {
        this.speciality_id = speciality_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
}
