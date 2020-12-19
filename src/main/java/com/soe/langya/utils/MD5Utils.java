package com.soe.langya.utils;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
public class MD5Utils {

    public static String getDigest(String msg)  {
        MessageDigest md = null;
        String res = null;
        try {
            md = MessageDigest.getInstance("md5");
            res = new String(md.digest(),"UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        md.update(msg.getBytes());

       return res;
    }
}
