package com.example.asymmetriccrypto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.spongycastle.asn1.pkcs.RSAPublicKey;
import org.spongycastle.util.encoders.Base64;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class Encrypt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }

    public void encrypt(View notUsed)
            throws java.security.NoSuchAlgorithmException,
            java.security.spec.InvalidKeySpecException,
            java.security.NoSuchAlgorithmException,
            java.security.InvalidKeyException,
            javax.crypto.NoSuchPaddingException,
            javax.crypto.IllegalBlockSizeException,
            javax.crypto.ShortBufferException,
            javax.crypto.BadPaddingException
    {
        EditText pubKeyFld = (EditText) findViewById(R.id.pubKeyFld);
        EditText msgFld = (EditText) findViewById(R.id.msgFld);
        // Retrieve the public key and plaintext
        String pubKey_str = pubKeyFld.getText().toString();
        String plain_str = msgFld.getText().toString();
        // Convert them to byte arrays
        byte[] pubKey_byte = Base64.decode(pubKey_str);
        byte[] plain_byte = plain_str.getBytes();
        // convert pubKey_byte to a public key

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKey_byte);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PublicKey pubby = fact.generatePublic(keySpec);


//        org.spongycastle.asn1.pkcs.RSAPublicKey pubKey =
//                org.spongycastle.asn1.pkcs.RSAPublicKey.getInstance(pubKey_byte);
//
//        BigInteger mod = pubKey.getModulus(), pubExp = pubKey.getPublicExponent();
//        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(mod, pubExp);
//        KeyFactory fact = KeyFactory.getInstance("RSA");
//        PublicKey pubby = fact.generatePublic(keySpec);

        // encrypt plaintext with public key
        byte[] crypt_byte = RSA_2048_ENCRYPT(plain_byte, pubby);
        String crypt_64 = Base64.toBase64String(crypt_byte);
        Intent encry = new Intent(this, EncryptedMessage.class);
        encry.putExtra("message",crypt_64);
        startActivity(encry);
        int i = 0;
    }

    private byte[] RSA_2048_ENCRYPT(byte[] plaintext, PublicKey pub)
            throws java.security.NoSuchAlgorithmException,
            java.security.InvalidKeyException,
            javax.crypto.NoSuchPaddingException,
            javax.crypto.IllegalBlockSizeException,
            javax.crypto.ShortBufferException,
            javax.crypto.BadPaddingException
    {
        Cipher RSACipher = Cipher.getInstance("RSA");
        RSACipher.init(Cipher.ENCRYPT_MODE, pub);
        return RSACipher.doFinal(plaintext,0,plaintext.length);
    }
}
