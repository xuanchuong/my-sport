package jlpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jlpt.model.Comment;
import jlpt.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/allComment")
	public List<Comment> getAllComments() {
		return commentService.loadAllMessages();
	}
	
	@PostMapping("/add")
	public HttpStatus insertPersone(@RequestBody Comment comment) {
		System.out.println(comment);
		return commentService.add(comment) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}
}
