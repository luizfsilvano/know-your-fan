import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

// ===== Auth Types =====
export interface AuthResponse {
  token: string;
}

export function signup(data: {
  username: string;
  email: string;
  password: string;
}) {
  return api.post<AuthResponse>("/api/auth/signup", data);
}

export function login(data: { email: string; password: string }) {
  return api.post<AuthResponse>("/api/auth/signin", data);
}

// ===== Fan Profile Types =====
export interface FanProfileRequest {
  nickname: string;
  preferredPlatform: string;
  favoriteGenre: string;
  playStyle: string;
  favoritePlayer: string;
  favoriteTeam: string;
  gameHoursPerWeek: string;
}

export interface FanProfileResponse {
  profileSummary: string;
}

export function createFanProfile(data: FanProfileRequest, token: string) {
  return api.post<FanProfileResponse>("/api/v1/fan-profile/generate", data, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}
