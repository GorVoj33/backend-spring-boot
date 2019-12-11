package test.project.myproject.dao;

import java.util.List;

import test.project.myproject.domain.Category;

public interface CategoryDAO {
	List<Category> getAll();
	boolean save(Category category);
}
