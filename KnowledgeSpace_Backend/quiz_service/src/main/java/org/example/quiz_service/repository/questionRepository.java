package org.example.quiz_service.repository;

import org.example.quiz_service.model.AiQuiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface questionRepository extends JpaRepository<Question, UUID> {
}
