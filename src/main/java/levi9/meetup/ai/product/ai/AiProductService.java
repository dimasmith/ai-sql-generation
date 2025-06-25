package levi9.meetup.ai.product.ai;

import dev.langchain4j.service.Result;
import java.util.List;
import levi9.meetup.ai.product.ProductRowMapper;
import levi9.meetup.ai.product.ai.filter.BuildingDtoAgent;
import levi9.meetup.ai.product.ai.sql.SqlAgent;
import levi9.meetup.ai.product.basic.ProductService;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import levi9.meetup.ai.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class that integrates AI-powered agents to assist with product filtering and search.
 * <p>
 * This service uses natural language input from the user to either:
 * <ul>
 *   <li>Generate raw SQL queries using an AI agent</li>
 *   <li>Construct a structured {@link FilterRequestDto} object for downstream filtering logic</li>
 * </ul>
 * <p>
 * It relies on {@link SqlAgent} and {@link BuildingDtoAgent} to interpret user input,
 * and delegates data retrieval to a {@link JdbcTemplate} or the existing {@link ProductService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AiProductService {

  private final JdbcTemplate jdbcTemplate;
  private final SqlAgent sqlAgent;
  private final BuildingDtoAgent filterBuildingDtoAgent;
  private final ProductService productService;

  /**
   * Generates a SQL query using the {@link SqlAgent} from user-provided natural language input,
   * executes it against the database, and returns a list of matching {@link Product} objects.
   * <p>
   * If the initial SQL query fails to execute, the method will automatically retry by informing
   * the agent of the error and regenerating the query.
   *
   * @param userMessage natural language query describing the desired filter criteria
   * @return list of {@link Product} entities matching the filter
   * @throws RuntimeException if the generated query is not a valid SELECT statement
   */
  public List<Product> findFilteredProductsByGeneratedDto(String userMessage) {
    Result<String> aiGeneratedResult = sqlAgent.generateSql(userMessage);
    String sql = aiGeneratedResult.content();
    log.info(sql);
    if (sql.trim().toLowerCase().startsWith("select")) {
      try {
        return jdbcTemplate.query(sql, new ProductRowMapper());
      } catch (Exception e) {
        log.error("Trying again: {}", e.getMessage());
        String newSql = sqlAgent.generateSql("Try again. There was an error: " + e.getMessage()).content();
        log.info("New SQL: {}", newSql);
        return jdbcTemplate.query(newSql, new ProductRowMapper());
      }
    } else {
      throw new RuntimeException("Only SELECT queries are expected");
    }
  }

  /**
   * Uses the {@link BuildingDtoAgent} to convert a natural language input into a {@link FilterRequestDto},
   * and delegates product filtering to the {@link ProductService}.
   *
   * @param userMessage natural language query describing filtering conditions
   * @return list of {@link Product} entities that match the AI-generated filtering request
   */
  public List<Product> findFilteredProductsByRequestDto(String userMessage) {
    Result<FilterRequestDto> aiGeneratedResult = filterBuildingDtoAgent.generateFilterRequestDto(userMessage);
    FilterRequestDto filterRequestDto = aiGeneratedResult.content();
    log.info("Result DTO: {}", filterRequestDto.toString());
    return productService.findFilteredProducts(filterRequestDto);
  }
}
