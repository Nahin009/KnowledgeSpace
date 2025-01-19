"use client";

import { topic } from "@/types/typeAiQuiz";
import {
  Button,
  Checkbox,
  Flex,
  NumberInput,
  TextInput,
  Textarea,
  Title,
} from "@mantine/core";
import React from "react";

function InputTopic({ onSubmit }: { onSubmit: (topic: topic) => void }) {

  const [allowTimer, setAllowTimer] = React.useState(false);
  const titleRef = React.useRef<HTMLInputElement>(null);
  const descriptionRef = React.useRef<HTMLTextAreaElement>(null);
  const quizDurationMinutesRef = React.useRef<HTMLInputElement>(null);
  const quizDurationSecondsRef = React.useRef<HTMLInputElement>(null);

  const handleSubmit = () => {
    const topic: topic = {
      title: titleRef.current?.value || "",
      description: descriptionRef.current?.value || "",
      allowTimer: allowTimer,
      quizDurationMinutes: quizDurationMinutesRef.current?.value || "",
      quizDurationSeconds: quizDurationSecondsRef.current?.value || "",
    };

    onSubmit(topic);
  };

  return (
    <div className="flex flex-col gap-4 my-4">
      <Title mx="xl" my="md" order={3}>
        Quiz
      </Title>

      <TextInput
        label="Topic"
        size="md"
        ml="xl"
        styles={{ input: { width: 500 } }}
        withAsterisk
        description="Input Quiz Topic, Subtopic"
        placeholder="Write the topic of the quiz here"
        ref={titleRef}
      />

      <Textarea
        label="Additional Information"
        placeholder="Give more information about the quiz here"
        ml="xl"
        styles={{ input: { width: 800 } }}
        autosize
        minRows={2}
        maxRows={4}
        ref={descriptionRef}
      />

      <Checkbox
        label="Allow Timer"
        id="allow-timer"
        checked={allowTimer}
        onChange={() => setAllowTimer(!allowTimer)}
        ml="xl"
        description="If enabled, a timer will be displayed on the quiz"
      />

      <Flex
        gap="xl"
        justify="flex-start"
        align="flex-start"
        direction="row"
        wrap="wrap"
        ml="xl"
      >
        <NumberInput
          label="Quiz Duration (minutes)"
          placeholder="minutes"
          clampBehavior="strict"
          suffix=" min"
          min={0}
          max={10}
          ml="xl"
          disabled={!allowTimer}
          styles={{ input: { width: 200 } }}
          ref={quizDurationMinutesRef}
        />

        <NumberInput
          label="Quiz Duration (seconds)"
          placeholder="seconds"
          clampBehavior="strict"
          suffix=" sec"
          min={0}
          max={59}
          ml="xl"
          disabled={!allowTimer}
          styles={{ input: { width: 200 } }}
          ref={quizDurationSecondsRef}
        />
      </Flex>

      <Button
        onClick={handleSubmit}
        color="teal"
        size="lg"
        mx="xl"
        style={{ width: 200 }}
      >
        Submit
      </Button>

    </div>
  );
}

export default InputTopic;
