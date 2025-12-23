package com.multithread_api_performance.app.service;

import com.multithread_api_performance.app.entity.Inventory;

public interface InventoryService {
    Inventory getInventoryByProductId(Long productId);
}