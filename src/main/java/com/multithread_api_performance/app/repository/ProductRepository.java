package com.multithread_api_performance.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multithread_api_performance.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
