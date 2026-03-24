import { useEffect, useState } from "react";
import { getAllWorkouts, createWorkout } from "../api/workouts";

export default function WorkoutsPage() {
    const [workouts, setWorkouts] = useState([]);
    const [name, setName] = useState("");
    const [userId, setUserId] = useState(1); // temporary until auth/me endpoint exists
    const [error, setError] = useState("");

    async function load() {
        setError("");
        try {
            const data = await getAllWorkouts();
            setWorkouts(data);
        } catch (e) {
            setError(e.message);
        }
    }

    useEffect(() => {
        load();
    }, []);

    async function onAdd(e) {
        e.preventDefault();
        setError("");

        const trimmed = name.trim();
        if (!trimmed) return;

        try {
            const created = await createWorkout({ userId: Number(userId), name: trimmed });
            setWorkouts((prev) => [created, ...prev]);
            setName("");
        } catch (e) {
            setError(e.message);
        }
    }

    return (
        <div style={{ maxWidth: 640, margin: "40px auto", padding: 16 }}>
            <h1>Workouts</h1>

            <form onSubmit={onAdd} style={{ display: "flex", gap: 8, marginBottom: 16 }}>
                <input
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                    style={{ width: 100, padding: 10 }}
                    placeholder="userId"
                />
                <input
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    style={{ flex: 1, padding: 10 }}
                    placeholder="Workout name"
                />
                <button type="submit">Add</button>
            </form>

            {error && <div style={{ color: "crimson" }}>{error}</div>}

            <ul>
                {workouts.map((w) => (
                    <li key={w.id}>
                        #{w.id} — {w.name} (userId: {w.userId})
                    </li>
                ))}
            </ul>
        </div>
    );
}