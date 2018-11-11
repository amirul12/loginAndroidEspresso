package com.amirul.loginespressotesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/*
 *  ****************************************************************************
 *  * Created by : Md Amirul  Islam on 11/9/2018 at 8.11 PM.
 *  * Email : amirul.csejust@gmail.com
 *  *
 *  * Purpose: To test all element of UI
 *  *
 *  * Last edited by : Md Amirul Islam on 11/11/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

public class MainActivity extends AppCompatActivity {

    private EditText mEmailEditText, mPasswordEditText;
    private TextView show;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailEditText = (EditText)findViewById(R.id.etEmailInput);
        mPasswordEditText = (EditText)findViewById(R.id.etPassInput);
        mPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId ==R.id.login || actionId == EditorInfo.IME_NULL){
                    login();
                    return true;
                }
                return false;
            }
        });

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {
        /*Reset errors */

        mEmailEditText.setError(null);
        mPasswordEditText.setError(null);

        /*getting value from email and pass*/

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;


        /*check for a valid Email*/
        if (TextUtils.isEmpty(email)){
            mEmailEditText.setError("This field is required");
            focusView = mEmailEditText;
            cancel = true;
        }else if (!isEmailVaild(email)){
            mEmailEditText.setError(getString(R.string.error_invalid_email));
            focusView = mEmailEditText;
            cancel = true;
        }

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            mPasswordEditText.setError(getString(R.string.error_field_required));
            focusView = mPasswordEditText;
            cancel = true;
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordEditText.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            if (email.equals("amir@gmail.com") && password.equals("123456")) {
                loginSuccessfully(email);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_login_failed), Toast.LENGTH_SHORT).show();
            }
        }

    }



    private boolean isEmailVaild(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void loginSuccessfully(String email) {

        Intent intent = new Intent(MainActivity.this, Welcome.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
        Toast.makeText(getApplicationContext(), getString(R.string.login_successfully), Toast.LENGTH_SHORT).show();
    }



}
