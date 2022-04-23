package com.github.alesaudate.pix.qrcode;


import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.regex.Pattern.compile;

public class QRCodeUtils {


    private QRCodeUtils(){
    }

    private static final Pattern ALPHANUMERIC_PATTERN = compile("[a-zA-Z\\d]+");
    private static final Pattern NUMERIC_PATTERN = compile("\\d+");
    private static final Pattern KEY_PATTERN = compile("[a-zA-Z\\d\\-*]+");
    private static final Pattern URL_PATTERN = compile("[a-zA-Z\\d\\-./]+");


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


    public static boolean validateStringAlphanumeric(String string) {
        return !ALPHANUMERIC_PATTERN.matcher(string).matches();
    }

    public static boolean validateKey(String key) {
        return !KEY_PATTERN.matcher(key).matches();
    }

    public static boolean validateURL(String url) {
        return !URL_PATTERN.matcher(url).matches();
    }

    public static boolean validateNumeric(String string) {
        return !NUMERIC_PATTERN.matcher(string).matches();
    }

    private static String leftPad(String string, int size) {
        int missingSize = size - string.length();
        StringBuilder padding = new StringBuilder();
        while (padding.length() < missingSize) {
            padding.append("0");
        }
        return padding.append(string).toString();
    }
}
