package levi9.meetup.ai.product.basic;

import java.util.List;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import levi9.meetup.ai.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> findFilteredProducts(FilterRequestDto filterRequest) {
    return productRepository.findProductsByCategoryAndParams(filterRequest);
  }

}
