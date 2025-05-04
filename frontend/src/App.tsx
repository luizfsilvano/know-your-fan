import { BrowserRouter, Routes, Route } from "react-router-dom";
import AuthPage from "./pages/AuthPage";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/auth" element={<AuthPage />} />
        {/* futuras rotas como FanFormPage etc */}
      </Routes>
    </BrowserRouter>
  );
}
