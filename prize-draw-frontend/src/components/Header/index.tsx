import './styles.css';
import IconLogo from "../../assets/svg/IconLogo.svg";
import { useNavigate } from 'react-router-dom';

export function Header() {

    const navigate = useNavigate();

    function handleLogoClick() {
        const token = localStorage.getItem("authToken");

        if(token) {
            navigate("/admin");
        } else {
            navigate("/");
        }
    }

    return(
        <header>
            <div className="prize-draw-header">
                <div onClick={handleLogoClick} className="prize-draw-header-logo">
                    <img src={IconLogo} alt="Logotipo" />
                    Prize Draw
                </div>
            </div>
        </header>
    )
}