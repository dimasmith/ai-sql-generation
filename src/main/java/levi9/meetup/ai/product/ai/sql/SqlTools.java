package levi9.meetup.ai.product.ai.sql;

import dev.langchain4j.agent.tool.Tool;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * A component that provides SQL-related tools for use by AI agents.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SqlTools {

  private final JdbcTemplate jdbcTemplate;

  /**
   * Returns the unit of measurement for a given parameter name.
   * <p>Used by AI agents to ensure correct numeric comparisons and conversions during query generation.</p>
   *
   * @param paramName the name of the parameter (case-insensitive)
   * @return the unit of measurement as stored in the param table
   */
  @Tool(name = "receiveUnitOfMeasurement", value = "Returns unit of measurement for params that can be measured")
  public String getUnitOfMeasurement(String paramName) {
    log.info("getUnitOfMeasurement tool called, paramName: {}", paramName);
    return jdbcTemplate.queryForObject(
        "SELECT p.unit_measurement FROM param p WHERE LOWER(p.name) = ?", String.class, paramName);
  }

  /**
   * Returns a set of fields that should be selected from the specified main entity table.
   * <p>Used to guide the AI in choosing meaningful fields during SQL generation.</p>
   *
   * @param mainEntity the name of the main table (e.g., "product")
   * @return a collection of field names that should be included in the result set
   * @throws IllegalStateException if the entity is not supported
   */
  @Tool(name = "getNeededFields", value = "Returns list of fields that should be taken from DB by main table name")
  public Collection<String> getNeededFields(String mainEntity) {
    log.info("getResultSetField tool called, mainEntity: {}", mainEntity);
    return switch (mainEntity){
      case "product" -> Set.of("id", "name", "category_id", "price", "available_amount", "created_at", "updated_at");
      default -> throw new IllegalStateException("Unexpected value: " + mainEntity);
    };
  }

  /**
   * Returns a list of all tables in the target schema.
   * <p>Used by the agent to discover the structure of the database and begin exploration.</p>
   *
   * @return list of table names from the {@code online_shop} schema
   */
  @Tool(name = "listTables", value = "Returns list of tables in the database")
  @Cacheable(value = "tableList")
  public List<String> listTables() {
    log.info("listTables tool called");
    return jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema = 'online_shop'", String.class);
  }

  /**
   * Returns metadata for a given table, including column names and data types.
   * <p>This helps AI models understand the structure of the table for safe and accurate query generation.</p>
   *
   * @param tableName the name of the table to describe
   * @return a list of maps, each containing {@code column_name} and {@code data_type}
   */
  @Tool(name = "describeTable", value = "Returns columns with datatypes for the specified table.")
  @Cacheable(value = "tableDescriptions", key = "#tableName")
  public List<Map<String, Object>> describeTable(String tableName) {
    log.info("describeTable tool called");
    return jdbcTemplate.queryForList("SELECT column_name, data_type FROM information_schema.columns WHERE table_name = ?", tableName);
  }
}