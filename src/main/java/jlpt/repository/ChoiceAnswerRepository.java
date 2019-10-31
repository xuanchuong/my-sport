package jlpt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jlpt.model.ChoiceAnswer;

@Repository
public interface ChoiceAnswerRepository<T> extends CrudRepository<ChoiceAnswer, Long>{

}
