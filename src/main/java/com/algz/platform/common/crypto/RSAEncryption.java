package com.algz.platform.common.crypto;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.net.util.Base64;

public class RSAEncryption {

	/** 
	 * RSA公钥加密 
	 *  
	 * @param str 加密字符串
	 * @param publicKey 公钥 
	 * @return 密文 
	 * @throws Exception 加密过程中的异常信息 
	 */  
	public static String encrypt( String str, String publicKey ) throws Exception{
		//base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		//RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}

	/** 
	 * RSA私钥解密
	 *  
	 * @param str 加密字符串
	 * @param privateKey 私钥 
	 * @return 名文
	 * @throws Exception 解密过程中的异常信息 
	 */  
	public static String decrypt(String str, String privateKey) {
		try {
			// 64位解码加密后的字符串
			byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
			// base64编码的私钥
			byte[] decoded = Base64.decodeBase64(privateKey);
			RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
					.generatePrivate(new PKCS8EncodedKeySpec(decoded));
			// RSA解密

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			String outStr = new String(cipher.doFinal(inputByte));
			return outStr;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}
	
	
	/** 
	 * 随机生成密钥对 
	 * @return [0]公钥；[1]密钥。
	 * @throws NoSuchAlgorithmException 
	 */  
	public static String[] genKeyPair(){
		try {
			String[] keys = new String[2]; 
			// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象  
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
			// 初始化密钥对生成器，密钥大小为96-1024位  
			keyPairGen.initialize(1024,new SecureRandom());  
			// 生成一个密钥对，保存在keyPair中  
			KeyPair keyPair = keyPairGen.generateKeyPair();  
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥  
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥  
			String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));  
			// 得到私钥字符串  
			String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));  
			// 将公钥和私钥保存到Map
			keys[0]=publicKeyString;  //0表示公钥
			keys[1]=privateKeyString;  //1表示私钥
			return keys;
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	} 
	
	
}
