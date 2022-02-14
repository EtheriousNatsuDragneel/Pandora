package com.alvis.exam.utility;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * ClassName: PKCS7Encoder
 * Description:
 * date: 2020/6/13 16:03
 *
 * @author LF
 * @since JDK 1.8
 */
public class PKCS7Encoder {
    private static final Charset CHARSET = Charset.forName("utf-8");
    private static final int BLOCK_SIZE = 32;

    public PKCS7Encoder() {
    }

    public static byte[] encode(int count) {
        int amountToPad = 32 - count % 32;
        if (amountToPad == 0) {
            amountToPad = 32;
        }

        char padChr = chr(amountToPad);
        String tmp = new String();

        for(int index = 0; index < amountToPad; ++index) {
            tmp = tmp + padChr;
        }

        return tmp.getBytes(CHARSET);
    }

    public static byte[] decode(byte[] decrypted) {
        int pad = decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }

        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    public static char chr(int a) {
        byte target = (byte)(a & 255);
        return (char)target;
    }
}