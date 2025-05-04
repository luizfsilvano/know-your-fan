import { useLocation, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { marked } from "marked";

interface LocationState {
  profileSummary: string;
}

export default function FanProfileResult() {
  const location = useLocation();
  const navigate = useNavigate();
  const state = location.state as LocationState | null;

  useEffect(() => {
    if (!state?.profileSummary) {
      navigate("/auth");
    }
  }, [state, navigate]);

  if (!state?.profileSummary) return null;

  return (
    <div className="min-h-screen bg-black text-white px-6 py-12">
      <div className="max-w-3xl mx-auto bg-gray-900 p-8 rounded-lg shadow-lg border border-gray-700">
        <h1 className="text-3xl font-bold mb-6">Your Fan Profile</h1>
        <div
          className="prose prose-invert max-w-none text-white"
          dangerouslySetInnerHTML={{
            __html: marked.parse(state.profileSummary),
          }}
        />
      </div>
    </div>
  );
}
