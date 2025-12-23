package com.multithread_api_performance.app.service;

import com.multithread_api_performance.app.entity.Product;

public interface ProductService {
    Product findById(Long id);
}
