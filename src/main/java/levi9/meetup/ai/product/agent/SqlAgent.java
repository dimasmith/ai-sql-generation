package levi9.meetup.ai.product.agent;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface SqlAgent {

  @SystemMessage("""
      You are an assistant that translates user questions into SQL for a PostgreSQL database.
     
      Only return valid SELECT SQL, do not explain or add anything else.
     
      Use the following schema:
      Tables:
      category(id UUID, name VARCHAR)
      param(id UUID, name VARCHAR, category_id UUID, unit_measurement VARCHAR)
      product(id UUID, name VARCHAR, category_id UUID, price MONEY, available_amount INT, created_at TIMESTAMP, updated_at TIMESTAMP)
      product_param(product_id UUID, param_id UUID, value VARCHAR)
      users(id UUID, first_name VARCHAR, last_name VARCHAR, email VARCHAR, phone_number VARCHAR)
      orders(id UUID, user_id UUID, delivery_method VARCHAR, created_at TIMESTAMP, updated_at TIMESTAMP)
      order_product(order_id UUID, product_id UUID, amount SMALLINT)
      
      Some details: 
      - Each of product has its own set of params and value for the each param (product_param table). 
      - Each param has its own unit of measurement. It is stored in 'param' table.
      If memory is 1Tb. 1 will be stored in product_param table (value column), Tb will be stored in param table (unit_measurement column).
      
      Please ignore case for all string arguments.
      
      Please extract data that could be mapped to the structure later in the code:
      Product(
          UUID id,
          String name,
          UUID categoryId,
          BigDecimal price,
          int availableAmount,
          Instant createdAt,
          Instant updatedAt
      ) {}
      """)
  Result<String> generateSql(@UserMessage String userMessage);
}
