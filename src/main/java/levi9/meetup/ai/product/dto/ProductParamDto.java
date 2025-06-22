package levi9.meetup.ai.product.dto;

import java.util.List;
import java.util.UUID;

public record ProductParamDto(
    UUID id,
    List<String> values
) {}