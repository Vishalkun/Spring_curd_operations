package com.nimapinfotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimapinfotech.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
