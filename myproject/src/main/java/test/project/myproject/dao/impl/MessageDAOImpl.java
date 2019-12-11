package test.project.myproject.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.MessageDAO;
import test.project.myproject.domain.Message;
import test.project.myproject.repository.MessageRepository;

@Component
public class MessageDAOImpl implements MessageDAO {
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public boolean save(Message message) {
		try {
			messageRepository.save(message);
			return true;
		}catch(Exception e) {
			return false;
		}
		//return false;
	}

	@Override
	public List<Message> getAll() {
		return (List<Message>) messageRepository.findAll();
	}

}
