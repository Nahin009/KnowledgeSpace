import { NavLink } from '@mantine/core';
import { IconGauge, IconFingerprint } from '@tabler/icons-react';

function Navbar() {
  return (
    <>
      <NavLink
        href="#required-for-focus"
        label="AI TUTOR"
        leftSection={<IconGauge size="1rem" stroke={1.5} />}
        childrenOffset={28}
      >
        <NavLink href="/AiTutor/aiQuiz" label="AI QUIZ" />
        <NavLink href="/AiTutor/aiLearn" label="AI Learn" />
      </NavLink>

    </>
  );
}

export default Navbar;