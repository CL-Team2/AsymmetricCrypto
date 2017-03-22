package com.example.asymmetriccrypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowPublicKey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_public_key);
        TextView key = (TextView) findViewById(R.id.publickey);
        String publicKey = getIntent().getStringExtra("key");
        key.setText(publicKey);
        key.setTextIsSelectable(true);

    }
}
