package com.example.eja;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Mendapatkan data dari Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nik = extras.getString("nik");
            String nama = extras.getString("nama");
            String tglLahir = extras.getString("tglLahir");
            String tempatLahir = extras.getString("tempatLahir");
            String alamat = extras.getString("alamat");
            String usia = extras.getString("usia");
            String jenisKelamin = extras.getString("jenisKelamin");
            String kewarganegaraan = extras.getString("kewarganegaraan");
            String kompetensi = extras.getString("kompetensi");
            String email = extras.getString("email");

            // Menampilkan data pada TextViews
            TextView textViewNik = findViewById(R.id.textViewNik);
            textViewNik.setText(nik);

            TextView textViewNama = findViewById(R.id.textViewNama);
            textViewNama.setText(nama);

            TextView textViewTglLahir = findViewById(R.id.textViewTglLahir);
            textViewTglLahir.setText(tglLahir);

            TextView textViewTempatLahir = findViewById(R.id.textViewTempatLahir);
            textViewTempatLahir.setText(tempatLahir);

            TextView textViewAlamat = findViewById(R.id.textViewAlamat);
            textViewAlamat.setText(alamat);

            TextView textViewUsia = findViewById(R.id.textViewUsia);
            textViewUsia.setText(usia);

            TextView textViewJenisKelamin = findViewById(R.id.textViewJenisKelamin);
            textViewJenisKelamin.setText(jenisKelamin);

            TextView textViewKewarganegaraan = findViewById(R.id.textViewKewarganegaraan);
            textViewKewarganegaraan.setText(kewarganegaraan);

            TextView textViewKompetensi = findViewById(R.id.textViewKompetensi);
            textViewKompetensi.setText(kompetensi);

            TextView textViewEmail = findViewById(R.id.textViewEmail);
            textViewEmail.setText(email);

            Button btnBack = findViewById(R.id.btnBack);
            btnBack.setOnClickListener(v -> finish());
        }
    }
}
