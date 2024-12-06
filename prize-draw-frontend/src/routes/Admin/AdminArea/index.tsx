import './styles.css';
import { CircleChevronRight } from 'lucide-react';


export function AdminArea() {

    function handleClickIconCard() {

    }

    return(
        <>
            <section className="prize-draw-admin-area-container">
                <div className="prize-draw-admin-area-welcome">
                    <p>Olá Rodrigo!</p>
                    <p>Bem-vindo à área administrativa! Aqui você pode visualizar e acompanhar todos os seus sorteios criados.</p>
                </div>
                <div className="prize-draw-containers">
                    <div className="prize-draw-container">
                        <h4>Sorteios em andamento</h4>
                        <span>Você possui 3 sorteios em andamento.</span>
                        <div className="prize-draw-card">
                            <p>Sorteio de final do ano</p>
                            <CircleChevronRight onClick={handleClickIconCard} className="prize-draw-icon-size"/>
                        </div>

                        <div className="prize-draw-card">
                            <p>Sorteio de final do ano</p>
                            <CircleChevronRight className="prize-draw-icon-size"/>
                        </div>

                        <div className="prize-draw-card">
                            <p>Sorteio de final do ano</p>
                            <CircleChevronRight className="prize-draw-icon-size"/>
                        </div>
                        <button className="prize-draw-btn">
                            Criar novo sorteio
                        </button>
                    </div>

                    <div className="prize-draw-container">
                        <h4>Sorteios finalizados</h4>
                        <span>Você possui 3 sorteios finalizados.</span>
                        <div className="prize-draw-card">
                            <p>Sorteio de final do ano</p>
                            <CircleChevronRight className="prize-draw-icon-size"/>
                        </div>

                        <div className="prize-draw-card">
                            <p>Sorteio de final do ano</p>
                            <CircleChevronRight className="prize-draw-icon-size"/>
                        </div>

                        <div className="prize-draw-card">
                            <p>Sorteio de final do ano</p>
                            <CircleChevronRight className="prize-draw-icon-size"/>
                        </div>

                    </div>
                </div>
            </section>
        </>
    )
}