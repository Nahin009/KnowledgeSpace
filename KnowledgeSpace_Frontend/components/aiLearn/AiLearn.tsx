"use client";

import POST from "@/server_actions/POST_Text_Return";
import { request } from "@/types/typeAiLearn";
import { Button, Loader, TextInput, Textarea, Title } from "@mantine/core";
import mermaid from "mermaid";
import { useEffect, useRef, useState } from "react";

function AiLearn() {
  const chartContainerRef = useRef<HTMLDivElement | null>(null);
  useEffect(() => {
    mermaid.initialize({ startOnLoad: true });
  }, []);

  const [contentState, setContentState] = useState<number>(0); // 0:not submitted, 1:submitted but not response received, 2:response received
  const [responseContent, setResponseContent] = useState<string>("");

  useEffect(() => {
    if (contentState === 2 && responseContent) {
      if (chartContainerRef.current) {
        chartContainerRef.current.innerHTML = responseContent;
        mermaid.contentLoaded();
      }
    }
  }, [contentState, responseContent]);

  const topicName = useRef<HTMLInputElement>(null);
  const topicDescription = useRef<HTMLTextAreaElement>(null);

  const handleSubmit = async () => {
    setContentState(1);

    const data: request = {
      topic: topicName.current?.value || "",
      details: topicDescription.current?.value || "",
    };

    console.log(data);

    const response = await POST("quiz-service/aiLearn/generate-content", data);

    if (response) {
      console.log(response);
      setContentState(2);
      setResponseContent(response);
    } else {
      console.log("Error in server response");
    }
  };

  return (
    <div>
      <TextInput label="Topic" placeholder="Input Topic Here" ref={topicName} />
      <Textarea
        label="Description"
        placeholder="Input Topic Description Here"
        ref={topicDescription}
      />

      <Button onClick={handleSubmit}>Submit</Button>

      {contentState === 1 && (
        <div className="">
          <Title order={5} mx="xl" my="md">
            Fetching Contents from the server...
          </Title>
          <Loader color="rgba(181, 22, 22, 1)" size="xl" type="dots" />
        </div>
      )}

      {/* {contentState === 2 && (
        <pre
          ref={chartContainerRef}
          dangerouslySetInnerHTML={{ __html: responseContent }}
        ></pre>
      )} */}

      <div ref={chartContainerRef}></div>
      {/* <img src="http://localhost:7001/Images_aiLearn/c2e3d3a3_839b_4bc5_aaa4_8cf7520e3244.png" alt="Diagram of a chloroplast showing the thylakoid membranes. " /> */}
    </div>
  );
}

export default AiLearn;
