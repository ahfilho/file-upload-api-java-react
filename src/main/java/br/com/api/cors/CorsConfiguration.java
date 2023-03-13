package br.com.api.cors;

import org.springframework.stereotype.Component;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
public class CorsConfiguration implements WebFilter {
    public Mono<Void> filter(ServerWebExchange ctx, WebFilterChain chain) {
        if (ctx != null) {
            ctx.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000/ssd");
            ctx.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
            ctx.getResponse().getHeaders().add("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
            if (ctx.getRequest().getMethod() == HttpMethod.OPTIONS) {
                ctx.getResponse().getHeaders().add("Access-Control-Max-Age", "1728000");
                ctx.getResponse().setStatusCode(HttpStatus.NO_CONTENT);
                return Mono.empty();
            } else {
                ctx.getResponse().getHeaders().add("Access-Control-Expose-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
                return chain.filter(ctx);
            }
        } else {
            return chain.filter(ctx);
        }
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String displayName() {
        return null;
    }

    @Override
    public WebInitParam[] initParams() {
        return new WebInitParam[0];
    }

    @Override
    public String filterName() {
        return null;
    }

    @Override
    public String smallIcon() {
        return null;
    }

    @Override
    public String largeIcon() {
        return null;
    }

    @Override
    public String[] servletNames() {
        return new String[0];
    }

    @Override
    public String[] value() {
        return new String[0];
    }

    @Override
    public String[] urlPatterns() {
        return new String[0];
    }

    @Override
    public DispatcherType[] dispatcherTypes() {
        return new DispatcherType[0];
    }

    @Override
    public boolean asyncSupported() {
        return false;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}


