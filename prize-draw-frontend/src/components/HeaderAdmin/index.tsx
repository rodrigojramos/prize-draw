import './styles.css';
import IconLogo from "../../assets/svg/IconLogo.svg";
import { Settings } from 'lucide-react';

export function HeaderAdmin() {
    return(
        <header>
            <div className="prize-draw-header">
                <div className='prize-draw-header-logo'>
                    <img src={IconLogo} alt="Logotipo" />
                    Prize Draw
                </div>
                <div className="prize-draw-header-settings">
                    <div className="prize-draw-header-name">
                        <p>Rodrigo</p>
                        <span>Sair</span>
                    </div>
                    <Settings className="prize-draw-icon-size"/>
                </div>
            </div>
        </header>
    )
}