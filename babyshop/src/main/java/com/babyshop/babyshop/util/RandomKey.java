package com.babyshop.babyshop.util;

import java.util.Random;

public class RandomKey {

	public String getNumber(int length) {
		String charPool = "0123456789";

		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(charPool.length());
			char randomChar = charPool.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	public String getString(int length) {
		String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(charPool.length());
			char randomChar = charPool.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}
}
