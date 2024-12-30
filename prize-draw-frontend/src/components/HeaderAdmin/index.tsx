import './styles.css';
import IconLogo from "../../assets/svg/IconLogo.svg";
import { Settings } from 'lucide-react';
import { useEffect, useState } from 'react';
import * as userService from '../../services/user-service'
import { UserDTO } from '../../models/user';

export function HeaderAdmin() {

    const [user, setUser] = useState<UserDTO>();

    useEffect(() => {
            const token = localStorage.getItem("authToken");
    
            if(token) {
                userService.findMe(token)
                    .then(response => {
                        console.log(response.data);
                        setUser(response.data);
                    })
            }
    
        },[])

    return(
        <header>
            <div className="prize-draw-admin-header">
                <div className='prize-draw-admin-header-logo'>
                    <img src={IconLogo} alt="Logotipo" />
                    Prize Draw
                </div>
                <div className="prize-draw-admin-header-settings">
                    <div className="prize-draw-admin-header-name">
                        <p>{user?.name}</p>
                        <span>Sair</span>
                    </div>
                    <Settings className="prize-draw-icon-size"/>
                </div>
            </div>
        </header>
    )
}