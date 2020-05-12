package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Category;
import edu.miu.cs545.group01.online.market.repository.CategoryRepository;
import edu.miu.cs545.group01.online.market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
}
