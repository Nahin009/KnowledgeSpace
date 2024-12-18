"use client";
import AiQuiz from "./AiQuiz";

// private String question;
//     private String option1;
//     private String option2;
//     private String option3;
//     private String option4;
//     private int correctOptionNo;
//     private String solutionDescription;

function page() {
  // const generatePDF = (): void => {
  //   const input = document.getElementById('pdf-content');
  //   if (input) {
  //     const options = {
  //       margin: [1, 1, 1, 1],
  //       filename: 'download.pdf',
  //       image: { type: 'jpeg', quality: 0.98 },
  //       html2canvas: { scale: 2 },
  //       jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
  //     };

  //     html2pdf().from(input).set(options).save();
  //   }
  // };

  return (
    <div className="">
      {/* <div id="pdf-content">
        <button
          onClick={generatePDF}
          className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
        >
          Download PDF
        </button> 
      </div> */}

      <AiQuiz />
    </div>
  );
}

export default page;
