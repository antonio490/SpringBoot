package com.antoniosanzc.spring.boot.token.example.keys;

public interface IEncriptyon {

	String encrypt(String key);

	String decrypt(String key);

}
