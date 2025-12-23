package com.multithread_api_performance.app.facade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multithread_api_performance.app.dto.ProductDetailDTO;
import com.multithread_api_performance.app.entity.Inventory;
import com.multithread_api_performance.app.entity.Price;
import com.multithread_api_performance.app.entity.Product;
import com.multithread_api_performance.app.service.InventoryService;
import com.multithread_api_performance.app.service.PriceService;
import com.multithread_api_performance.app.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductVirtualThread {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PriceService priceService;
    
   @Autowired
    private ExecutorService virtualThreadExecutor;

    public ProductDetailDTO getProductDetails(long productId) {
        log.info("Virtual-thread facade for productId={}", productId);

        try {
            Future<Product> productFuture =
                    virtualThreadExecutor.submit(() -> productService.findById(productId));

            Future<Price> priceFuture =
                    virtualThreadExecutor.submit(() -> priceService.getPriceByProductId(productId));

            Future<Inventory> inventoryFuture =
                    virtualThreadExecutor.submit(() -> inventoryService.getInventoryByProductId(productId));

            Product product = productFuture.get();
            Price price = priceFuture.get();
            Inventory inventory = inventoryFuture.get();

            return new ProductDetailDTO(
                    productId,
                    product.getCategory().getName(),
                    product.getName(),
                    product.getDescription(),
                    inventory.getAvailableQuantity(),
                    price.getPrice(),
                    inventory.getStatus()
            );

        } catch (Exception ex) {
            log.error("Failed to fetch product details for productId={}", productId, ex);
            throw new RuntimeException("Unable to fetch product details", ex);
        }
    }
    
}
