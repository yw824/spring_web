package com.leew.hello.ioc;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component("urlEncoder")
public class UrlEncoder implements IEncoder {
    public String encode(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "ERROR : Cannot Encode Messages: " + message;
        }
    }
}
