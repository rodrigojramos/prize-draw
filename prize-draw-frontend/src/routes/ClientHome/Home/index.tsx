import { useState } from 'react';
import './styles.css';
import * as authService from "../../../services/auth-service";
import { useNavigate } from 'react-router-dom';

export function Home() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");

    async function handleLogin() {
        try {
            const token = await authService.login(email); 
            localStorage.setItem("authToken", token);
            console.log(token);
            console.log(email);
            navigate("/admin");
        } catch {
            return null;
        }
    }

    return(
        <section className="prize-draw-section-home">
            <div className="prize-draw-main-sentence">
                <h2>A plataforma de sorteios que você pode confiar!</h2>
            </div>
            <div className="prize-draw-main-text">
                <p>Planeje, organize e realize seus sorteios de forma prática e segura. Perfeito para criadores, empresas e influenciadores.</p>
            </div>
            <div className="prize-draw-home-login">
                <div className="prize-draw-login">
                    <p>Acesse sua conta:</p>
                    <input 
                        type="text"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <button onClick={handleLogin} className="prize-draw-btn">
                        Entrar
                    </button>
                </div>
            </div>
        </section>
    )
}