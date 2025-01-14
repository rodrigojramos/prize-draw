import './styles.css';

export function PrizeDrawPage() {
    return(
        <section className="prize-draw-page-section">
            <div className="prize-draw-page-card">
                <div className="prize-draw-page-title">
                    <h3>Sorteio de ano novo com Rodrigo</h3>
                </div>
                <div className="prize-draw-page-creator">
                    <h4>Sorteio criado por:</h4>
                    <p>Rodrigo</p>
                </div>
                <div className="prize-draw-page-end-date">
                    <h4>As inscrições finalizam no dia:</h4>
                    <p>01/06/2025</p>
                </div>
                <div className="prize-draw-page-description">
                    <p><strong>Descrição:</strong>Prepare-se para começar o ano com sorte e prêmios incríveis! Nosso querido streamer Rodrigo está celebrando o início de um novo ciclo com um sorteio especial para todos os seguidores. Participe para concorrer a prêmios exclusivos e começar 2025 com o pé direito!</p> 
                </div>
                <div className="prize-draw-page-awards">
                    <h4>Premiação:</h4>
                    <div className="prize-draw-page-awards-organization">
                        <p>1° - CPU Gamer</p>
                        <p>2° - Monitor</p>
                    </div>
                </div>
                <div className="prize-draw-page-timer">
                    <p>30D:14H:32M</p>
                </div>
                <div className="prize-draw-page-obs">
                    <p><strong>Observações:</strong>As inscrições serão encerradas assim que o timer chegar a zero, porém o sorteio só será realizado quando o criador do sorteio quiser.</p>
                </div>
            </div>
            <form>
                <div className="prize-draw-page-register">
                    <h4>Para se inscrever preencha seus dados abaixo:</h4>
                    <div className="prize-draw-page-register-inputs">
                        <input 
                            type="text"
                            placeholder="Nome"
                        />
                        <input 
                            type="text"
                            placeholder="E-mail"
                        />
                        <input 
                            type="text"
                            placeholder="Documento"
                        />
                    </div>
                    <div className="prize-draw-page-register-btn">
                        <button>Registrar</button>
                    </div>
                </div>
            </form>
        </section>
    )
}