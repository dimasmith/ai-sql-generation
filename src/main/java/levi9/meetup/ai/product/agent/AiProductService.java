package levi9.meetup.ai.product.agent;

import dev.langchain4j.service.Result;
import java.util.List;
import java.util.UUID;
import levi9.meetup.ai.product.ProductRowMapper;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import levi9.meetup.ai.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiProductService {

  private final JdbcTemplate jdbcTemplate;
  private final SqlAgent sqlAgent;

  public List<Product> findFilteredProducts(String userMessage) {
    Result<String> aiGeneratedResult = sqlAgent.generateSql(userMessage);
    String sql = aiGeneratedResult.content();
    log.info(sql);
    if (sql.trim().toLowerCase().startsWith("select")) {
      return jdbcTemplate.query(sql, new ProductRowMapper());
    } else {
      throw new RuntimeException("Only SELECT queries are expected");
    }
  }
}
