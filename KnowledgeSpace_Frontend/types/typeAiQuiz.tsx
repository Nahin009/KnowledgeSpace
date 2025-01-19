import { UUID } from "crypto";

type topic = {
  title: string;
  description: string;
  allowTimer: boolean;
  quizDurationMinutes: string;
  quizDurationSeconds: string;
}

type question = {
  id: UUID;
  question: string;
  option1: string;
  option2: string;
  option3: string;
  option4: string;
}

type quiz = {
  topic: topic;
  questions: question[];
}

type answer = {
  questionId: UUID;
  answerOptionNo: number;
}

type solution = {
  questionId: UUID;
  correctOptionNo: number;
  solutionDescription: string;
}

export type { answer, question, quiz, solution, topic };
