package test.project.myproject.dao;

import java.util.List;

import test.project.myproject.domain.Message;

public interface MessageDAO {
	boolean save(Message message);
	List<Message> getAll();
}
