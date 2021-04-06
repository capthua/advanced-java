package me.capthua.advancedjava.week3;

public class NettyServerApplication {
    public final static String GATEWAY_NAME="NIOGateway";
    public final static String GATEWAY_VERSION="3.0.0";

    public static void main(String[] args) {
        String proxyPort=System.getProperty("proxyPort","8888");
    }
}
