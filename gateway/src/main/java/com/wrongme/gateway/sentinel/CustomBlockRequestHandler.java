package com.wrongme.gateway.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomBlockRequestHandler implements BlockRequestHandler {
    private static final String DEFAULT_BLOCK_MSG_PREFIX = "This request is blocked by sentinel.222";
    String bodyJson1 = "{\n" +
            "  \"code\": 429,\n" +
            "  \"data\": \"%s The detail exception class: %s \"\n" +
            "}";
    String bodyJson2 = "{\n" +
            "  \"code\": 500,\n" +
            "  \"data\": \"%s The detail exception class: %s \"\n" +
            "}";
    String bodyJson3 = "{\n" +
            "  \"code\": 501,\n" +
            "  \"data\": \"%s The detail exception class: %s \"\n" +
            "}";
    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
        if (throwable instanceof FlowException){
            return  ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS) // 状态码
                    .contentType(MediaType.APPLICATION_JSON) // 内容类型为 text/plain 纯文本
                    .bodyValue(String.format(bodyJson1, DEFAULT_BLOCK_MSG_PREFIX, throwable.getClass().getSimpleName()));
        }else if (throwable instanceof DegradeException){
            return  ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS) // 状态码
                    .contentType(MediaType.APPLICATION_JSON) // 内容类型为 text/plain 纯文本
                    .bodyValue(String.format(bodyJson2, DEFAULT_BLOCK_MSG_PREFIX, throwable.getClass().getSimpleName()));
        }
        return  ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS) // 状态码
                .contentType(MediaType.APPLICATION_JSON) // 内容类型为 text/plain 纯文本
                .bodyValue(String.format(bodyJson3, DEFAULT_BLOCK_MSG_PREFIX, throwable.getClass().getSimpleName())); // 错误提示
    }
}
