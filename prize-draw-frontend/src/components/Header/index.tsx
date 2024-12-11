import './styles.css';
import IconLogo from "../../assets/svg/IconLogo.svg";

export function Header() {
    return(
        <header>
            <div className="prize-draw-header">
                <img src={IconLogo} alt="Logotipo" />
                Prize Draw
            </div>
        </header>
    )
}