import { useState } from "react";
import AuthForm from "../components/AuthForm";
import LoadingSpinner from "../components/Loading";

export default function AuthPage() {
  const [mode, setMode] = useState<"login" | "signup">("signup");
  const [isLoading, setIsLoading] = useState(false);

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white relative">
      {isLoading && (
        <div className="absolute inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50">
          <LoadingSpinner />
        </div>
      )}

      <div className="bg-gray-800 p-8 rounded-2xl shadow-xl w-full max-w-md z-10">
        <img
          src={
            "https://upload.wikimedia.org/wikipedia/pt/f/f9/Furia_Esports_logo.png"
          }
          alt="FURIA Logo"
          className="mx-auto mb-4 w-24 interactive-logo"
          onClick={() => window.open("https://furia.gg", "_blank")}
          draggable="false"
        />
        <h1 className="text-3xl font-bold mb-6 text-center">
          {mode === "signup" ? "Create your Fan Profile" : "Welcome back!"}
        </h1>
        <AuthForm mode={mode} setIsLoading={setIsLoading} />
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
