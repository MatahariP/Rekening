package com.example.rekening.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rekening.adapter.ListRekeningsActivity;
import com.example.rekening.model.Rekening;
import com.example.rekening.R;
import com.example.rekening.adapter.RekeningAdapter;
import com.example.rekening.db.DbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RekeningAdapter adapter;
    private ArrayList<Rekening> rekeningsArrayList;
    private DbHelper dbHelper;
    private Button btnTambahRekening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rview);
        adapter = new RekeningAdapter(this);
        dbHelper = new DbHelper(this);
        rekeningsArrayList = dbHelper.getAllUsers();
        adapter.setListRekenings(rekeningsArrayList);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        btnTambahRekening = findViewById(R.id.btnTambahRekening);
        btnTambahRekening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListRekeningsActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        rekeningsArrayList = dbHelper.getAllUsers();
        adapter.setListRekenings(rekeningsArrayList);
        adapter.notifyDataSetChanged();
    }
}

