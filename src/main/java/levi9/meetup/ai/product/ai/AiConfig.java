package levi9.meetup.ai.product.ai;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AiConfig {

  @Bean
  public ChatMemory agentChatMemory() {
    return new MessageWindowChatMemory.Builder().maxMessages(50).build();
  }
}
