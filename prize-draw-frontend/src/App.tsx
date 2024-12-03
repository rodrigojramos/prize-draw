import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Admin } from "./routes/Admin";
import { AdminArea } from "./routes/Admin/AdminArea";

export default function App() {
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Admin />}>
          <Route index element={<AdminArea />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}
