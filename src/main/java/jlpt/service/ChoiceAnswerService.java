package jlpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jlpt.model.ChoiceAnswer;
import jlpt.repository.ChoiceAnswerRepository;

@Service
public class ChoiceAnswerService {

	@Autowired
	private ChoiceAnswerRepository<ChoiceAnswer> choiceAnswerRepository;
	
	@Transactional
	public List<ChoiceAnswer> getAllChoiceAnswers() {
		return (List<ChoiceAnswer>) choiceAnswerRepository.findAll();
	}
	
	@Transactional
	public boolean addNewChoiceAnswer(ChoiceAnswer newObject) {
		System.out.println(newObject.toString());
		return choiceAnswerRepository.save(newObject) != null;
	}
}
