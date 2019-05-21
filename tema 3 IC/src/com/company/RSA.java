package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RSA {
    private final static BigInteger one = new BigInteger("1");
    private final static BigInteger zero = new BigInteger("0");

    public BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger n;
    public Random rand;
    public int bitlength = 1024;
    private BigInteger p, q;

    @Override
    public String toString() {
        return "privateKey=" + privateKey + "\n" + ", publicKey=" + publicKey + "\n" + ", n=" + n ;
    }

    public RSA() {
        rand=new Random();
        p = BigInteger.probablePrime(bitlength, rand);
        System.out.println("p=" + p );
        q = BigInteger.probablePrime( bitlength, rand );
        System.out.println( "q=" + q );

        n = p.multiply(q);
        BigInteger phiFunction = (p.subtract(one)).multiply(q.subtract(one));

        do publicKey = new BigInteger(phiFunction.bitLength(), rand);
        while(publicKey.compareTo(one) < 0 || publicKey.compareTo(phiFunction) > 0 || !publicKey.gcd(phiFunction).equals(one));
        privateKey = publicKey.modInverse(phiFunction);
        System.out.println( "The public key is:  " + publicKey + "\n" + "The private key is: " + privateKey);
    }

    public BigInteger encrypt() {
        BigInteger plaintext;
        do{
            plaintext=new BigInteger( n.bitLength()-1,rand );
        }while(plaintext==plaintext.max(n.subtract( one )));
        System.out.println("Plaintext = " + plaintext);
        return plaintext.modPow(publicKey, n);
    }

    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, n);
    }

    public BigInteger TCR(BigInteger encrypted){
        BigInteger dp = privateKey.mod(p.subtract(one));
        BigInteger dq = privateKey.mod(q.subtract(one));
        BigInteger encryptedP=encrypted.mod(p);
        BigInteger encryptedQ=encrypted.mod(q);
        BigInteger c2=p.modInverse(q);//inversul lui p modulo q

        BigInteger messageP = encryptedP.modPow(dp,p);
        BigInteger messageQ = encryptedQ.modPow(dq,q);

        BigInteger u = c2.multiply(messageQ.subtract(messageP)).mod(q);
        BigInteger dencrypted = messageP.add(u.multiply(p));
        return dencrypted;
    }

    /*public BigInteger fraction(BigInteger a, BigInteger b){
        List<BigInteger> r=new ArrayList<>();

        while(b.compareTo(zero)<0){

        }
        return a;
    }

    public BigInteger WienerAttack(){
        BigInteger b=null;
        return b;
    }*/

    public int getBitlength() {
        return bitlength;
    }

    public void setBitlength(int bitlength) {
        this.bitlength = bitlength;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

}
