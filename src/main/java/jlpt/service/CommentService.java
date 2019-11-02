package jlpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jlpt.model.Comment;
import jlpt.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> loadAllMessages() {
		return (List<Comment>) commentRepository.findAll();
	}
}
