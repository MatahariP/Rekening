package com.example.rekening.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rekening.R;
import com.example.rekening.activities.MainActivity;
import com.example.rekening.db.DbHelper;

public class ListRekeningsActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private EditText etdName, edtRekening;
    private Button btn_submit, btn_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rekening);

        dbHelper = new DbHelper(this);
        etdName = findViewById(R.id.edt_name);
        edtRekening = findViewById(R.id.edt_rekening);
        btn_submit = findViewById(R.id.btn_submit);
        btn_list = findViewById(R.id.btn_list);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edtRekening.getText().toString().isEmpty()) {
                    Toast.makeText(ListRekeningsActivity.this, "Error: Rekening harus diisi!", Toast.LENGTH_SHORT).show();
                } else if (etdName.getText().toString().isEmpty()) {
                    Toast.makeText(ListRekeningsActivity.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    if (isNumeric(edtRekening.getText().toString())) {
                        dbHelper.addUserDetail(edtRekening.getText().toString(), etdName.getText().toString());
                        etdName.setText("");
                        edtRekening.setText("");
                        Toast.makeText(ListRekeningsActivity.this, "Simpan berhasil!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ListRekeningsActivity.this, "Error: Rekening harus berupa angka!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            private boolean isNumeric(String str) {
                try {
                    double d = Double.parseDouble(str);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

        });


        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListRekeningsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
