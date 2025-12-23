package com.multithread_api_performance.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multithread_api_performance.app.dto.ProductDetailDTO;
import com.multithread_api_performance.app.facade.ProductASyncFacade;
import com.multithread_api_performance.app.facade.ProductSyncFacade;
import com.multithread_api_performance.app.facade.ProductVirtualThread;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductSyncFacade productSyncFacade;

    @Autowired
    private ProductASyncFacade productASyncFacade;

    @Autowired
    private ProductVirtualThread productVirtualThread;

    @GetMapping("/{id}/sync")
    public ResponseEntity<ProductDetailDTO> getProductSync(@PathVariable Long id) {
        log.info("Rest request to get product by id sync: {}", id);
        return ResponseEntity.ok(productSyncFacade.getProductDetails(id));
    }

    @GetMapping("/{id}/async")
    public ResponseEntity<ProductDetailDTO> getProductAsync(@PathVariable Long id) {
        log.info("Rest request to get product by id async: {}", id);
        return ResponseEntity.ok(productASyncFacade.getProductDetails(id));
    }

    @GetMapping("/{id}/virtual-thread")
    public ResponseEntity<ProductDetailDTO> getProductVirtualThread(@PathVariable Long id) {
        log.info("Rest request to get product by id async: {}", id);
        return ResponseEntity.ok(productVirtualThread.getProductDetails(id));
    }
}
