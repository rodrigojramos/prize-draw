import { Outlet } from "react-router-dom";
import { HeaderAdmin } from "../../components/HeaderAdmin";
import { Footer } from "../../components/Footer";

export function Admin() {
    return(
        <>
            <HeaderAdmin />
            <Outlet />
            <Footer />
        </>
    )
}