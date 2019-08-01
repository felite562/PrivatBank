package com.luba.todo.privatbank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luba.todo.privatbank.parser.ListAdapter;
import com.luba.todo.privatbank.parser.ModelRecycler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TooManyListenersException;

public class SecondActivity extends AppCompatActivity {
    ArrayList<ModelRecycler> modelRecyclerArrayList;
    private ListAdapter retrofitAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        modelRecyclerArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        retrofitAdapter = new ListAdapter(this, modelRecyclerArrayList);
        recyclerView.setAdapter(retrofitAdapter);
        writeRecycler();
    }



    private void writeRecycler() {
        try {
            String str = readFromFile();
            JSONArray dataArray = new JSONArray(str);
            for (int i = 0; i < dataArray.length(); i++) {

                ModelRecycler modelRecycler = new ModelRecycler();
                JSONObject dataobj = dataArray.getJSONObject(i);

                modelRecycler.setCcy(dataobj.getString("ccy"));
                modelRecycler.setbase_ccy(dataobj.getString("base_ccy"));
                modelRecycler.setBuy(dataobj.getString("buy"));
                modelRecycler.setSale(dataobj.getString("sale"));

                modelRecyclerArrayList.add(modelRecycler);
                retrofitAdapter.notifyDataSetChanged();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String readFromFile() {
        String ret = "";
        InputStream inputStream = null;
        try {
            inputStream = openFileInput("privat.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("activity", "Can not read file: " + e.toString());
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }


    }
