package levi9.meetup.ai.product.basic.repository;

import java.util.List;
import java.util.UUID;
import levi9.meetup.ai.product.ProductRowMapper;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import levi9.meetup.ai.product.dto.ProductParamDto;
import levi9.meetup.ai.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRepository {

  public static final String SELECT_PRODUCT = """
      SELECT DISTINCT p.id,
                      p.name,
                      p.category_id,
                      p.price::numeric,
                      p.available_amount,
                      p.created_at,
                      p.updated_at
      FROM product p""";

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public List<Product> findProductsByCategoryAndParams(FilterRequestDto filterRequest) {
    MapSqlParameterSource queryParams = new MapSqlParameterSource();

    StringBuilder sql = new StringBuilder(SELECT_PRODUCT);
    StringBuilder whereClause = new StringBuilder(" WHERE 1=1");

    buildCategoryClause(filterRequest.getCategory(), whereClause, queryParams);
    buildParamsClause(filterRequest.getParams(), sql, whereClause, queryParams);

    sql.append(whereClause);
    log.info(sql.toString());
    log.info(queryParams.getValues().toString());
    return jdbcTemplate.query(sql.toString(), queryParams, new ProductRowMapper());
  }

  private void buildParamsClause(List<ProductParamDto> params,
                                 StringBuilder sql,
                                 StringBuilder whereClause,
                                 MapSqlParameterSource queryParams) {
    if (CollectionUtils.isNotEmpty(params)) {
      sql.append(" LEFT JOIN product_param pp ON pp.product_id = p.id");

      // combine params with previous conditions
      whereClause.append(" AND ");

      for (int i = 0; i < params.size(); i++) {
        ProductParamDto param = params.get(i);

        if (i > 0) {
          whereClause.append(" OR ");
        }

        whereClause.append(" (pp.param_id = :param_id").append(i);
        whereClause.append(" AND pp.value IN (:pp_values").append(i).append("))");

        queryParams.addValue("param_id" + i, param.id());
        queryParams.addValue("pp_values" + i, param.values());
      }
      whereClause.append(" GROUP BY p.id");
      whereClause.append(" HAVING COUNT(DISTINCT pp.param_id) = :params_amount;");
      queryParams.addValue("params_amount", params.size());
    }
  }

  private void buildCategoryClause(UUID categotyId,
                                   StringBuilder whereClause,
                                   MapSqlParameterSource queryParams) {
    if (categotyId != null) {
      whereClause.append(" AND p.category_id = :category_id");
      queryParams.addValue("category_id", categotyId);
    }
  }
}
