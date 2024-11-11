package com.Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Revshop.exception.CategoryAlreadyExistsException;
import com.Revshop.exception.CategoryNotFoundException;
import com.Revshop.models.Category;
import com.Revshop.repos.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository catrepo;

    public List<Category> getAllCategories() {		
        return catrepo.findAll(Sort.by(Sort.Direction.DESC,"catid"));
    }
	
    public void saveCategory(Category cat) {
        // Check if a category with the same name already exists
        if (catrepo.existsByCatname(cat.getCatname())) { // Ensure you have a method like this in your repository
            throw new CategoryAlreadyExistsException(cat.getCatname());
        }
		
        if (catrepo.existsById(cat.getCatid())) {
            Category cc = catrepo.findById(cat.getCatid()).get();
            cc.setCatname(cat.getCatname());
            catrepo.save(cc);
        } else {
            catrepo.save(cat);
        }
    }

    public Category findByCatId(int id) {
        return catrepo.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public long generateCatId() {
        return catrepo.count() == 0 ? 1 : catrepo.count() + 1;
    }
	
    public long totalCategories() {
        return catrepo.count();
    }
}
