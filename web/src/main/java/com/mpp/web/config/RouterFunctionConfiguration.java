package com.mpp.web.config;

import com.mpp.web.domain.User;
import com.mpp.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * WebFlux
 * 路由器函数 配置
 *
 * Flux：0～N个对象集合
 * Mono：0～1个对象集合
 * Reactive 中的Flux 和 Mono都是异步处理（非阻塞）
 *
 * Flux 和 Mono 都是Publisher发布器,发布信息来源
 *
 * 为什么使用Reactive请求方式，因为是异步非阻塞，之前的请求方式是同步阻塞的，
 * 使用异步方式提高吞吐量
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * 定义Get请求
     * 注入：字段注入，方法注入，构造函数注入，setter/getter注入
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> userFindAll(UserRepository userRepository) {
        return RouterFunctions.route(RequestPredicates.GET("/users/all"), request -> {
            Collection<User> users = userRepository.findAll();
            Flux<User> userFlux = Flux.fromIterable(users);
            return ServerResponse.ok().body(userFlux, User.class);
        });
    }

}
