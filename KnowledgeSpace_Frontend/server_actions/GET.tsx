"use server";

export default async function GET(path: string) {
    "use server";
    try {
        const response = await fetch(`http://localhost:8765/${path}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
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