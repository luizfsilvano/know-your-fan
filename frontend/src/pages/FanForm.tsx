import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import { createFanProfile } from "../services/api";

interface JwtPayload {
  username: string;
}

export default function FanForm() {
  const [preferredPlatform, setPreferredPlatform] = useState("");
  const [favoriteGenre, setFavoriteGenre] = useState("");
  const [playStyle, setPlayStyle] = useState("");
  const [favoritePlayer, setFavoritePlayer] = useState("");
  const [gameHoursPerWeek, setGameHoursPerWeek] = useState("");
  const [nickname, setNickname] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/auth");
      return;
    }
    const decoded = jwtDecode<JwtPayload>(token);
    setNickname(decoded.username);
  }, [navigate]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const token = localStorage.getItem("token");
      if (!token) throw new Error("Token not found");

      const res = await createFanProfile(
        {
          nickname,
          preferredPlatform,
          favoriteGenre,
          playStyle,
          favoritePlayer,
          favoriteTeam: "FURIA",
          gameHoursPerWeek,
        },
        token
      );

      navigate("/fan-profile-result", {
        state: { profileSummary: res.data.profileSummary },
      });
    } catch (err) {
      setError("Failed to create fan profile. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-black flex items-center justify-center px-4">
      <div className="relative w-full max-w-md bg-gray-900 p-8 rounded-xl shadow-lg overflow-hidden before:absolute before:top-0 before:right-0 before:w-24 before:h-24 before:bg-white before:rounded-full before:blur-2xl before:-z-10 after:absolute after:bottom-0 after:left-0 after:w-32 after:h-32 after:bg-gray-700 after:rounded-full after:blur-2xl after:-z-10">
        <h2 className="text-2xl font-bold text-white text-center mb-6">
          Fan Profile Form
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          <select
            value={preferredPlatform}
            onChange={(e) => setPreferredPlatform(e.target.value)}
            required
            className="w-full p-2 bg-gray-800 text-white border border-gray-700 rounded-md"
          >
            <option value="" disabled>
              Select Platform
            </option>
            <option value="PC">PC</option>
            <option value="CONSOLE">Console</option>
            <option value="MOBILE">Mobile</option>
            <option value="OTHER">Other</option>
          </select>

          <select
            value={favoriteGenre}
            onChange={(e) => setFavoriteGenre(e.target.value)}
            required
            className="w-full p-2 bg-gray-800 text-white border border-gray-700 rounded-md"
          >
            <option value="" disabled>
              Select Genre
            </option>
            <option value="FPS">FPS</option>
            <option value="MOBA">MOBA</option>
            <option value="RPG">RPG</option>
            <option value="FIGHTING">Fighting</option>
            <option value="SPORTS">Sports</option>
            <option value="STRATEGY">Strategy</option>
            <option value="BATTLE_ROYALE">Battle Royale</option>
            <option value="OTHER">Other</option>
          </select>

          <select
            value={playStyle}
            onChange={(e) => setPlayStyle(e.target.value)}
            required
            className="w-full p-2 bg-gray-800 text-white border border-gray-700 rounded-md"
          >
            <option value="" disabled>
              Select Play Style
            </option>
            <option value="CASUAL">Casual</option>
            <option value="HARDCORE">Hardcore</option>
            <option value="COMPETITIVE">Competitive</option>
          </select>

          <input
            type="text"
            placeholder="Favorite Player"
            value={favoritePlayer}
            onChange={(e) => setFavoritePlayer(e.target.value)}
            className="w-full p-2 bg-gray-800 text-white border border-gray-700 rounded-md"
            required
          />

          <input
            type="number"
            placeholder="Game Hours Per Week"
            value={gameHoursPerWeek}
            onChange={(e) => setGameHoursPerWeek(e.target.value)}
            className="w-full p-2 bg-gray-800 text-white border border-gray-700 rounded-md"
            required
          />

          <button
            type="submit"
            className="w-full bg-white text-black font-bold py-2 rounded-md hover:opacity-90 transition-opacity"
            disabled={isLoading}
          >
            Submit
          </button>

          {error && <p className="text-red-500 text-sm text-center">{error}</p>}
        </form>

        {isLoading && (
          <div className="absolute inset-0 bg-black bg-opacity-60 backdrop-blur-sm flex flex-col items-center justify-center z-10">
            <div className="flex flex-row gap-2">
              <div className="w-4 h-4 rounded-full bg-white animate-bounce"></div>
              <div className="w-4 h-4 rounded-full bg-white animate-bounce [animation-delay:-.3s]"></div>
              <div className="w-4 h-4 rounded-full bg-white animate-bounce [animation-delay:-.5s]"></div>
            </div>
            <p className="text-white text-sm mt-4">Generating summary...</p>
          </div>
        )}
      </div>
    </div>
  );
}
