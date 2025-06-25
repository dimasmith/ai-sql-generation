package levi9.meetup.ai.product;

import java.util.List;
import levi9.meetup.ai.product.ai.AiProductService;
import levi9.meetup.ai.product.basic.ProductService;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import levi9.meetup.ai.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for retrieving products using standard filters or AI-powered queries.
 */
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final AiProductService aiProductService;

  /**
   * Retrieves filtered products using a structured {@link FilterRequestDto}.
   *
   * @param filterRequestDto the request object containing explicit filtering conditions
   * @return list of products that match the filter
   */
  @PostMapping
  public List<Product> getProducts(@RequestBody FilterRequestDto filterRequestDto) {

    return productService.findFilteredProducts(filterRequestDto);
  }

  /**
   * Retrieves products by generating and executing SQL based on a natural language query.
   * <p>
   * The AI agent interprets the text and constructs a raw SQL query to retrieve matching products.
   * </p>
   *
   * @param text a natural language query (e.g. "show me all cheap laptops with 16GB RAM")
   * @return list of products that match the AI-generated SQL query
   */
  @PostMapping("/ai/sql")
  public List<Product> getProductsWithAi(@RequestBody String text) {

    return aiProductService.findFilteredProductsByGeneratedDto(text);
  }

  /**
   * Retrieves products by generating a {@link FilterRequestDto} from a natural language query.
   * <p>
   * The AI agent converts the input into a DTO, which is then used for product filtering.
   * </p>
   *
   * @param text a natural language query (e.g. "laptops under $1000 with SSD and 8GB RAM")
   * @return list of products that match the AI-generated DTO
   */
  @PostMapping("/ai/dto")
  public List<Product> getProductsWithAi2(@RequestBody String text) {

    return aiProductService.findFilteredProductsByRequestDto(text);
  }
}
