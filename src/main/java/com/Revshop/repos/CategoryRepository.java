package com.Revshop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Revshop.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	 boolean existsByCatname(String catname);
}
