import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import AuthPage from "./pages/AuthPage";
import FanForm from "./pages/FanForm";
import FanProfileResult from "./pages/FanProfileResult";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/auth" replace />} />
        <Route path="/auth" element={<AuthPage />} />
        <Route path="/fan-form" element={<FanForm />} />
        <Route path="/fan-profile-result" element={<FanProfileResult />} />
      </Routes>
    </BrowserRouter>
  );
}
