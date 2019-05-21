package com.company;
//import rsa.RSA;

import java.math.BigInteger;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        RSA key=new RSA();

        BigInteger encrypt = (BigInteger) key.encrypt();
        BigInteger decrypt = (BigInteger) key.decrypt(encrypt);
        BigInteger decryptTCR = (BigInteger) key.TCR(encrypt);

        System.out.println("Plaintext encrypted = " + encrypt);
        System.out.println("Plaintext after decryption = " + decrypt);
        System.out.println("Plaintext after decryption using TCR is = " + decryptTCR);
    }
}
