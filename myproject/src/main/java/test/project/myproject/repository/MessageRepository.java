package test.project.myproject.repository;

import org.springframework.data.repository.CrudRepository;

import test.project.myproject.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

}
