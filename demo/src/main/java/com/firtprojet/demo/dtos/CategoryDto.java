package com.firtprojet.demo.dtos;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.firtprojet.demo.entity.Category}
 */
@Value
@Data
public class CategoryDto implements Serializable {
    Long id;
    String name;
}