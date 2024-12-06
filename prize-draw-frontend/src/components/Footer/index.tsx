import './styles.css';
import IconLogo from "../../assets/svg/IconLogo.svg";

export function Footer() {
    return(
        <>
            <footer>
                <div className="prize-draw-footer">
                    <img src={IconLogo} alt="Logotipo" />
                    <h3>Prize Draw</h3>
                    <p>A casa dos melhores sorteios online.</p>
                </div>
            </footer>
        </>
    )
}