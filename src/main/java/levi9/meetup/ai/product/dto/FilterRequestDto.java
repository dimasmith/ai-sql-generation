package levi9.meetup.ai.product.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class FilterRequestDto {

  private UUID category;
  private List<ProductParamDto> params;
}
