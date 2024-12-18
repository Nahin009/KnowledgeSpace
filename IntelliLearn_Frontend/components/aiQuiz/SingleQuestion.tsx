"use client";

import classes from "@/styles/question.module.css";
import { question } from "@/types/typeAiQuiz";
import { CheckIcon, Divider, Group, Radio, Stack, Text } from "@mantine/core";
import { IconX } from "@tabler/icons-react";
import { UUID } from "crypto";
import { useState } from "react";


function SingleQuestion({ question, onAnswer } : { question: question, onAnswer: (questionId: UUID, optionIndex: number) => void }){
  const [value, setValue] = useState<string | null>(null);

  const options = [
    { index: 1, value: question.option1 },
    { index: 2, value: question.option2 },
    { index: 3, value: question.option3 },
    { index: 4, value: question.option4 },
  ];

  const handleChange = (value: string | null) => {
    setValue(value);
    const selectedOption = options.find(option => option.value === value)?.index;
    if (selectedOption !== undefined) {
      onAnswer(question.id, selectedOption);
    }
  };

  const optionCards = options.map((item) => (
    <Radio.Card
      className={classes.root}
      radius="md"
      value={item.value}
      key={item.index}
      styles={{ card: { width: 800, backgroundColor: "white" } }}
    >
      <Group wrap="nowrap" align="flex-start">
        <Radio.Indicator icon={CheckIcon} color="blue" />
        {/* <Radio.Indicator icon={IconX} color="red" /> */}
        <div>
          <Text className={classes.description}>{item.value}</Text>
        </div>
      </Group>
    </Radio.Card>
  ));

  return (
    <>
      <Radio.Group
        value={value}
        onChange={handleChange}
        label={question.question}
        mx="xl"
        my="md"
      >
        <Stack pt="md" gap="xs">
          {optionCards}
        </Stack>
      </Radio.Group>

      <Text fz="xs" mt="md" mx="xl">
        CurrentValue: {value || "–"}
      </Text>
      <Divider my="md" />
    </>
  );
};

export default SingleQuestion;