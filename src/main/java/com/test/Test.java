package com.test;

import java.io.UnsupportedEncodingException;

/**
 * @author algz1
 */
public class Test{
	/** 7位ascii字符，也叫作iso646-us、unicode字符集的基本拉丁块 */
	 public static final String us_ascii = "us-ascii";
	 
	 /** iso 拉丁字母表 no.1，也叫作 iso-latin-1 */
	 public static final String iso_8859_1 = "iso-8859-1";
	 
	 /** 8 位 ucs 转换格式 */
	 public static final String utf_8 = "utf-8";
	 
	 /** 16 位 ucs 转换格式，big endian（最低地址存放高位字节）字节顺序 */
	 public static final String utf_16be = "utf-16be";
	 
	 /** 16 位 ucs 转换格式，little-endian（最高地址存放低位字节）字节顺序 */
	 public static final String utf_16le = "utf-16le";
	 
	 /** 16 位 ucs 转换格式，字节顺序由可选的字节顺序标记来标识 */
	 public static final String utf_16 = "utf-16";
	 
	 /** 中文超大字符集 */
	 public static final String gbk = "gbk";
	 
	 /**
	 * 将字符编码转换成us-ascii码
	 */
	 public String toascii(String str){
	 return this.changecharset(str, us_ascii);
	 }
	 /**
	 * 将字符编码转换成iso-8859-1码
	 */
	 public String toiso_8859_1(String str){
	 return this.changecharset(str, iso_8859_1);
	 }
	 /**
	 * 将字符编码转换成utf-8码
	 */
	 public String toutf_8(String str){
	 return this.changecharset(str, utf_8);
	 }
	 /**
	 * 将字符编码转换成utf-16be码
	 */
	 public String toutf_16be(String str){
	 return this.changecharset(str, utf_16be);
	 }
	 /**
	 * 将字符编码转换成utf-16le码
	 */
	 public String toutf_16le(String str){
	 return this.changecharset(str, utf_16le);
	 }
	 /**
	 * 将字符编码转换成utf-16码
	 */
	 public String toutf_16(String str){
	 return this.changecharset(str, utf_16);
	 }
	 /**
	 * 将字符编码转换成gbk码
	 */
	 public String togbk(String str) {
	 return this.changecharset(str, gbk);
	 }
	  
	 /**
	 * 字符串编码转换的实现方法
	 * @param str 待转换编码的字符串
	 * @param newcharset 目标编码
	 * @return
	 * @throws unsupportedencodingexception
	 */
	 public String changecharset(String str, String newcharset) {
	 if (str != null) {
	  //用默认字符编码解码字符串。
	  byte[] bs = str.getBytes();
	  //用新的字符编码生成字符串
	  try {
		return new String(bs, newcharset);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }
	 return null;
	 }
	 /**
	 * 字符串编码转换的实现方法
	 * @param str 待转换编码的字符串
	 * @param oldcharset 原编码
	 * @param newcharset 目标编码
	 * @return
	 * @throws unsupportedencodingexception
	 */
	 public String changecharset(String str, String oldcharset, String newcharset) {
		 try {
			 if (str != null) {
				  //用旧的字符编码解码字符串。解码可能会出现异常。
				  byte[] bs = str.getBytes(oldcharset);
				  //用新的字符编码生成字符串
				  return new String(bs, newcharset);
				 }
		 }catch(Exception e) {
			 
		 }

	 return null;
	 }
	 
	 public static void main(String[] args)  {
		 String str1="{position={x=150, y=80}, size={width=66, height=36}, attrs={text={text=开始}, body={rx=20, ry=26}}, shape=custom-rect, ports={groups={top={position=top, attrs={circle={r=4, magnet=true, stroke=#5F95FF, strokeWidth=1, fill=#fff, style={visibility=hidden}}}}, right={position=right, attrs={circle={r=4, magnet=true, stroke=#5F95FF, strokeWidth=1, fill=#fff, style={visibility=hidden}}}}, bottom={position=bottom, attrs={circle={r=4, magnet=true, stroke=#5F95FF, strokeWidth=1, fill=#fff, style={visibility=hidden}}}}, left={position=left, attrs={circle={r=4, magnet=true, stroke=#5F95FF, strokeWidth=1, fill=#fff, style={visibility=hidden}}}}}, items=[{group=top, id=c1d53dc0-1305-42a2-8c02-350e4c8f1987}, {group=right, id=2fd84dfd-029e-4ab4-b547-903743275151}, {group=bottom, id=daf2683e-6284-4c33-b482-ab7a6661583b}, {group=left, id=333b3b17-b28f-444d-97bb-b4fd2894cad2}]}, id=3b7d0ee8-d86f-4067-82a0-dee0a896a64d, zIndex=1}";
		 System.out.println(java.net.URLEncoder.encode(str1));
	 Test test = new Test();
	 String str = "this is a 中文的 String!";
	 System.out.println("str: " + str);
	 String gbk = test.togbk(str);
	 System.out.println("转换成gbk码: " + gbk);
	 System.out.println();
	 String ascii = test.toascii(str);
	 System.out.println("转换成us-ascii码: " + ascii);
	 gbk = test.changecharset(ascii,us_ascii, gbk);
	 System.out.println("再把ascii码的字符串转换成gbk码: " + gbk);
	 System.out.println();
	 String iso88591 = test.toiso_8859_1(str);
	 System.out.println("转换成iso-8859-1码: " + iso88591);
	 gbk = test.changecharset(iso88591,iso_8859_1, gbk);
	 System.out.println("再把iso-8859-1码的字符串转换成gbk码: " + gbk);
	 System.out.println();
	 String utf8 = test.toutf_8(str);
	 System.out.println("转换成utf-8码: " + utf8);
	 gbk = test.changecharset(utf8,utf_8, gbk);
	 System.out.println("再把utf-8码的字符串转换成gbk码: " + gbk);
	 System.out.println();
	 String utf16be = test.toutf_16be(str);
	 System.out.println("转换成utf-16be码:" + utf16be);
	 gbk = test.changecharset(utf16be,utf_16be, gbk);
	 System.out.println("再把utf-16be码的字符串转换成gbk码: " + gbk);
	 System.out.println();
	 String utf16le = test.toutf_16le(str);
	 System.out.println("转换成utf-16le码:" + utf16le);
	 gbk = test.changecharset(utf16le,utf_16le, gbk);
	 System.out.println("再把utf-16le码的字符串转换成gbk码: " + gbk);
	 System.out.println();
	 String utf16 = test.toutf_16(str);
	 System.out.println("转换成utf-16码:" + utf16);
	 gbk = test.changecharset(utf16,utf_16le, gbk);
	 System.out.println("再把utf-16码的字符串转换成gbk码: " + gbk);
	 String s=null;
	try {
		s = new String("中文".getBytes("utf-8"),"utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 System.out.println(s);
	 }
}