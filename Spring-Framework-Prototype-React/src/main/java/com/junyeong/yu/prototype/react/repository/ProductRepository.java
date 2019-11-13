package com.junyeong.yu.prototype.react.repository;

import com.junyeong.yu.prototype.react.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    public List<Product> findAllByOrderByIdDesc();
}