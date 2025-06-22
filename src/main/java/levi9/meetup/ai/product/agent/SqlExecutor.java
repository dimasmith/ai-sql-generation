package levi9.meetup.ai.product.agent;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SqlExecutor {

    private final JdbcTemplate jdbcTemplate;

    public SqlExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Object execute(String sql) {
        try {
            if (sql.trim().toLowerCase().startsWith("select")) {
                return jdbcTemplate.queryForList(sql);
            } else {
                throw new Exception("Only select is allowed");
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}