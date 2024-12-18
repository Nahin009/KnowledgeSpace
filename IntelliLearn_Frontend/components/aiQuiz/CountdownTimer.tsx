"use client";
import { useEffect, useState } from "react";

function CountdownTimer({ minutes, seconds, startCountdown, onTimeUp } : { minutes: number, seconds: number, startCountdown: boolean, onTimeUp: () => void}){
  const [remainingTime, setRemainingTime] = useState(0);
  const [isRunning, setIsRunning] = useState(false);
  const [isRed, setIsRed] = useState(false);

  useEffect(() => {
    setRemainingTime(minutes * 60 + seconds); // Initialize remaining time in seconds
  }, [minutes, seconds]);

  useEffect(() => {
    let interval: NodeJS.Timeout;
    if (startCountdown && remainingTime > 0 && !isRunning) {
      setIsRunning(true);
    }

    if (isRunning) {
      interval = setInterval(() => {
        setRemainingTime((prevTime) => {
          const newTime = prevTime - 1;
          if (newTime <= (minutes * 60 + seconds) * 0.2) {
            setIsRed(true);
          }
          if (newTime <= 0) {
            clearInterval(interval);
            setIsRunning(false);
            setIsRed(false);
            onTimeUp();
          }
          return newTime;
        });
      }, 1000);
    }

    return () => clearInterval(interval);
  }, [isRunning, remainingTime, minutes, seconds, startCountdown, onTimeUp]);

  const formatTime = (time: number) => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes.toString().padStart(2, "0")}:${seconds
      .toString()
      .padStart(2, "0")}`;
  };

  return (
    <div className="flex flex-col items-center justify-center bg-gray-100">
      <div
        className={`text-4xl font-bold transition-transform duration-500 ${
          isRed ? "text-red-500 animate-pulse" : "text-black"
        } ${isRed ? "animate-bounce" : ""}`}
      >
        {formatTime(remainingTime)}
      </div>
    </div>
  );
};

export default CountdownTimer;