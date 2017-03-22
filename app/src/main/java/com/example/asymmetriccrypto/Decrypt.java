package com.example.asymmetriccrypto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.spongycastle.util.encoders.Base64;

import java.io.Serializable;
import java.security.PrivateKey;

import javax.crypto.Cipher;

public class Decrypt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
    }

    public void decrypt(View notUsed)
            throws java.security.NoSuchAlgorithmException,
            java.security.InvalidKeyException,
            javax.crypto.NoSuchPaddingException,
            javax.crypto.IllegalBlockSizeException,
            javax.crypto.ShortBufferException,
            javax.crypto.BadPaddingException
    {
        PrivateKey priv = (PrivateKey) this.getIntent().getSerializableExtra("privateKey");
        EditText cryptMessageView = (EditText) findViewById(R.id.msgFld);
        byte[] cryptMessage_byte = Base64.decode(cryptMessageView.getText().toString());
        byte[] plain = RSA_2048_DECRYPT(cryptMessage_byte, priv);
        String plain_str = new String(plain);
        //System.out.println(plain_str);
        Intent decrypter = new Intent(this, DecryptedMessage.class);
        decrypter.putExtra("decryption",plain_str);
        startActivity(decrypter);
    }

    private byte[] RSA_2048_DECRYPT(byte[] cryptTxt, PrivateKey priv)
            throws java.security.NoSuchAlgorithmException,
            java.security.InvalidKeyException,
            javax.crypto.NoSuchPaddingException,
            javax.crypto.IllegalBlockSizeException,
            javax.crypto.ShortBufferException,
            javax.crypto.BadPaddingException
    {
        Cipher RSACipher = Cipher.getInstance("RSA");
        RSACipher.init(Cipher.DECRYPT_MODE, priv);
        return RSACipher.doFinal(cryptTxt,0,cryptTxt.length);
    }
}
