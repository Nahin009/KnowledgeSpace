"use server";

export default async function POST(path: string, body: any) {
  "use server";
  try {
    const response = await fetch(`http://localhost:8765/${path}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(body),
      cache: "no-store",
    });

    if (response.status != 200) {
      console.log(await response.json());
      return null;
    }

    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.log(error);
    return null;
  }
}
