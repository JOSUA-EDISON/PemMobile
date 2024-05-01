package com.example.eja;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNIK;
    private EditText editTextNamaLengkap;
    private EditText etTanggalLahir;
    private EditText editTextTempatLahir;
    private EditText editTextAlamat;
    private EditText textViewUsia;
    private Spinner spinnerJenisKelamin;
    private RadioGroup radioGroupKewarganegaraan;
    private CheckBox checkBoxDevelopment, checkBoxAI, checkBoxDesign, checkBoxWriting, checkBoxFinance;
    private EditText editTextEmail;
    private Button buttonReset;
    private Button buttonSubmit;
    private Calendar myCalendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNIK = findViewById(R.id.editTextNIK);
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        editTextTempatLahir = findViewById(R.id.editTextTempatLahir);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        textViewUsia = findViewById(R.id.textViewUsia);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        radioGroupKewarganegaraan = findViewById(R.id.radioGroupKewarganegaraan);
        checkBoxDevelopment = findViewById(R.id.checkBoxDevelopment);
        checkBoxAI = findViewById(R.id.checkBoxAI);
        checkBoxDesign = findViewById(R.id.checkBoxDesign);
        checkBoxWriting = findViewById(R.id.checkBoxWriting);
        checkBoxFinance = findViewById(R.id.checkBoxFinance);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonReset = findViewById(R.id.buttonReset);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize Calendar instance
        myCalendar = Calendar.getInstance();

        // Set up OnClickListener for Date EditText
        etTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        // Set OnClickListener for buttonReset
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });

        // Set OnClickListener for buttonSubmit
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        // Initialize DatePickerDialog
        datePickerDialog = new DatePickerDialog(
                this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        );

        // Set min date for DatePickerDialog to today's date
        datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
    }

    private void showDatePickerDialog() {
        datePickerDialog.show();
    }

    // DatePickerDialog.OnDateSetListener
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            calculateAge();
        }
    };

    private void calculateAge() {
        // Get current date
        Calendar today = Calendar.getInstance();

        // Calculate age based on selected date of birth
        int age = today.get(Calendar.YEAR) - myCalendar.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < myCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        // Set the calculated age to the EditText
        textViewUsia.setText(String.valueOf(age));
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        etTanggalLahir.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void resetForm() {
        editTextNIK.setText("");
        editTextNamaLengkap.setText("");
        etTanggalLahir.setText("");
        editTextTempatLahir.setText("");
        editTextAlamat.setText("");
        textViewUsia.setText("");
        spinnerJenisKelamin.setSelection(0);
        radioGroupKewarganegaraan.clearCheck();
        checkBoxDevelopment.setChecked(false);
        checkBoxAI.setChecked(false);
        checkBoxDesign.setChecked(false);
        checkBoxWriting.setChecked(false);
        checkBoxFinance.setChecked(false);
        editTextEmail.setText("");
    }

    private void submitForm() {
        // Mendapatkan data dari form
        String nik = editTextNIK.getText().toString();
        String nama = editTextNamaLengkap.getText().toString();
        String tglLahir = etTanggalLahir.getText().toString();
        String tempatLahir = editTextTempatLahir.getText().toString();
        String alamat = editTextAlamat.getText().toString();
        String usia = textViewUsia.getText().toString();
        String jenisKelamin = spinnerJenisKelamin.getSelectedItem().toString();
        String kewarganegaraan = "";

        int selectedId = radioGroupKewarganegaraan.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            kewarganegaraan = radioButton.getText().toString();
        }

        StringBuilder kompetensi = new StringBuilder();
        if (checkBoxDevelopment.isChecked()) {
            kompetensi.append("Development & IT, ");
        }
        if (checkBoxAI.isChecked()) {
            kompetensi.append("AI Services, ");
        }
        if (checkBoxDesign.isChecked()) {
            kompetensi.append("Design Creative, ");
        }
        if (checkBoxWriting.isChecked()) {
            kompetensi.append("Writing, ");
        }
        if (checkBoxFinance.isChecked()) {
            kompetensi.append("Finance & Accounting");
        }

        String email = editTextEmail.getText().toString();

        // Validate form
        if (nik.isEmpty() || nama.isEmpty() || tglLahir.isEmpty() || tempatLahir.isEmpty() ||
                alamat.isEmpty() || usia.isEmpty() || jenisKelamin.isEmpty() || kewarganegaraan.isEmpty() ||
                kompetensi.toString().isEmpty() || email.isEmpty()) {
            Toast.makeText(MainActivity.this, "Harap lengkapi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            Toast.makeText(MainActivity.this, "Format email tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email domain
        if (!isValidEmailDomain(email)) {
            Toast.makeText(MainActivity.this, "Domain email tidak diizinkan", Toast.LENGTH_SHORT).show();
            return;
        }

        // Membuat intent untuk perpindahan data ke SecondActivity
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // Menambahkan data ke intent
        intent.putExtra("nik", nik);
        intent.putExtra("nama", nama);
        intent.putExtra("tglLahir", tglLahir);
        intent.putExtra("tempatLahir", tempatLahir);
        intent.putExtra("alamat", alamat);
        intent.putExtra("usia", usia);
        intent.putExtra("jenisKelamin", jenisKelamin);
        intent.putExtra("kewarganegaraan", kewarganegaraan);
        intent.putExtra("kompetensi", kompetensi.toString());
        intent.putExtra("email", email);

        // Memulai aktivitas SecondActivity dengan intent yang telah dibuat
        startActivity(intent);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidEmailDomain(String email) {
        String[] allowedDomains = {"@gmail.com", "@mail.com"};
        for (String domain : allowedDomains) {
            if (email.endsWith(domain)) {
                return true;
            }
        }
        return false;
    }
}
