package com.nimapinfotech.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "CATEGORY")

public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "CATEGORY_ID", nullable = false)
	private Integer categoryId;

	@Column(name = "CATEGORY_TITLE", nullable = false)
	private String categoryTitle;

	@Column(name = "CATEGORY_DESCRIPTION", nullable = false)
	private String categoryDescription;

	@JsonIgnore// to ignore this field
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private Set<Product> product = new HashSet<>();

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescription="
				+ categoryDescription + ", product=" + product + "]";
	}
	
	
}
