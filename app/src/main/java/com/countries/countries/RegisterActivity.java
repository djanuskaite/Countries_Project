package com.countries.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Create an Account");

        final EditText setName = findViewById(R.id.setName);
        final EditText setEmail = findViewById(R.id.setEmail);
        final EditText setPassword = findViewById(R.id.setPassword);

        Button registerBtn1 = findViewById(R.id.registerBtn1);

        registerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = setName.getText().toString();
                String email = setEmail.getText().toString();
                String password = setPassword.getText().toString();

                setEmail.setError(null);
                // email vaidation
                if(!Validation.isValidEmail(email)) {
                    setEmail.setError(getResources().getString(R.string.invalid_email));
                    setEmail.requestFocus();

                } else if (!Validation.isValidUsername(name)) {
                    setName.setError(getResources().getString(R.string.login_invalid_credentials_message));
                    setName.requestFocus();
                }
                else if (!Validation.isValidPassword(password)) {
                    setPassword.setError(getResources().getString(R.string.login_invalid_credentials_message));
                    setPassword.requestFocus();
                }
                else {
                    // Intention to go in login window                       from     --------->   to
                    Intent goToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    // going to login window, action.
                    startActivity(goToLoginActivity);

                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.new_user),
                            Toast.LENGTH_SHORT).show();
                    //konstruojamas objektas
                    //public User(String username, String password, String email)
                    User user=new User(name, password, email);
                    Toast.makeText(RegisterActivity.this,
                            "Username:"+ user.getUsernameForRegistration()+"\n"+
                                    "Password:"+user.getPasswordForRegistration()+"\n"+
                                    "Email:"+user.getEmailForRegistration(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
