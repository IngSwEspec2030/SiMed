package com.sw.ingenieria.simed.util;

import com.sun.xml.fastinfoset.util.CharArray;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class EncoderUtil {

    private EncoderUtil(){}

    public static String encode(String plainText){
        PasswordEncoder encoder = buildEncoder();
        return encoder.encode(plainText);
    }

    public static boolean validate(String plainText, String encodedText){
        PasswordEncoder encoder = buildEncoder();
        char[] charPlainText = plainText.toCharArray();
        CharSequence charSequence = new CharArray(charPlainText,0, charPlainText.length, true);
        return encoder.matches(charSequence,encodedText);
    }
    private static PasswordEncoder buildEncoder() {
        return new BCryptPasswordEncoder();
    }
}
