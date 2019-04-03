package com.creatorblue.cbitedu.core.utils;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class Base64 {
	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray(); 

	public static String encode(byte[] data) {
		int start = 0;
		int len = data.length;
		StringBuffer buf = new StringBuffer(data.length * 3 / 2);

		int end = len - 3;
		int i = start;
		int n = 0;

		while (i <= end) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 0x0ff) << 8)
					| (((int) data[i + 2]) & 0x0ff);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);

			i += 3;

			if (n++ >= 14) {
				n = 0;
				buf.append(" ");
			}
		}

		if (i == start + len - 2) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 255) << 8);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("=");
		} else if (i == start + len - 1) {
			int d = (((int) data[i]) & 0x0ff) << 16;

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("==");
		}

		return buf.toString();
	}

	private static int decode(char c) {
		if (c >= 'A' && c <= 'Z')
			return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z')
			return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9')
			return ((int) c) - 48 + 26 + 26;
		else
			switch (c) {
			case '+':
				return 62;
			case '/':
				return 63;
			case '=':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
			}
	}
	public static byte[] decode(String s) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(s, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		try {
			bos.close();
			bos = null;
		} catch (IOException ex) {
			System.err.println("Error while decoding BASE64: " + ex.toString());
		}
		return decodedBytes;
	}

	private static void decode(String s, OutputStream os) throws IOException {
		int i = 0;

		int len = s.length();

		while (true) {
			while (i < len && s.charAt(i) <= ' ')
				i++;

			if (i == len)
				break;

			int tri = (decode(s.charAt(i)) << 18)
					+ (decode(s.charAt(i + 1)) << 12)
					+ (decode(s.charAt(i + 2)) << 6)
					+ (decode(s.charAt(i + 3)));

			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '=')
				break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '=')
				break;
			os.write(tri & 255);

			i += 4;
		}
	}
	
	public static String decodeUserId(String user_id)
	{
	  String [] targerarray= new String []{"o","r","s","t","I","K","Q","V","M","N","X"}; 
	  char [] useridarray=user_id.toCharArray();
	  String return_id="";
	  int charat=-1;
	  int ws=0;
      int countlength=-1;
	  for(int i=0;i<targerarray.length;i++)
	  {
		  if(targerarray[i].equals(String.valueOf(useridarray[useridarray.length-2])))
		  {
			  charat=i;
			 
		  }
		  if(targerarray[i].equals(String.valueOf(useridarray[useridarray.length-1])))
		  {
			  ws=i;
			 
		  }
		  
	  }
	  countlength=charat+ws;
	  for(int i =charat;i<countlength;i++)
	  {
		  for(int j=0;j<targerarray.length;j++)
		  {
			  if(targerarray[j].equals(String.valueOf(useridarray[i])))
			  {
				  return_id+=String.valueOf((j));
			  }
		  }
	  }
	  return return_id;
	}
//	public static String encodeCmd(){
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("userName", "xinxicaiji");
//		data.put("site", "yyfcj");
//		data.put("channelId", "7349");
//		data.put("count", "4");
//		data.put("start", "0");
//		return encode((JSONObject.fromObject(data).toString().getBytes()));
//	}
	
	public static void main(String[] args) throws Exception {
		ScriptEngineManager engineManager = new ScriptEngineManager();  
        ScriptEngine engine = engineManager.getEngineByName("JavaScript"); //得到脚本引擎
        engine.eval(new java.io.FileReader("D:\\runtime-EclipseApplication-c2\\hnbcrp-platform\\src\\main\\webapp\\js\\md5.js"));  
        Invocable inv = (Invocable)engine;  
        Object a = inv.invokeFunction("MD5", "123456" );  
        System.out.println(a.toString());
	}
}
