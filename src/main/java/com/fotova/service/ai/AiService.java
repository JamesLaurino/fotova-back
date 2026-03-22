package com.fotova.service.ai;

import com.fotova.entity.ProductEntity;

public interface AiService {
    String translateTitleAndDescription(ProductEntity product);
}
