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
                    <p>Rodrigo</p>
                    <Settings className="prize-draw-icon-size"/>
                </div>
            </div>
        </header>
    )
}