package levi9.meetup.ai.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import levi9.meetup.ai.product.model.Product;
import org.springframework.jdbc.core.RowMapper;

/**
 * Maps a row from the result set to a {@link Product} object.
 */
public class ProductRowMapper implements RowMapper<Product> {

  @Override
  public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Product(
        UUID.fromString(rs.getString("id")),
        rs.getString("name"),
        UUID.fromString(rs.getString("category_id")),
        rs.getBigDecimal("price"),
        rs.getInt("available_amount"),
        rs.getTimestamp("created_at").toInstant(),
        rs.getTimestamp("updated_at").toInstant());
  }
}
