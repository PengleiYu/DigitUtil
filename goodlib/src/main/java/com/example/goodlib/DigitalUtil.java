package com.example.goodlib;

import android.util.Log;

/**
 * 用于数字的转换处理
 * Created by yupenglei on 17/3/22.
 */

public class DigitalUtil {

    private static final String TAG = "DigitalUtil";


    /**
     * @param bytes byte表示
     * @return 16进制表示
     */
    public static String bytes2Hex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        StringBuilder hexes = new StringBuilder();
        for (byte b : bytes) {
            hexes.append(Character.forDigit(b >> 4 & 0x0f, 16))
                    .append(Character.forDigit(b & 0x0f, 16));
        }
        return hexes.toString().toUpperCase();
    }

    /**
     * @param hexes 16进制表示
     * @return byte表示
     */
    public static byte[] hex2Bytes(String hexes) {
        if (hexes.length() % 2 != 0) {
            hexes = "0" + hexes;
        }
        int len = hexes.length() / 2;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            int n = (Character.digit(hexes.charAt(i << 1), 16) << 4) | Character.digit(hexes
                    .charAt((i << 1) + 1), 16);
            bytes[i] = (byte) n;
        }
        return bytes;
    }

    /**
     * @param bytes byte表示, 不可大于4
     * @return int表示
     */
    public static int bytes2int(byte[] bytes) {
        if (bytes.length > 4) {
            Log.e(TAG + ".bytes2int", "warring: bytes array`s length is too large.");
        }
        int result = 0;
        for (byte b : bytes) {
            result = (result << 8) | (b & 0xff);
        }
        return result;
    }

    /**
     * @param value int表示
     * @param len   要获取的byte数组长度,从右开始
     * @return byte表示
     */
    public static byte[] int2bytes(int value, int len) {
        if (len > 4) {
            Log.e(TAG + ".int2bytes", "warring: len is too large.");
        }
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - 1 - i] = (byte) ((value >> (8 * i)) & 0xff);
        }
        return b;
    }

    /**
     * @param value byte表示
     * @return bit字符表示
     */
    public static String byte2bits(byte value) {
        StringBuilder builder = new StringBuilder();
        for (int i = Byte.SIZE - 1; i >= 0; i--) {
            boolean nil = (value & (0x1 << i)) == 0;
            builder.append(nil ? "0" : "1");
        }
        return builder.toString();
    }

    /**
     * @param bytes 原始byte数组列表
     * @return 连接后的byte数组
     */
    public static byte[] concatenateBytes(byte[]... bytes) {
        int length = 0;
        for (byte[] b : bytes) {
            length += b.length;
        }
        byte[] data = new byte[length];
        int startIndex = 0;
        for (byte[] b : bytes) {
            System.arraycopy(b, 0, data, startIndex, b.length);
            startIndex += b.length;
        }
        return data;
    }

    /**
     * @param bytes 原始byte数组
     * @return byte数组的无符号表示
     */
    public static int[] byteArray2UnsignIntArray(byte[] bytes) {
        int[] ints = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0) {
                ints[i] = bytes[i] + 256;
            } else {
                ints[i] = bytes[i];
            }
        }
        return ints;
    }

}
