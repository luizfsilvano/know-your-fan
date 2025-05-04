import { useState } from "react";
import AuthForm from "../components/AuthForm";

export default function AuthPage() {
  const [mode, setMode] = useState<"login" | "signup">("signup");

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white">
      <div className="bg-gray-800 p-8 rounded-2xl shadow-xl w-full max-w-md">
        <h1 className="text-3xl font-bold mb-6 text-center">
          {mode === "signup" ? "Create your Fan Profile" : "Welcome back!"}
        </h1>
        <AuthForm mode={mode} />
        <p className="text-center mt-4 text-sm">
          {mode === "signup"
            ? "Already have an account?"
            : "Don't have an account?"}{" "}
          <button
            className="text-orange-500 underline"
            onClick={() => setMode(mode === "signup" ? "login" : "signup")}
          >
            {mode === "signup" ? "Login" : "Sign up"}
          </button>
        </p>
      </div>
    </div>
  );
}
