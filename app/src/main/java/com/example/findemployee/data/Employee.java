package com.example.findemployee.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "employees")
public class Employee {
    @PrimaryKey(autoGenerate = true)
   private int unique_Id;
    private String first_name;
    private String last_name;
    private String birthday;
    private String avatar_url;
    private int age;
    private int speciality_id;

    public Employee(int unique_Id, String first_name, String last_name, String birthday, String avatar_url, int age, int speciality_id) {
        this.unique_Id = unique_Id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.avatar_url = avatar_url;
        this.age = age;
        this.speciality_id = speciality_id;
    }
    @Ignore
    public Employee( String first_name, String last_name, String birthday, String avatar_url,  int speciality_id) {
        this.unique_Id = unique_Id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.avatar_url = avatar_url;
        this.speciality_id = speciality_id;
        setAge(birthday);
    }

    public int getSpeciality_id() {
        return speciality_id;
    }

    public int getUnique_Id() {
        return unique_Id;
    }

    public void setUnique_Id(int unique_Id) {
        this.unique_Id = unique_Id;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public void setSpeciality_id(int speciality_id) {
        this.speciality_id = speciality_id;
    }

    @Ignore
    private void setAge(String birthday)
    {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(birthday);
            if (date.getYear()<0)
                date = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault()).parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date!=null) {
            int year = date.getYear();
            Date currentDate = new Date();
            int currentyear = currentDate.getYear();
            age = currentyear - year-1;
            if (currentDate.getMonth()>=date.getMonth() && currentDate.getDay()>=date.getDay())
            {
                age++;
            }

        }
        else
        {
            age=0;
        }
    }

    public int getAge() {

        return age;
    }



    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

}
