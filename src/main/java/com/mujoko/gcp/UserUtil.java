package com.mujoko.gcp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




public class UserUtil {


	/**
	 * Lower case Hex Digits.
	 */
	private static final String HEX_DIGITS = "0123456789abcdef";

	/**
	 * Byte mask.
	 */
	private static final int BYTE_MSK = 0xFF;

	/**
	 * Hex digit mask.
	 */
	private static final int HEX_DIGIT_MASK = 0xF;

	/**
	 * Number of bits per Hex digit (4).
	 */
	private static final int HEX_DIGIT_BITS = 4;

	private static String  TOKEN_KEY = "e1de2280dcca3bc50292af8c8940a4c20df46cce3a39cf51e23a6542c5a30552023943e15705a29e8bd2cda2545dfbf3a3e5683ed7c6e0159979a56fade00f88";

	/**
	   public static function hash_password($password = null)
    {
        return sha1(sprintf('%s-%s', self::TOKEN_KEY, $password));
    }
	 */
	public static String hashPassword(final String message) 
			throws UnsupportedOperationException, NullPointerException {
		String tmessage = TOKEN_KEY+"-"+message;
		try {
			return computeSha1OfByteArray(tmessage.getBytes(("UTF-8")));
		} catch (UnsupportedEncodingException ex) {
			throw new UnsupportedOperationException(ex);
		}
	}
	
	

	private static String computeSha1OfByteArray(final byte[] message)
			throws UnsupportedOperationException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(message);
			byte[] res = md.digest();
			return toHexString(res);
		} catch (NoSuchAlgorithmException ex) {
			throw new UnsupportedOperationException(ex);
		}
	}

	/**
	 * Compute a String in HexDigit from the input.
	 * 
	 * @param byteArray
	 *                a row byte array
	 * @return a hex String
	 * d21959852ed1b2f65d5ff98a1c59449e9828faf7 (prod)
	 */
	private static String toHexString(final byte[] byteArray) {
		StringBuilder sb = new StringBuilder(byteArray.length * 2);
		for (int i = 0; i < byteArray.length; i++) {
			int b = byteArray[i] & BYTE_MSK;
			sb.append(HEX_DIGITS.charAt(b >>> HEX_DIGIT_BITS)).append(
					HEX_DIGITS.charAt(b & HEX_DIGIT_MASK));
		}
		return sb.toString();
	}

}
