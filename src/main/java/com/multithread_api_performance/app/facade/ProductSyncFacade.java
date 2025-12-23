package com.multithread_api_performance.app.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multithread_api_performance.app.dto.ProductDetailDTO;
import com.multithread_api_performance.app.service.InventoryService;
import com.multithread_api_performance.app.service.PriceService;
import com.multithread_api_performance.app.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductSyncFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PriceService priceService;

    public ProductDetailDTO getProductDetails(long productId) {
        log.info("Sync facade for getting product details for the product id {}",productId);
        
        //fetch product
        var product = productService.findById(productId);

        //fetch the price
        var price = priceService.getPriceByProductId(productId);

        //fetch the inventory
        var inventory = inventoryService.getInventoryByProductId(productId);

        //combine result
        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());
    
    }

}
