CREATE TABLE ChoiceAnswer (
    id  SERIAL NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id)
)

CREATE TABLE multiple_choice_question (
    id  SERIAL NOT NULL,
    question VARCHAR(255),
	answers_id VARCHAR(45),
	correct_answer VARCHAR(45),
    PRIMARY KEY (id)
)

CREATE TABLE choice_question_answer (
  question_id integer NOT NULL,
  answer_id integer NOT NULL,
  PRIMARY KEY (question_id, answer_id),
  CONSTRAINT question_fk
    FOREIGN KEY (question_id)
    REFERENCES multiple_choice_question (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);