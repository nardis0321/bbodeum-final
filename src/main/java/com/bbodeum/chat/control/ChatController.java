package com.bbodeum.chat.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/chat")
    public String chatGET(){

        log.info("@ChatController, chat GET()");
        
        return "chat";
    }
}
