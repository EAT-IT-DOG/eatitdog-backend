package com.eatitdog.eatitdog.global.lib.encrypt;

import io.jsonwebtoken.SignatureAlgorithm;

public interface Encrypt {

    SignatureAlgorithm getSignatureAlgorithm();

    String encode(String data);

    /**
     * @param originalData 암호화되지 않은 기본 문자열
     * @param encryptedData 이미 암호화되어 있는 문자열
     */
    boolean match(String originalData, String encryptedData);
}
