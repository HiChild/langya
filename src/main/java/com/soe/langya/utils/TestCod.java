package com.soe.langya.utils;

import org.junit.Test;

public class TestCod {

    @Test
    public void testCode() {
        String Code = InvitationCodeUtils.createShareCode();
        System.out.println(Code);
    }

    @Test
    public void testMD5() {
        String digest = MD5Utils.getDigest("123");
        System.out.println(digest);
        String digest2 = MD5Utils.getDigest("123");
        System.out.println(digest2);
        if (digest.equals(digest2)) {
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
    }
}
