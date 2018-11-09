package com.amirul.loginespressotesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView show;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.etEmailInput);
        password = (EditText)findViewById(R.id.etPassInput);
        show = (TextView) findViewById(R.id.show);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailId = email.getText().toString();
                String passId = password.getText().toString();

                if(emailId.equals("") && passId.equals("")){
                    show.setText("empty");
                }else if(emailId.equals("amirul") && passId.equals("12345")){
                    show.setText("success");
                }else {
                    show.setText("invalid");
                }
            }
        });



    }


}
