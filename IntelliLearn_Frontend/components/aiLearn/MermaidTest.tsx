"use client";

import { useEffect, useRef } from "react";
import mermaid from "mermaid";

function MermaidTest() {
  const chartContainerRef = useRef(null);

  const chartDefinition = `
**Iteration 3:**
* Middle element: 13
* 13 = 13, we have found our target!

**Flowchart:**

<pre class="mermaid">graph LR
    A[Start] --> B{Initialize search interval}
    B --> C{Find middle element}
    C --> D{Compare target and middle element}
    D -- target > middle --> E{Discard right half}
    D -- target < middle --> F{Discard left half}
    D -- target = middle --> G{Found target}
    E --> B
    F --> B
    G --> H{End}</pre>

**Conclusion:**
  `;

  useEffect(() => {
    mermaid.initialize({ startOnLoad: true });

    if (chartContainerRef.current) {
      mermaid.contentLoaded();
    }
  }, [chartDefinition]);

  return (
    <pre ref={chartContainerRef} dangerouslySetInnerHTML={{ __html: chartDefinition }}></pre>

  );
}

export default MermaidTest;
