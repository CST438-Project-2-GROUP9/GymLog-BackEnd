const API_BASE = import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080";

export async function getAllWorkouts() {
    const res = await fetch(`${API_BASE}/api/workouts`);
    if (!res.ok) throw new Error(`GET workouts failed: ${res.status}`);
    return res.json();
}

export async function createWorkout({ userId, name }) {
    const res = await fetch(`${API_BASE}/api/workouts`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userId, name }),
    });
    if (!res.ok) throw new Error(`POST workout failed: ${res.status}`);
    return res.json(); // returns the created Workout
}