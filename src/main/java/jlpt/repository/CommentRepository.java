package jlpt.repository;

import org.springframework.data.repository.CrudRepository;

import jlpt.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
