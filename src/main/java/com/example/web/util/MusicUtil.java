package com.example.web.util;

import com.google.gson.Gson;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 网易云API解密
 * @Author: 邓启龙
 * @Description:
 * @Date: Created in 2019/9/22 16:33
 */
public class MusicUtil {
    private static String MODULUS = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
    private static String NONCE = "0CoJUm6Qyw8W8jud";
    private static  String PUBKEY = "010001";
    private static String VI = "0102030405060708";
    private static String USERAGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.157 Safari/537.36";
    private static String COOKIE = "os=pc; osver=Microsoft-Windows-10-Professional-build-10586-64bit; appver=2.0.3.131777; channel=netease; __remember_me=true";
    private static String REFERER = "http://music.163.com/";
    private static String secretKey = "TA3YiYCfY2dDJQgg";
    private static String encSecKey = "84ca47bca10bad09a6b04c5c927ef077d9b9f1e37098aa3eac6ea70eb59df0aa28b691b7e75e4f1f9831754919ea784c8f74fbfadf2898b0be17849fd656060162857830e241aba44991601f137624094c114ea8d17bce815b0cd4e5b8e2fbaba978c6d1d14dc3d1faf852bdd28818031ccdaaa13a6018e1024e2aae98844210";
    Gson gson = new Gson();
    public MusicUtil(){

    }

    public static  String connection(String url, Map<String, Object> data)
            throws Exception {
        String response = "";
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        StringBuffer parameterBuffer = new StringBuffer();
        URL curl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) curl.openConnection();
        if (data != null && data.size() > 0) {
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Referer", REFERER);
            conn.setRequestProperty("Cookie", COOKIE);
            conn.setRequestProperty("User-Agent", USERAGENT);
            Iterator iterator = data.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (data.get(key) != null) {
                    value = (String) data.get(key);
                } else {
                    value = "";
                }
                parameterBuffer.append(key).append("=")
                        .append(URLEncoder.encode(value, "utf-8"));
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
            try {
                out = new OutputStreamWriter(conn.getOutputStream());
                out.write(parameterBuffer.toString());
                out.flush();
                reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String lines;
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    response += lines;
                }
            } catch (Exception e) {

            } finally {
                reader.close();
                out.close();
            }
        }
        return response;
    }

    public  static Map<String, Object> prepare(Map<String, Object> raw) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("params", encrypt(jsonEncode(raw), NONCE));
        data.put("params", encrypt((String) data.get("params"), secretKey));
        data.put("encSecKey", encSecKey);
        return data;
    }

    private static  String jsonEncode(Object object) {

        return new Gson().toJson(object);
    }

    private static String encrypt(String content, String password) {
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            IvParameterSpec iv = new IvParameterSpec(VI.getBytes());// 创建iv
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(result); // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
