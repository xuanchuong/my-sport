package jlpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jlpt.model.MultipleChoiceQuestion;

public interface MultipleChoiceQuestionRepository<T> extends JpaRepository<MultipleChoiceQuestion, Long>{

}
