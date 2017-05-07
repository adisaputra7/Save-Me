package com.example.nurhadis.sakuria1.Activities;

import android.accounts.AccountAuthenticatorActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nurhadis.sakuria1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRegister;
    private EditText textName;
    private EditText textEmail;
    private EditText textPassword;
    private EditText textPhone;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference mDatabaseRef;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        mRegister = (Button) findViewById(R.id.signUp);
        textName = (EditText) findViewById(R.id.fullName);
        textEmail = (EditText) findViewById(R.id.email);
        textPassword = (EditText) findViewById(R.id.password);
        textPhone = (EditText) findViewById(R.id.phone);

        mRegister.setOnClickListener(this);

        //Assign Instance
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

    }

    private void registerUser() {
        final String name, email, password, phone;

         name = textName.getText().toString().trim();
         email = textEmail.getText().toString().trim();
         password = textPassword.getText().toString().trim();
         phone = textPhone.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email",Toast.LENGTH_SHORT).show();
            //stopping the function execution further;
            return;
        }
        if (TextUtils.isEmpty(name)){
            //email is empty
            Toast.makeText(this, "Please enter Fullname",Toast.LENGTH_SHORT).show();
            //stopping the function execution further;
            return;
        }
        if (TextUtils.isEmpty(password)){
            //email is empty
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            //stopping the function execution further;
            return;
        }
        if (TextUtils.isEmpty(phone)){
            //email is empty
            Toast.makeText(this, "Please enter phone",Toast.LENGTH_SHORT).show();
            //stopping the function execution further;
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //user is succesfull registered and logged in
                            //we will start the profile activity here
                            //and display toast
                            DatabaseReference mChildDatabase = mDatabaseRef.child("Users").push();

                            String key_user= mChildDatabase.getKey();

                            mChildDatabase.child("keyUser").setValue(key_user);// set User key in database
                            mChildDatabase.child("emailUser").setValue(email);// set email in database
                            mChildDatabase.child("pwdUser").setValue(password);// set password in database
                            mChildDatabase.child("fullname").setValue(name);
                            mChildDatabase.child("phone").setValue(phone);

                            Toast.makeText(AccountActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(AccountActivity.this, Summary.class));

                        }
                        else {

                            Toast.makeText(AccountActivity.this, "Couldn't register, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

        @Override
    public void onClick(View view) {
        registerUser();
    }
}