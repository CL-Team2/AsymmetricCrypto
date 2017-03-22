package com.example.asymmetriccrypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class EncryptedMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_message);
        TextView encrypted = (TextView) findViewById(R.id.messageEncrypted);
        String message1 = getIntent().getStringExtra("message");
        encrypted.setText(message1);
        encrypted.setTextIsSelectable(true);
    }
}

