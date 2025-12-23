package com.multithread_api_performance.app.service;

import com.multithread_api_performance.app.entity.Price;

public interface PriceService {
    Price getPriceByProductId(Long productId);
}
