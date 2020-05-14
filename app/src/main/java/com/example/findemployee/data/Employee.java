package com.example.findemployee.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Employee {

    private String first_name;
    private String last_name;
    private String birthday;
    private String avatar_url;
    private int age;
    private List<Speciality> specialityList;

    public List<Speciality> getSpecialityList() {
        return specialityList;
    }

    public void setSpecialityList(List<Speciality> specialityList) {
        this.specialityList = specialityList;
    }
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

    public Employee(String first_name, String last_name, String birthday, String avatar_url, List<Speciality> specialityList) {
        this.first_name = first_name.substring(0,1).toUpperCase()+first_name.substring(1).toLowerCase();
        this.last_name = last_name.substring(0,1).toUpperCase()+last_name.substring(1).toLowerCase();
        this.birthday = birthday;
        this.avatar_url = avatar_url;
        this.specialityList=specialityList;
        setAge(birthday);
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
