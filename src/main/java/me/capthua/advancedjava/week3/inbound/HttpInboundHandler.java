package me.capthua.advancedjava.week3.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import me.capthua.advancedjava.week3.filter.HeaderHttpRequestFilter;
import me.capthua.advancedjava.week3.filter.HttpRequestFilter;
import me.capthua.advancedjava.week3.outbound.httpclient4.HttpOutboundHandler;

import java.util.List;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final List<String> proxyServer;
    private HttpOutboundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();


    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer=proxyServer;
        this.handler=new HttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest=(FullHttpRequest) msg;
            handler.handle(fullHttpRequest,ctx,filter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
