package jlpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jlpt.model.ChoiceAnswer;

public interface ChoiceAnswerRepository<T> extends JpaRepository<ChoiceAnswer, Long>{

}
