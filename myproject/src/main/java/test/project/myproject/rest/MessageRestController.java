package test.project.myproject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import test.project.myproject.dao.MessageDAO;
import test.project.myproject.domain.Message;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessageRestController {
	@Autowired
	MessageDAO msgDAO;
	
	@GetMapping(value = "/rest/messages")
	public List<Message> getAllMessages(){
		return msgDAO.getAll();
	}
	
	@PostMapping(value = "/rest/messages")
	public boolean saveNewMessage(@RequestBody Message msg){
		
		return msgDAO.save(msg);
	}
}
