package levi9.meetup.ai.product;

import java.util.List;
import levi9.meetup.ai.product.agent.AiProductService;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import levi9.meetup.ai.product.model.Product;
import levi9.meetup.ai.product.basic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final AiProductService aiProductService;

  //  {
  //    "category": "8cacd6cf-37db-4b75-9219-88808ff59e0e",
  //      "params": [
  //    {
  //      "id": "c2a19482-d216-4e6a-abd2-e2443c4f8cfc",
  //        "values": [
  //          "12",
  //          "24",
  //          "36"
  //            ]
  //    },
  //    {
  //      "id": "585489b8-f64a-439e-9e5f-2c51fabe8355",
  //        "values": [
  //      "сірий"
  //            ]
  //    }
  //    ]
  //  }
  @PostMapping
  public List<Product> getProducts(@RequestBody FilterRequestDto filterRequestDto) {

    return productService.findFilteredProducts(filterRequestDto);
  }

  @PostMapping("/ai")
  public List<Product> getProductsWithAi(@RequestBody String text) {

    return aiProductService.findFilteredProducts(text);
  }
}
