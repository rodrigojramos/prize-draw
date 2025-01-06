import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Admin } from "./routes/Admin";
import { AdminArea } from "./routes/Admin/AdminArea";
import { ClientHome } from "./routes/ClientHome";
import { Home } from "./routes/ClientHome/Home";
import { DrawForm } from "./routes/Admin/DrawForm";
import { DrawDetails } from "./routes/Admin/DrawDetails";

export default function App() {
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ClientHome />}>
          <Route index element={<Home />} />
        </Route>
        <Route path="/admin" element={<Admin />}>
          <Route index element={<AdminArea />} />
          <Route path="new-draw" element={<DrawForm />} />
          <Route path="draw-details/:drawId" element={<DrawDetails />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}
