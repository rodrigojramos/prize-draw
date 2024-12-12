import './styles.css';

export function DrawForm() {
    return(
        <section className="prize-draw-section-form">
            <form>
                <div className="prize-draw-form">
                    <h2>Criar novo sorteio</h2>
                    <input 
                        type="text"
                        placeholder="Nome" 
                    />
                    <input 
                        type="text"
                        placeholder="Descrição" 
                    />
                    <input 
                        type="datetime-local"
                        placeholder="Data final do sorteio"
                    />
                    <input 
                        type="text"
                        placeholder="Criador"
                    />
                    <input 
                        type="text"
                        placeholder="Prêmio"
                    />
                    <div className="prize-draw-form-btns">
                        <button className="prize-draw-btn">
                            Cancelar
                        </button>
                        <button className="prize-draw-btn">
                            Salvar
                        </button>
                    </div>
                </div>
            </form>
        </section>
    )
}