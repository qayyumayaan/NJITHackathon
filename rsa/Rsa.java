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

public class Rsa{ 
    public static void rsaEncrypt(byte[] arrayWav, String s){

  
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
  
        if(s.equals("encrypt")){
           try {
              encrypt(pub, arrayWav);
           } catch (InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
                 | IOException | UnsupportedAudioFileException e) {
              System.err.println("Exception: " + e.getMessage());
              e.printStackTrace();
           } 
       }else if(s.equals("decrypt")){
           try {
              decrypt(priv, arrayWav);
           } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
              System.err.println("Exception: " + e.getMessage()); 
              e.printStackTrace();
           } 
       }
  
     }


     public static double[] encrypt(PublicKey pub, byte userWavArray[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException, 
    NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedAudioFileException{ 

      //Sets up an instance of cipher 
         Cipher encryptWav = Cipher.getInstance("RSA"); 
         encryptWav.init(Cipher.ENCRYPT_MODE, pub);

        //encrypts byte array using RAS encryption 
         byte[] encryptedWav = encryptWav.doFinal(userWavArray); 

         //initialize array 
         double[] doubles = new double[encryptedWav.length / Double.BYTES];

         //converts byte array to double 
         for (int i = 0; i < doubles.length; i++){ 
            //creates new byte array 
            byte[] arr = new byte[userWavArray.length]; 

            //takes chunks of 8 bytes and converts it into double 
            //allocates double in byte array 
            for(int j = 0; j < 8; j++){
               arr[j] = encryptedWav[(i*Double.BYTES) + j]; 
            } 

            ByteBuffer byteTemp = ByteBuffer.allocate(Double.BYTES);
            byteTemp.put(arr);
            byteTemp.flip(); 

            doubles[i] = byteTemp.getDouble();  
         }
         
         double[] doubleWav = doubles; 

        return doubleWav; 
   } 

    public static byte[] decrypt(PrivateKey priv, double[] encryptedSong) throws FileNotFoundException, IOException, 
    IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{

        byte[] encryptedWav = new byte[encryptedSong.length * Double.BYTES];
         for (int i = 0; i < encryptedSong.length; i++){ 
            ByteBuffer byteTemp = ByteBuffer.allocate(Double.BYTES);
            byteTemp.putDouble(encryptedSong[i]);
            System.arraycopy(byteTemp.array(), 0, encryptedWav, i*Double.BYTES, Double.BYTES);
         }

         Cipher decryptWav = Cipher.getInstance("RSA");
         decryptWav.init(Cipher.DECRYPT_MODE, priv);


         byte[] decryptedWavFile = decryptWav.doFinal(encryptedWav);
         return decryptedWavFile; 
    } 
}