import { question, topic, answer } from "@/types/typeAiQuiz";
import { Button, Title } from "@mantine/core";
import { useState } from "react";
import CountdownTimer from "./CountdownTimer";
import SingleQuestion from "./SingleQuestion";
import { UUID } from "crypto";

function Quiz({ timeMin, timeSec, questions, onSubmit } : { timeMin: string, timeSec: string, questions: question[], onSubmit: (answers: answer[]) => void }) {
  const [startCountdown, setStartCountdown] = useState(false);
  const [isBlurred, setIsBlurred] = useState(true);
  const [answers, setAnswers] = useState<answer[]>([]);

  const handleStartCountdown = () => {
    setStartCountdown(true);
    setIsBlurred(false);
  };

  const handleAnswer = (questionId: UUID, optionIndex: number) => {
    setAnswers(prevAnswers => {
      const newAnswers = prevAnswers.filter(answer => answer.questionId !== questionId);
      newAnswers.push({ questionId, answerOptionNo: optionIndex });
      return newAnswers;
    });
  };

  const handleSubmit = async () => {
    await onSubmit(answers);
  };

  return (
    <div>
      <div className="sticky top-16 z-10">
        <CountdownTimer
          minutes={parseInt(timeMin)}
          seconds={parseInt(timeSec)}
          startCountdown={startCountdown}
          onTimeUp={handleSubmit}
        />
      </div>

      <Button onClick={handleStartCountdown} mx="xl" my="md">
        Start Quiz
      </Button>

      <div className={`transition duration-300 ${isBlurred ? "blur-sm" : ""}`}>
        {questions.map((question) => (
          <div key={question.id}>
            <SingleQuestion question={question} onAnswer={handleAnswer} />
          </div>
        ))}
      </div>

      <Button onClick={handleSubmit} mx="xl" my="md">
        Submit
      </Button>
    </div>
  );
};

export default Quiz;