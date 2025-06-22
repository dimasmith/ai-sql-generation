package levi9.meetup.ai.product.basic.service;

import java.util.List;
import levi9.meetup.ai.product.dto.FilterRequestDto;
import levi9.meetup.ai.product.model.Product;
import levi9.meetup.ai.product.basic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> findFilteredProducts(FilterRequestDto filterRequest) {
    return productRepository.findProductsByCategoryAndParams(filterRequest);
  }

//  public List<Product> findFilteredProducts(UUID categoryId, Map<UUID, List<String>> params) {
//    return productRepository.findProductsByCategoryAndParams(categoryId, params);
//  }
}
