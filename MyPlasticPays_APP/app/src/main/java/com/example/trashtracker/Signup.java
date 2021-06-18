package com.example.trashtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //Variables
    TextInputLayout regName, regUserName,regEmail,regPhoneNo,regPassword;
    Button regBtn,regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Hooks to all elements in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUserName = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.btn1);
        regToLoginBtn = findViewById(R.id.signup_screen);


        //Save data in firebase on button click
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //Get all the values
                String name = regName.getEditText().getText().toString();
                String username = regUserName.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo= regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNo,password);
                reference.child(phoneNo).setValue(helperClass);
            }
        });
    }
}