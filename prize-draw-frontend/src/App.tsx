import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Admin } from "./routes/Admin";
import { AdminArea } from "./routes/Admin/AdminArea";
import { ClientHome } from "./routes/ClientHome";
import { Home } from "./routes/ClientHome/Home";
import { DrawForm } from "./routes/Admin/DrawForm";
import { DrawDetails } from "./routes/Admin/DrawDetails";
import { PrizeDrawPage } from "./routes/ClientHome/PrizeDrawPage";

export default function App() {
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ClientHome />}>
          <Route index element={<Home />} />
          <Route path="draw/:drawId" element={<PrizeDrawPage />} />
        </Route>
        <Route path="/admin" element={<Admin />}>
          <Route index element={<AdminArea />} />
          <Route path="draw/:drawId" element={<DrawForm />} />
          <Route path="draw-details/:drawId" element={<DrawDetails />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}
