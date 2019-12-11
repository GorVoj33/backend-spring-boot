package test.project.myproject.repository;

import org.springframework.data.repository.CrudRepository;

import test.project.myproject.domain.Category;
import test.project.myproject.domain.User;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
