package levi9.meetup.ai.product.ai.sql;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * AI service interface for generating SQL queries from natural language user input.
 *
 *  <p>This service allows multi-step reasoning to fulfill the request, including:</p>
 * <ul>
 *   <li>Fetching table and column metadata to understand schema</li>
 *   <li>Identifying relevant fields to include in the query</li>
 *   <li>Resolving units of measurement (e.g., from {@code param} and {@code product_param} tables)</li>
 *   <li>Converting values to match stored units when needed</li>
 * </ul>
 * </p>
 *
 * <p>The final SQL output must be valid and ready to execute, without additional explanation or formatting.</p>
 */
@AiService(tools = {"sqlTools"},
    chatMemory = "agentChatMemory",
    wiringMode = AiServiceWiringMode.EXPLICIT,
    chatModel = "openAiChatModel")
public interface SqlAgent {

  /**
   * Generates a valid SELECT SQL query based on a user's natural language request.
   *
   * @param userMessage natural language request for data
   * @return a valid SQL query as a {@link Result<String>}
   */
  @SystemMessage("""
      You are an assistant that translates user questions into SQL for a PostgreSQL database.
      
      Only return valid SELECT SQL, do not explain or add anything else.
      
      You can run multiple steps to solve a task, such as:
          - receive all tables from schema to understand structure
            - receive all fields from specific table
            - run extra queries to get basic unit of measurement of parameter if needed
            - get needed fields that should be returned
            - finally running queries
      
      Some details:
      - Each of product has its own set of params and value for the each param (product_param table).
      - Each param has its own unit of measurement. It is stored in 'param' table.
      If memory is 1Tb. 1 will be stored in product_param table (value column), Tb will be stored in param table (unit_measurement column).
      
      If you receive a request for SQL with units of measurement you need to define unit of measurement stored in DB and the convert the received value to it.
      
      All params are stored in varchar because there might be either string or numeric values.
      Also consider, that unit_measurement can be null.
      
      Please ignore case for all string arguments.
      
      Please extract data that could be mapped to the structure later in the code.
      """)
  Result<String> generateSql(@UserMessage String userMessage);
}
