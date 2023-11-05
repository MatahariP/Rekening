package com.example.rekening.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rekening.R;
import com.example.rekening.db.DbHelper;
import com.example.rekening.model.Rekening;

public class UpdateActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private EditText etName, etRekening;
    private Button btnSave;
    private Rekening rekening;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbHelper = new DbHelper(this);
        etName = findViewById(R.id.edt_name);
        etRekening = findViewById(R.id.edt_rekening);
        btnSave = findViewById(R.id.btn_submit);
        Intent intent = getIntent();
        rekening = (Rekening) intent.getSerializableExtra("user");
        etName.setText(rekening.getName());
        etRekening.setText(rekening.getRekening());
        btnSave.setOnClickListener((View v) -> {
            dbHelper.updateUser(rekening.getId(), etRekening.getText().toString(), etName.getText().toString());

            Toast.makeText(UpdateActivity.this, "Updated berhasil!",
                    Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}