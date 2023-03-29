package com.bbodeum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.bbodeum.chat.handler.ChatHandler;

@Configuration
//@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	private final ChatHandler chatHandler = new ChatHandler();
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		 registry.addHandler(chatHandler(), "/chatHandler") //핸들러 등록, 접근url
		 registry.addHandler(chatHandler, "ws/chat") //핸들러 등록, 접근url
		 	.addInterceptors(new HttpSessionHandshakeInterceptor()) //선후 정보 인터셉터 설정
		 	.setAllowedOrigins("*"); //cors설정
    }

//    @Bean
//    public WebSocketHandler chatHandler() {
//        return new ChatHandler();
//    }

}
