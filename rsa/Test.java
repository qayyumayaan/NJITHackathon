package rsa; 

import java.security.*; 
import java.io.*; 
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException; 

import java.nio.ByteBuffer;
import java.nio.file.*; 

public class Test { 

    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();  
        
        PrivateKey priv = pair.getPrivate(); 
        PublicKey pub = pair.getPublic(); 

        try (FileOutputStream f = new FileOutputStream("public.key")){
            f.write(pub.getEncoded());
        }
        
        try (FileOutputStream f = new FileOutputStream("private.key")){
            f.write(priv.getEncoded());
        } 

        byte[] arrayWav = {1.0,2.0, (byte) 3.0, 4.0, 5.0, 6.0, 7.0, 8.0}; 

        Rsa.encrypt(pub, arrayWav); 

        Rsa.decrypt(priv, arrayWav);
    }


}
