package levi9.meetup.ai.product.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Product(
    UUID id,
    String name,
    UUID categoryId,
    BigDecimal price,
    int availableAmount,
    Instant createdAt,
    Instant updatedAt
) {}