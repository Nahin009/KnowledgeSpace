# KnowledgeSpace
An AI based web platform featureing AI-based automated MCQ exam system, with questions, options and answers generated using Gemini’s NLP capabilities based on user’s selected topic and queries. This is done through advanced prompt engineering to get structured responses. Also there is another feature which integrates NLP and computer vision aiming to implement an advanced content generation interface where AI generates necessary images at proper place with text responses.

## Techmology Stacks

- **Frontend :** Next JS, Typescript, Tailwind CSS, Mantine UI
- **Backend :** Java, SpringBoot, Maven
- **Database :** PostgreSql, Spring Data JPA, Supabase
- **Architecture :** Microservice Architecture\
- **AI Tools :**
  - **NLP :** Gemini
  - **Image Generation :** Stability AI
- **Flowchart Rendering :** Mermaid JS

## AI Quiz

- User can give topic with additional description of the quiz. User can also set the time of the quiz optionally.
![1](https://github.com/user-attachments/assets/927e8426-8e17-40ba-adb7-7374d0b44fc7)

- Question, Option, Correct answers and Solution descriptions are fetched in the backend from the NLP api endpoint.
![2](https://github.com/user-attachments/assets/2c141923-5aa3-4f50-b36e-722ec0f7e08d)

- Question, Option are fetched in a formatted way in the frontend. 
![3](https://github.com/user-attachments/assets/3bd9c32e-f95c-45d9-9374-84998c9423fb)

- Now user can give exam within the time. After the time, answers will be submitted automatically. Or user can submit anytime by clicking the button.
![4](https://github.com/user-attachments/assets/d210c73e-23cb-4699-9927-ac9d8236a81b)

- Each submitted answers with the correct answers are the shown. Also with the score.
![5](https://github.com/user-attachments/assets/d20e5bf3-df21-4693-9032-7ab8c45f94f4)

---

## Ai Learn

- User can give prompt with additional instructions
![Screenshot from 2025-01-19 02-57-41](https://github.com/user-attachments/assets/77e7677f-7fe7-4137-9dda-9d36ddaa130f)

- Then the fetched response from NLP and Image generation endpoint are combined and shown. Also there is flowchart generation feature
![Screenshot from 2025-01-19 03-00-13](https://github.com/user-attachments/assets/da0ebfd0-6079-4c7d-a4d3-7e4df7b6355e)
![Screenshot from 2025-01-19 03-00-25](https://github.com/user-attachments/assets/9df96519-f2d1-4bc9-b97a-94c4abcb3be7)
![Screenshot from 2025-01-19 03-40-23](https://github.com/user-attachments/assets/ac54c461-4162-45ad-ad69-6ee2e95507e8)

- Another example is below
![Screenshot from 2025-01-19 13-42-47](https://github.com/user-attachments/assets/cf67bc22-d358-41ed-b58e-4a5102a18e4f)
![Untitled design](https://github.com/user-attachments/assets/8ccfb91f-8bcb-4ee4-adae-6027dc27da58)
![Screenshot from 2025-01-19 14-08-08](https://github.com/user-attachments/assets/bfabd3cf-6c0f-4caa-8c54-562490c8648a)
