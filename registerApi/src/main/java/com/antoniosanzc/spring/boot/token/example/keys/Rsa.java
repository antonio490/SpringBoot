package com.antoniosanzc.spring.boot.token.example.keys;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rsa implements IEncriptyon {

	private Logger log = LoggerFactory.getLogger(Rsa.class);
	private static BigInteger n;
	private static BigInteger p = new BigInteger("2803");
	private static BigInteger q = new BigInteger("3671");
	private static BigInteger e = new BigInteger("2621183");
	private static BigInteger totient;
	private static BigInteger d;


	private void generaClaves(){
		// n = p * q
		n = p.multiply(q);
		// toltient = (p-1)*(q-1)
		totient = p.subtract(BigInteger.valueOf(1));
		totient = totient.multiply(q.subtract(BigInteger.valueOf(1)));
		// d = e^1 mod totient
		d = e.modInverse(totient);
	}

	@Override
	public String encrypt(String key) {
		
		if( key == null)
			return null;
		
		if(n == null)
			generaClaves();

		byte[] temp = new byte[1];
		byte[] dig = key.getBytes();
		BigInteger[] bigdig = new BigInteger[dig.length];

		for(int i=0; i<bigdig.length;i++){
			temp[0] = dig[i];
			bigdig[i] = new BigInteger(temp);
		}

		BigInteger[] encrypt = new BigInteger[bigdig.length];

		for(int i=0; i<bigdig.length; i++)
			encrypt[i] = bigdig[i].modPow(e,n);


		String textCod = "";
		for(int i=0; i<encrypt.length; i++) {
			textCod += encrypt[i].toString();
			if( i < encrypt.length-1)
				textCod += "-";
		}
		return textCod;
	}

	@Override
	public String decrypt(String key) {
		if( key == null)
			return null;

		String[] s = key.split("-");

		int size = s.length;
		BigInteger[] encrypt = new BigInteger[size];

		for(int i=0;i<size;i++){
			try {
				encrypt[i] = new BigInteger( s[i] );
			} catch (NumberFormatException e) {
				log.error("Se ha producido un error al parsear.",e);
				return null;
			}
		}

		BigInteger[] decrypt = new BigInteger[encrypt.length];

		for(int i=0; i<decrypt.length; i++)
			decrypt[i] = encrypt[i].modPow(d,n);

		char[] charArray = new char[decrypt.length];

		for(int i=0; i<charArray.length; i++)
			charArray[i] = (char) (decrypt[i].intValue());

		return new String(charArray);
	}

}

