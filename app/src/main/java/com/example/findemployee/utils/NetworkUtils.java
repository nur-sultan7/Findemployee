package com.example.findemployee.utils;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.loader.content.AsyncTaskLoader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {
    private static String BASE_URL ="https://gitlab.65apps.com/65gb/static/raw/master/testTask.json";
    public static class AsyncJSONLoader extends AsyncTaskLoader<JSONArray>
    {

        public AsyncJSONLoader(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Nullable
        @Override
        public JSONArray loadInBackground() {
            JSONArray result =null;
            URL url = null;
            try {
                url = new URL(BASE_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (url==null)
            {
                return null;
            }
            HttpsURLConnection connection=null;
            try {
                connection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line!=null)
                {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                result = jsonObject.getJSONArray("response");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            finally {
                if (connection!=null)
                {
                    connection.disconnect();
                }
            }

            return result;
        }
    }
}
