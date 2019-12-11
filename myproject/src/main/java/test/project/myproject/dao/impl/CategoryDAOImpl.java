package test.project.myproject.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.CategoryDAO;
import test.project.myproject.domain.Category;
import test.project.myproject.repository.CategoryRepository;

@Component
public class CategoryDAOImpl implements CategoryDAO{
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public List<Category> getAll() {
		return (List<Category>) categoryRepository.findAll();
	}
	public boolean save(Category category) {
		try {
			categoryRepository.save(category);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}

}
