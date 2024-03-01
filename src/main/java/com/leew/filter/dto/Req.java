package com.leew.filter.dto;

public class Req<T> {

    private Header header;
    private T resBody; // JSON마다 다른 Body를 가질 것이기 때문

    // 이러한 형태를 서버에서도 받아야 한다.

    public static class Header {
        private String responseCode;

        // 여기서는 Getter/Setter

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getResBody() {
        return resBody;
    }

    public void setResBody(T body) {
        this.resBody = body;
    }

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", body=" + resBody +
                '}';
    }
}
