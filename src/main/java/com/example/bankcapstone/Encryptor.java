package com.example.bankcapstone;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class Encryptor {

    private final String aesPath = "src/main/resources/com/example/bankcapstone/out.tmp";
    private final Integer keyLenght = 128;


    public SecretKey generateAesKey(){
        SecretKey aesKey = null;

        try{
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(keyLenght);
            aesKey = kg.generateKey();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return aesKey;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Encryptor encryptor = new Encryptor();

        String plainText = "barclays";

        SecretKey aesKey = encryptor.generateAesKey();
        System.out.println(aesKey.getEncoded());

        byte[] cipherText;
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, aesKey);
        cipherText = c.doFinal(plainText.getBytes());

        System.out.println(cipherText);

        FileWriter fileWriter = new FileWriter("src/main/resources/com/example/bankcapstone/passcode.csv");

        fileWriter.write(Base64.getEncoder().encode(cipherText).toString());
        fileWriter.close();

        FileReader fileReader = new FileReader("src/main/resources/com/example/bankcapstone/passcode.csv");
        Scanner scanner = new Scanner(fileReader);
        String pass = scanner.nextLine();

        System.out.println(pass);

        FileOutputStream fileOutputStream = null;

        try{
            fileOutputStream = new FileOutputStream(encryptor.aesPath);
            ObjectOutputStream obj = new ObjectOutputStream(fileOutputStream);
            obj.writeObject(aesKey);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fileOutputStream.close();
        }

        FileInputStream fileInputStream = null;


        fileInputStream = new FileInputStream(encryptor.aesPath);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        SecretKey aes_key = (SecretKey) ois.readObject();
        System.out.println(aes_key.getEncoded());

        c.init(Cipher.DECRYPT_MODE, aes_key);
        String decryptText = new String(c.doFinal(Base64.getDecoder().decode(pass)));
        System.out.println(decryptText);
    }
}
