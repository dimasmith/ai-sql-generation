package levi9.meetup.ai.product.ai.filter;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import levi9.meetup.ai.product.dto.FilterRequestDto;

/**
 * AI service interface for generating {@code FilterRequestDto} objects from natural language input.
 *
 * <p>This service allows multi-step reasoning to fulfill the request, including:</p>
 * <ul>
 *   <li>Retrieving the structure of the {@code FilterRequestDto}</li>
 *   <li>Resolving a category ID based on the category name</li>
 *   <li>Resolving a parameter ID based on the parameter name</li>
 * </ul>
 *
 * <p>Useful for automating dynamic filter construction in user-facing search or filtering scenarios.</p>
 */
@AiService(tools = "buildDtoTools",
    chatMemory = "agentChatMemory",
    wiringMode = AiServiceWiringMode.EXPLICIT,
    chatModel = "openAiChatModel")
public interface BuildingDtoAgent {

  /**
   * Generates a {@link FilterRequestDto} based on a user message.
   * <p>
   * The AI processes the request, resolves necessary IDs, and maps extracted data to the FilterRequestDto structure.
   *
   * @param userMessage a natural language query representing a filtering request
   * @return a generated {@code FilterRequestDto} wrapped in a {@code Result}
   */
  @SystemMessage("""
      You are an assistant that translates user questions FilterRequestDto.
      
      
      You can run multiple steps to solve a task, such as:
      - get structure of filter request dto
      - get category id by category name
      - get parameter id by parameter name
      
      """)
  Result<FilterRequestDto> generateFilterRequestDto(@UserMessage String userMessage);
}
