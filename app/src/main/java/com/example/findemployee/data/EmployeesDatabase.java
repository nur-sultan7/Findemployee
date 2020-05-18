package com.example.findemployee.data;

import android.content.Context;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Speciality.class,Employee.class, SpecialityOfEmployee.class} , version = 3, exportSchema = false)
public abstract class EmployeesDatabase extends RoomDatabase {
    private static EmployeesDatabase database;
    private static final String DB_NAME="employees.db";
    private static final Object LOCK = new Object();

    public static EmployeesDatabase getInstance(Context context)
    {
        synchronized (LOCK)
        {
            if (database==null)
            {
                database= Room.databaseBuilder(context,EmployeesDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();
            }
        }
        return database;
    }

    public abstract EmployeeDao employeeDao();
}
