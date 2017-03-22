package com.example.asymmetriccrypto;

import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.SecureRandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.spongycastle.util.io.pem.PemObject;
import org.spongycastle.util.io.pem.PemWriter;


public class MainActivity extends AppCompatActivity {
    boolean keyGenerated;
    KeyPair pair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialization
        keyGenerated = false;
        
    }

    public void genPair(View notUsed)
            throws java.security.NoSuchAlgorithmException, java.io.IOException
    {
        if(keyGenerated) return;
        // generate an RSA-2048 public/private key pair
        pair = RSA_2048_GENPAIR();
        // on key generation, set keyGenerated to indicate that a key pair is now present
        keyGenerated = true;
        // activate previously unusable buttons
        activateButtons();
        // create an exportable copy of the public key in PEM format
        StringWriter strWriter = new StringWriter();
        PemWriter pmWriter = new PemWriter(strWriter);
        pmWriter.writeObject(new PemObject("PUBLIC KEY", pair.getPublic().getEncoded()));
        pmWriter.flush();
        pmWriter.close();
        String thisllneverwork = strWriter.toString();
        Intent intent = new Intent(this, ShowPublicKey.class);
        intent.putExtra("key", thisllneverwork);
        startActivity(intent);
    }

    public void beginEncryAct(View notUsed)
    {
        Intent newIntent = new Intent(this, Encrypt.class);
        startActivity(newIntent);
    }

    public void beginDecryAcy(View notUsed)
    {
        Intent newIntent = new Intent(this, Decrypt.class);
        newIntent.putExtra("privateKey", pair.getPrivate());
        startActivity(newIntent);
    }

    private KeyPair RSA_2048_GENPAIR()
            throws java.security.NoSuchAlgorithmException
    {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.genKeyPair();
    }

    private void activateButtons()
    {
                Button encrypt_button = (Button) findViewById(R.id.encrypt_button),
                decrypt_button = (Button) findViewById(R.id.decrypt_button);

        encrypt_button.setClickable(true);
        encrypt_button.setAlpha(1);
        decrypt_button.setClickable(true);
        decrypt_button.setAlpha(1);
    }
}
