package org.example.intellilearn_backend.repository;

import org.example.intellilearn_backend.model.AiQuiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface questionRepository extends JpaRepository<Question, UUID> {
}