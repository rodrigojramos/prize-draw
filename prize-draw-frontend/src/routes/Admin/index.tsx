import { Outlet } from "react-router-dom";
import { HeaderAdmin } from "../../components/HeaderAdmin";

export function Admin() {
    return(
        <>
            <HeaderAdmin />
            <Outlet />
        </>
    )
}