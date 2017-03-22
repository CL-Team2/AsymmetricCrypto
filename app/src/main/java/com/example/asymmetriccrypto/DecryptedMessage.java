package com.example.asymmetriccrypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DecryptedMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypted_message);
        TextView viewer = (TextView) findViewById(R.id.decryptedMessage);
        String originalMessage = getIntent().getStringExtra("decryption");
        viewer.setText(originalMessage);
        viewer.setTextIsSelectable(true);

    }
}
