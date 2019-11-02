package jlpt.repository;

import org.springframework.data.repository.CrudRepository;

import jlpt.model.ChoiceAnswer;

public interface ChoiceAnswerRepository<T> extends CrudRepository<ChoiceAnswer, Long>{

}
