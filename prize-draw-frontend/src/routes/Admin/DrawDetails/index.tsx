import { BookOpenText, Logs, NotebookText, Pencil, Trash2 } from 'lucide-react';
import './styles.css';
import { useState } from 'react';

export function DrawDetails() {

    const [selectedTab, setSelectedTab] = useState<'information' | 'participants' | 'logs'>('information');


    return(
        <section className="prize-draw-details-section">
            <div className="prize-draw-details-name">
                <h2>Sorteio de ano novo com Rodrigo</h2>
            </div>
            <div className="prize-draw-details-options">
                <div className={`prize-draw-details-option-information ${selectedTab === 'information' ? 'active' : ''}`}
                    onClick={() => setSelectedTab('information')}>
                    <BookOpenText />
                    <p>Informações</p>
                </div>
                <div className={`prize-draw-details-option-list-participants ${selectedTab === 'participants' ? 'active' : ''}`}
                    onClick={() => setSelectedTab('participants')}>
                    <NotebookText />
                    <p>Participantes</p>
                </div>
                <div className={`prize-draw-details-option-logs ${selectedTab === 'logs' ? 'active' : ''}`}
                    onClick={() => setSelectedTab('logs')}>
                    <Logs />
                    <p>Logs</p>
                </div>
                <div className="prize-draw-details-options-edit-delete">
                    <Pencil />
                    <p>Editar</p>
                </div>
                <div className="prize-draw-details-options-edit-delete">
                    <Trash2 />
                    <p>Excluir</p>
                </div>
            </div>
            <div className="prize-draw-details-content">
                {
                    selectedTab === 'information' &&
                    <>
                        <div className="prize-draw-details-informations">
                            <div className="prize-draw-details-description">
                                <h5>Descrição:</h5>
                                <p className="prize-draw-details-text">Prepare-se para começar o ano com sorte e prêmios incríveis! Nosso querido streamer Rodrigo está celebrando o início de um novo ciclo com um sorteio especial para todos os seguidores. Participe para concorrer a prêmios exclusivos e começar 2025 com o pé direito!</p>
                            </div>
                            <div className="prize-draw-details-description">
                                <h5>Data de criação:</h5>
                                <p className="prize-draw-details-text">10/12/2024</p>
                            </div>
                            <div className="prize-draw-details-description">
                                <h5>Data de encerramento:</h5>
                                <p className="prize-draw-details-text">31/12/2024</p>
                            </div>
                            <div className="prize-draw-details-description">
                                <h5>Criador:</h5>
                                <p className="prize-draw-details-text">Rodrigo</p>
                            </div>
                            <h5 className="prize-draw-details-awards">Prêmios:</h5>
                            <p>1º - CPU Gamer</p>
                            <p>2º - Monitor</p>
                            <p>3º - Fone</p>
                            <p>4º - Mouse e teclado</p>
                        </div>
                    </>
                }
                {
                    selectedTab === 'participants' &&
                    <>
                        <div className="prize-draw-details-list-participant">
                            <table className="prize-draw-details-table-list-participants">
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>E-mail</th>
                                        <th>Documento</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Luffy</td>
                                        <td>luffy@gmail.com</td>
                                        <td>111.222.333-44</td>
                                    </tr>
                                    <tr>
                                        <td>Zoro</td>
                                        <td>zoro@gmail.com</td>
                                        <td>555.666.777-88</td>
                                    </tr>
                                    <tr>
                                        <td>Sanji</td>
                                        <td>sanji@gmail.com</td>
                                        <td>999.000.111-22</td>
                                    </tr>

                                    <tr>
                                        <td>Luffy</td>
                                        <td>luffy@gmail.com</td>
                                        <td>111.222.333-44</td>
                                    </tr>
                                    <tr>
                                        <td>Zoro</td>
                                        <td>zoro@gmail.com</td>
                                        <td>555.666.777-88</td>
                                    </tr>
                                    <tr>
                                        <td>Sanji</td>
                                        <td>sanji@gmail.com</td>
                                        <td>999.000.111-22</td>
                                    </tr>

                                    <tr>
                                        <td>Luffy</td>
                                        <td>luffy@gmail.com</td>
                                        <td>111.222.333-44</td>
                                    </tr>
                                    <tr>
                                        <td>Zoro</td>
                                        <td>zoro@gmail.com</td>
                                        <td>555.666.777-88</td>
                                    </tr>
                                    <tr>
                                        <td>Sanji</td>
                                        <td>sanji@gmail.com</td>
                                        <td>999.000.111-22</td>
                                    </tr>

                                </tbody>
                            </table>
                        </div>
                    </>
                }
                {
                    selectedTab === 'logs' && 
                    <>
                        <div className="prize-draw-details-list-logs">
                            <table className="prize-draw-details-table-list-logs">
                                <thead>
                                    <tr>
                                        <th>Ação</th>
                                        <th>Detalhes</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Usuário registrado!</td>
                                        <td>Participante Rodrigo de ID 6772a00af4890e1e37be79de cadastrado no sorteio às 11:00 do dia 14/11/2024.</td>
                                    </tr>
                                    <tr>
                                        <td>Usuário registrado!</td>
                                        <td>Participante Rodrigo de ID 6772a00af4890e1e37be79de cadastrado no sorteio às 11:00 do dia 14/11/2024.</td>
                                    </tr>
                                    <tr>
                                        <td>Usuário registrado!</td>
                                        <td>Participante Rodrigo de ID 6772a00af4890e1e37be79de cadastrado no sorteio às 11:00 do dia 14/11/2024.</td>
                                    </tr>
                                    <tr>
                                        <td>Usuário registrado!</td>
                                        <td>Participante Rodrigo de ID 6772a00af4890e1e37be79de cadastrado no sorteio às 11:00 do dia 14/11/2024.</td>
                                    </tr>
                                    <tr>
                                        <td>Usuário registrado!</td>
                                        <td>Participante Rodrigo de ID 6772a00af4890e1e37be79de cadastrado no sorteio às 11:00 do dia 14/11/2024.</td>
                                    </tr>

                                </tbody>
                            </table>
                        </div>
                    </>
                }
            </div>
            <div className="prize-draw-details-timer">
                <p>20D:14H:32M</p>
            </div>
        </section>
    )
}