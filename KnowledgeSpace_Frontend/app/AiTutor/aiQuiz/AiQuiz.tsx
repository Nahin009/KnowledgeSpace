import InputTopic from "@/components/aiQuiz/InputTopic";
import Quiz from "@/components/aiQuiz/Quiz";
import POST from "@/server_actions/POST";
import { answer, question, solution, topic } from "@/types/typeAiQuiz";
import { Loader, Title } from "@mantine/core";
import { useState } from "react";

function AiQuiz() {
  const [topic, setTopic] = useState<topic>({
    title: "",
    description: "",
    allowTimer: false,
    quizDurationMinutes: "",
    quizDurationSeconds: "",
  });

  const [questions, setQuestions] = useState<question[]>([]);
  const [submitted, setSubmitted] = useState(false);

  const [solution, setSolution] = useState<solution[]>([]);
  const [answers, setAnswers] = useState<answer[]>([]);
  const [examFinished, setExamFinished] = useState(false);

  const handleSubmit = async (topic: topic) => {
    setTopic(topic);
    console.log("topic", topic);

    setSubmitted(true);

    const data = {
      title: topic.title,
      description: topic.description,
    };

    const response = await POST("quiz-service/aiQuiz/generateQuiz", data);

    if (response) {
      setQuestions(response);
    } else {
      console.log("Error in server response");
    }
  };

  const handleQuizSubmit = async (answers: answer[]) => {
    setAnswers(answers);

    setExamFinished(true);

    const response = await POST("quiz-service/aiQuiz/submitAnswers", answers);

    if (response) {
      console.log("Quiz submitted successfully");
      setSolution(response);
    } else {
      console.log("Error in server response");
    }
  };

  return (
    <div>
      {!submitted && <InputTopic onSubmit={handleSubmit} />}

      {submitted && (
        <div>
          <Title order={3} mx="xl" my="md">
            {topic.title}
          </Title>
          <Title order={4} mx="xl" my="md">
            {topic.description}
          </Title>
        </div>
      )}

      {submitted && !examFinished && questions.length > 0 && (
        <div>
          <Quiz
            timeMin={topic.quizDurationMinutes}
            timeSec={topic.quizDurationSeconds}
            questions={questions}
            onSubmit={handleQuizSubmit}
          />
        </div>
      )}

      {submitted && !examFinished && questions.length === 0 && (
        <div className="flex flex-col items-center">
          <Title order={5} mx="xl" my="md">
            Fetching Questions from the server...
          </Title>
          <Loader color="rgba(181, 22, 22, 1)" size="xl" type="dots" />
        </div>
      )}

      {examFinished && solution.length === 0 && (
        <div className="flex flex-col items-center">
          <Title order={5} mx="xl" my="md">
            Evaluating your answers...
          </Title>
          <Loader color="rgba(181, 22, 22, 1)" size="xl" type="dots" />
        </div>
      )}

      {examFinished && solution.length > 0  && (
        <div>
          <hr className="border-2 border-green-400 my-4" />
          <Title order={3} mx="xl" my="md">
            Exam Finished
          </Title>
          <Title order={4} mx="xl" my="md">
            Your Score: {solution.filter((item) => item.correctOptionNo === answers.find((answer) => answer.questionId === item.questionId)?.answerOptionNo).length} / {solution.length}
          </Title>

          <hr className="border-2 border-gray-400 my-4" />

          {solution.map((item) => (
            <div key={item.questionId}>
              <Title order={5} mx="xl" my="md">
                Question: {questions.find((question) => question.id === item.questionId)?.question}
              </Title>
              <Title order={6} mx="xl" my="md">
                Correct Answer: {questions.find((question) => question.id === item.questionId)?.[`option${item.correctOptionNo}`]}
              </Title>
              <Title order={6} mx="xl" my="md">
                Your Answer: {(questions.find((question) => question.id === item.questionId)?.[`option${answers.find((answer) => answer.questionId === item.questionId)?.answerOptionNo}`] as string) || ""}
              </Title>
              <Title order={6} mx="xl" my="md">
                Solution Description: {item.solutionDescription}
              </Title>
              <hr className="border-2 border-gray-400 my-4" />
            </div>
          ))}

        </div>
      )}
    </div>
  );
}

export default AiQuiz;
