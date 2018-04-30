package com.testenglish.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyStringUtils {
	
	private MyStringUtils() {
		throw new AssertionError();
	}

	public static String hash256(String str) {
		String hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			hash = bytesToHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
	
	private static String bytesToHex(byte[] bytes) {
		StringBuffer hex = new StringBuffer();
		for (byte byt : bytes) hex.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
		return hex.toString();
	}
	
	public static Map<String, String> parseHttpRequestBody(String body) {
		Map<String, String> requestBody = new HashMap<>();
		for (String keyValue : body.replaceAll("\\s", "").split("&")) {
			requestBody.put(keyValue.split("=")[0], keyValue.split("=")[1]);
		}
		return requestBody;
	}
	
	/**
	 * Return a comma seperated String from this Collection of Integer.
	 * 
	 * @param c - the source Integer Collection
	 * @return the comma seperated String
	 */
	public static String toSeperatedString(Collection<Integer> c) {
		StringBuilder sb = new StringBuilder();
		for (Integer i : c) sb.append(i).append(",");
		return sb.toString().substring(0, sb.lastIndexOf(","));
	}
	
	/**
	 * Convert this comma seperated integer String into a List of Integer
	 * 
	 * @param str - the source String
	 * @return the converted List
	 */
	public static List<Integer> asIntegerList(String str) {
		List<Integer> intList = new LinkedList<>();
		for (String s : str.split(",")) intList.add(Integer.parseInt(s));
		return intList;
	}
	
}