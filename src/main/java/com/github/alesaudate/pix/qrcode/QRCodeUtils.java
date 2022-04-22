package com.github.alesaudate.pix.qrcode;


import static java.lang.String.format;

public class QRCodeUtils {

    public static String getLengthWithTwoDigits(String string) {
        if (string.length() > 99) {
            throw new IllegalArgumentException(format("The provided string is greater than 99 characters: %s", string));
        }
        return leftPad(String.valueOf(string.length()), 2);
    }

    public static String crc16(String inputString) {
        final int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

        int result = 0xFFFF; // initial value

        final byte[] bytes = inputString.getBytes();

        for (final byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                final boolean bit = (b >> 7 - i & 1) == 1;
                final boolean c15 = (result >> 15 & 1) == 1;
                result <<= 1;
                if (c15 ^ bit) {
                    result ^= polynomial;
                }
            }
        }

        result &= 0xffff;

        return leftPad(Integer.toHexString(result).toUpperCase(), 4);
    }

    private static String leftPad (String string, int size) {
        int missingSize = size - string.length();
        StringBuilder padding = new StringBuilder();
        while (padding.length() < missingSize) {
            padding.append("0");
        }
        return padding.append(string).toString();
    }
}
