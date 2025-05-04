import { useState } from "react";
import { login, signup } from "../services/api";
import { useNavigate } from "react-router-dom";

interface AuthFormProps {
  mode: "login" | "signup";
}

export default function AuthForm({ mode }: AuthFormProps) {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res =
        mode === "signup"
          ? await signup({ username, email, password })
          : await login({ email, password });

      const token = res.data.token;
      localStorage.setItem("token", token);
      navigate("/fan-form");
    } catch (err: any) {
      if (err.response?.status === 409) {
        setError("User already registered.");
      } else {
        setError("Something went wrong. Please try again.");
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
      {mode === "signup" && (
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="p-3 rounded bg-gray-700 text-white placeholder-gray-400"
          required
        />
      )}

      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className="p-3 rounded bg-gray-700 text-white placeholder-gray-400"
        required
      />

      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className="p-3 rounded bg-gray-700 text-white placeholder-gray-400"
        required
      />

      <button
        type="submit"
        className="bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded"
      >
        {mode === "signup" ? "Create Account" : "Login"}
      </button>

      {error && <p className="text-red-400 text-sm">{error}</p>}
    </form>
  );
}
