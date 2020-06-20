package com.company;

import java.io.UnsupportedEncodingException;

public interface Crypto {

  byte[]encrypt(byte[] data) ;
    byte[]decrypt(byte[] data);
}
