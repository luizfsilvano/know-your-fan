import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

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
