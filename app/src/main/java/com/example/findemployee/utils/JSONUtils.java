package com.example.findemployee.utils;

import com.example.findemployee.data.Employee;
import com.example.findemployee.data.Speciality;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    private static String KEY_FIRST_NAME = "f_name";
    private static String KEY_LAST_NAME = "l_name";
    private static String KEY_BIRTHDAY = "birthday";
    private static String KEY_AVATAR = "avatr_url";
    private static String KEY_JSON_SPECIALITY = "specialty";
    private static String KEY_SPECIALITY_ID = "specialty_id";
    private static String KEY_SPECIALITY_NAME = "name";

    public static ArrayList<Employee> getArrayJSON(JSONArray jsonArray) throws JSONException {
        ArrayList<Employee> arrayList = new ArrayList<>();
        if (jsonArray==null)
        {
            return null;
        }
        for (int i=0;i<jsonArray.length();i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String first_name = jsonObject.getString(KEY_FIRST_NAME);
            String last_name = jsonObject.getString(KEY_LAST_NAME);
            String birthday = jsonObject.getString(KEY_BIRTHDAY);
            String avatar_url = jsonObject.getString(KEY_AVATAR);
            JSONArray specialityJSON = jsonObject.getJSONArray(KEY_JSON_SPECIALITY);
            List<Speciality> specialityList = new ArrayList<>();
            for (int j=0;i<specialityJSON.length();i++)
            {
                specialityList.add(new Speciality(specialityJSON.getJSONObject(j).getInt(KEY_SPECIALITY_ID),specialityJSON.getJSONObject(j).getString(KEY_SPECIALITY_NAME)));
            }

            arrayList.add(new Employee(first_name,last_name,birthday,avatar_url,specialityList));
        }
        return arrayList;

    }
}
