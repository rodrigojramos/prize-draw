import './styles.css';
import IconLogo from "../../assets/svg/IconLogo.svg";
import { Settings } from 'lucide-react';
import { useEffect, useState } from 'react';
import * as userService from '../../services/user-service';
import * as authService from '../../services/auth-service';
import { UserDTO } from '../../models/user';
import { Link, useNavigate } from 'react-router-dom';

export function HeaderAdmin() {
    
    const navigate = useNavigate();

    const [user, setUser] = useState<UserDTO>();

    useEffect(() => {
            const token = localStorage.getItem("authToken");
    
            if(token) {
                userService.findMe(token)
                    .then(response => {
                        setUser(response.data);
                    })
            }
    
        },[])

    async function handleLogout() {
        authService.logout()
            .then(() => {
                navigate("/")
            })
    }
            
    return(
        <header>
            <div className="prize-draw-admin-header">
                <Link to="/admin">
                    <div className='prize-draw-admin-header-logo'>
                        <img src={IconLogo} alt="Logotipo" />
                        Prize Draw
                    </div>
                </Link>
                <div className="prize-draw-admin-header-settings">
                    <div className="prize-draw-admin-header-name">
                        <p>{user?.name}</p>
                        <span onClick={handleLogout}>Sair</span>
                    </div>
                    <Link to="/admin">
                        <Settings className="prize-draw-icon-size"/>
                    </Link>
                </div>
            </div>
        </header>
    )
}