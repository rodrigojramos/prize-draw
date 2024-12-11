import './styles.css';

export function Home() {
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
                    <input type="text" />
                    <button className="prize-draw-btn">
                        Entrar
                    </button>
                </div>
            </div>
        </section>
    )
}