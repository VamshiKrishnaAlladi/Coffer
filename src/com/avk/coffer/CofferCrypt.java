package com.avk.coffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CofferCrypt {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    
    public static final int MAX_KEY_INDEX = 100000;
    
    private static Random R;
    private static long[] randomKeys;
    
    static{
    	try {
			
    		R = new Random(new Long(new Scanner(new File("./Coffer/.cofferseed")).nextLong()));
			randomKeys = R.longs(MAX_KEY_INDEX, 1000000000000000L, 10000000000000000L).toArray();
    		
		} catch (FileNotFoundException e) { e.printStackTrace(); }
    }
    
    public static void encrypt2File_Index(int index, String plainText, File outputFile){
        try {
        	String key = getKey(index);
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
             
            byte[] inputBytes = plainText.getBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
             
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            outputStream.close();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
 
    public static String decryptFromFile_Index(int index, File encryptedFile) throws Exception{
    	String key = getKey(index);
    	Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
    	Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    	cipher.init(Cipher.DECRYPT_MODE, secretKey);

    	FileInputStream inputStream = new FileInputStream(encryptedFile);
    	byte[] inputBytes = new byte[(int) encryptedFile.length()];
    	inputStream.read(inputBytes);
    	inputStream.close();

    	byte[] outputBytes = cipher.doFinal(inputBytes);

    	return new String(outputBytes);		
    }
    
    public static void encrypt2File_Key(String key, String plainText, File outputFile){
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] inputBytes = plainText.getBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
             
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            outputStream.close();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
    
    public static String decryptFromFile_Key(String key, File encryptedFile) throws Exception{
    	Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
    	Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    	cipher.init(Cipher.DECRYPT_MODE, secretKey);

    	FileInputStream inputStream = new FileInputStream(encryptedFile);
    	byte[] inputBytes = new byte[(int) encryptedFile.length()];
    	inputStream.read(inputBytes);
    	inputStream.close();

    	byte[] outputBytes = cipher.doFinal(inputBytes);

    	return new String(outputBytes);		
    }
    
    public static byte[] encrypt(int index, String plainText) throws Exception{
        	String key = getKey(index);
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] inputBytes = plainText.getBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
             
            return outputBytes;
    }
 
    public static String decrypt(int index, byte[] encryptedText) throws Exception{
    	String key = getKey(index);
    	Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
    	Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    	cipher.init(Cipher.DECRYPT_MODE, secretKey);

    	byte[] inputBytes = encryptedText;
    	
    	byte[] outputBytes = cipher.doFinal(inputBytes);

    	return new String(outputBytes);		
    }

    public static String getKey(int index){ return Long.toString(randomKeys[index]); }
    
}