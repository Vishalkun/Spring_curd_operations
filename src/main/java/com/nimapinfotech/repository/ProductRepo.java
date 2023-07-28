package com.nimapinfotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimapinfotech.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
