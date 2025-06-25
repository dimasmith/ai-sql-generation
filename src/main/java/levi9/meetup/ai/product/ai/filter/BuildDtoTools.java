package levi9.meetup.ai.product.ai.filter;

import dev.langchain4j.agent.tool.Tool;
import java.util.UUID;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * A component exposing a set of AI tools for resolving values and structure required to build dto.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BuildDtoTools {

  private final JdbcTemplate jdbcTemplate;

  /**
   * Provides the structure (class reference) of {@link FilterRequestDto} for the AI model.
   *
   * @return the {@code Class} object representing {@link FilterRequestDto}
   */
  @Tool(name = "filterRequestDtoStructure")
  public Class getFilterRequestDtoStructure() {
    log.info("getFilterRequestDtoStructure");
    return FilterRequestDto.class;
  }

  /**
   * Resolves the UUID of a category based on its name using a case-insensitive lookup.
   *
   * @param categoryName the name of the category
   * @return the UUID of the category, or {@code null} if not found
   */
  @Tool(name = "categotyId")
  public UUID getCategoryId(String categoryName) {
    log.info("getCategoryId");
    try {
      return jdbcTemplate.queryForObject("SELECT id FROM category WHERE LOWER(name) = ?", UUID.class, categoryName);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Resolves the UUID of a parameter based on its name using a case-insensitive lookup.
   *
   * @param paramName the name of the parameter
   * @return the UUID of the parameter, or {@code null} if not found
   */
  @Tool(name = "paramId")
  public UUID getParamId(String paramName) {
    log.info("getParamId");
    try {
      return jdbcTemplate.queryForObject("SELECT id FROM param WHERE LOWER(name) = ?", UUID.class, paramName);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}