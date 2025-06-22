package levi9.meetup.ai.product.agent;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class SqlTools {

    private final SqlExecutor sqlExecutor;

    public SqlTools(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Tool
    public Object runSql(String sql) {
        return sqlExecutor.execute(sql);
    }
}