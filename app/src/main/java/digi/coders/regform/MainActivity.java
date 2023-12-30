package digi.coders.regform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton radioButton, radiomail, radiofemail, radioOther;
    Button button, save;

    TextInputEditText firstName, secondName, email, password, dob, mobile;
    CheckBox first_chechbox, second_chechbox, third_chechbox;
    String flag = "";
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = findViewById(R.id.sp);
        button = findViewById(R.id.register);
        save = findViewById(R.id.show);

        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        first_chechbox = findViewById(R.id.first_chechbox);
        second_chechbox = findViewById(R.id.second_chechbox);
        third_chechbox = findViewById(R.id.third_chechbox);
        mobile = findViewById(R.id.mobile);

        radioGroup = findViewById(R.id.radioGroup);
        radiomail = findViewById(R.id.radioMail);
        radiofemail = findViewById(R.id.radioFemail);
        radioOther = findViewById(R.id.radioOther);

        arrayList.add("State");
        arrayList.add("Andhra Pradesh");
        arrayList.add("Arunachal Pradesh");
        arrayList.add("Bihar");
        arrayList.add("Goa");
        arrayList.add("Uttar Pradesh");
        arrayList.add("Haryana");
        arrayList.add("Punjab");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(adapter);

        Intent a = getIntent();

        flag = a.getStringExtra("flag");

        if (flag.equalsIgnoreCase("UPDATE")) {
            button.setText("UPDATE");
            Updatefirebasedata();

        } else if (flag.equalsIgnoreCase("SUBMIT")) {
            button.setText("SUBMIT");
        }

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPicker();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertdataFirebase();
            }

        });
    }


    void Updatefirebasedata() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Student Data");
        String mob = getIntent().getStringExtra("number").toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Log.d("Updatefirebasedata", "exists");


                    for (DataSnapshot snapshot1:snapshot.getChildren()){
                        DataModel dataModel = snapshot1.getValue(DataModel.class);
                        if (dataModel.getMobileNo().equalsIgnoreCase(""+mob)){
                            firstName.setText(""+dataModel.getFname());
                            secondName.setText("" + dataModel.getSname());
                            email.setText("" + dataModel.getEemail());
                            password.setText("" + dataModel.getPpassword());
                            dob.setText("" + dataModel.getDdob());
                            first_chechbox.setText("" + dataModel.getF_check());
                            second_chechbox.setText("" + dataModel.getS_check());
                            third_chechbox.setText("" + dataModel.getT_check());
                            mobile.setText("" + dataModel.getMobileNo());
                            mobile.setFocusable(false);
                            mobile.setInputType(InputType.TYPE_NULL);

                            if (dataModel.getR_radio().equalsIgnoreCase("Mail")){
                                radiomail.isChecked();
                            }
                            else if (dataModel.getR_radio().equalsIgnoreCase("Femail")){
                                radiofemail.isChecked();
                            }
                            else if (dataModel.getR_radio().equalsIgnoreCase("Other")){
                                radioOther.isChecked();
                            }

//                            firstName.setText("");
//                            secondName.setText("");
//                            email.setText("");
//                            password.setText("");
//                            dob.setText("");
//                            mobile.setText("");
//                            radioButton.setText("");
//                            first_chechbox.setText("");
//                            second_chechbox.setText("");
//                            third_chechbox.setText("");

                        }
                    }



                }else {
                    Log.d("Updatefirebasedata", "not exists");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Updatefirebasedata", "error" + error.getMessage());
            }
        });

    }

    void InsertdataFirebase() {

        String f_name = firstName.getText().toString().trim();
        String s_name = secondName.getText().toString().trim();
        String e_mail = email.getText().toString().trim();
        String p_password = password.getText().toString().trim();
        String d_dob = dob.getText().toString().trim();
        String number = mobile.getText().toString().trim();

        radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        String radio = radioButton.getText().toString();

        String check1 = "";
        String check2 = "";
        String check3 = "";

        if (first_chechbox.isChecked()) {
            check1 = first_chechbox.getText().toString().trim();
        } else if (second_chechbox.isChecked()) {
            check2 = second_chechbox.getText().toString().trim();
        } else if (third_chechbox.isChecked()) {
            check3 = third_chechbox.getText().toString().trim();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Student Data");

        DataModel model = new DataModel(f_name, s_name, e_mail, p_password, d_dob, check1, check2, check3, radio, number);
        reference.child(number).setValue(model);

        if (f_name.equalsIgnoreCase("") || f_name == null) {
            Toast.makeText(MainActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        } else if (s_name.equalsIgnoreCase("") || s_name == null) {
            Toast.makeText(MainActivity.this, "Enter Second Name", Toast.LENGTH_SHORT).show();
            return;
        } else if (e_mail.equalsIgnoreCase("") || e_mail == null) {
            Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        } else if (p_password.equalsIgnoreCase("") || p_password == null) {
            Toast.makeText(MainActivity.this, "Strong Password", Toast.LENGTH_SHORT).show();
            return;
        } else if (d_dob.equalsIgnoreCase("") || d_dob == null) {
            Toast.makeText(MainActivity.this, "Date of Birth", Toast.LENGTH_SHORT).show();
            return;
        } else if (number.equalsIgnoreCase("") || number == null) {
            Toast.makeText(MainActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }

        firstName.setText("");
        secondName.setText("");
        email.setText("");
        password.setText("");
        dob.setText("");
        mobile.setText("");
        radioButton.setText("");
        first_chechbox.setText("");
        second_chechbox.setText("");
        third_chechbox.setText("");
    }

    void DataPicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int days = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        },
                year, month, days);
        datePickerDialog.show();
    }
}
