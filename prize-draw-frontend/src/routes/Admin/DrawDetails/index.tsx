import { BookOpenText, CircleArrowRight, Logs, NotebookText, Pencil, Trash2, Trophy } from 'lucide-react';
import './styles.css';
import { useEffect, useState } from 'react';
import { PrizeDrawDTO } from '../../../models/prizeDraw';
import * as prizeDrawService from "../../../services/prizeDraw-service";
import * as participantService from "../../../services/participant-service";
import * as logsService from "../../../services/logs-service";
import { useNavigate, useParams } from 'react-router-dom';
import { ParticipantDTO } from '../../../models/participant';
import { auditLogDTO } from '../../../models/auditLog';
import { Link } from 'react-router-dom';

export function DrawDetails() {

    const params = useParams();
    const navigate = useNavigate();

    const [selectedTab, setSelectedTab] = useState<'information' | 'participants' | 'logs' | 'edit' | 'delete' | 'winners'>('information');

    const [draw, setDraw] = useState<PrizeDrawDTO>();

    const [participants, setParticipants] = useState<ParticipantDTO[]>([]);

    const [logs, setLogs] = useState<auditLogDTO[]>([]);

    const [formattedCreationDate, setFormattedCreationDate] = useState<string>('');
    const [formattedEndDate, setFormattedEndDate] = useState<string>('');
    const [timeRemaining, setTimeRemaining] = useState<string>('00D:00H:00M');
    
    const formatCPF = (cpf: string) => {
        return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
    };

    const formatLogDate = (logDate: string) => {
        const date = new Date(logDate);
    
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
    
        return `${day}/${month}/${year} às ${hours}:${minutes}`;
    };

    const updateTimeRemaining = (endDate: string) => {
        const interval = setInterval(() => {
            const end = new Date(endDate);
            const now = new Date();
            const timeDiff = end.getTime() - now.getTime();

            if (timeDiff <= 0) {
                setTimeRemaining('00D:00H:00M');
                clearInterval(interval);
                return;
            }

            const days = Math.floor(timeDiff / (1000 * 3600 * 24));
            const hours = Math.floor((timeDiff % (1000 * 3600 * 24)) / (1000 * 3600));
            const minutes = Math.floor((timeDiff % (1000 * 3600)) / (1000 * 60));

            setTimeRemaining(`${String(days).padStart(2, '0')}D:${String(hours).padStart(2, '0')}H:${String(minutes).padStart(2, '0')}M`);
        }, 1000);

        return () => clearInterval(interval);
    };

    useEffect(() => {
        prizeDrawService.getPrizeDrawById(String(params.drawId))
            .then(response => {
                const drawData = response.data;
                setDraw(drawData);
    
                const dateCreation = new Date(drawData.creationDate);
                const creationDay = String(dateCreation.getDate()).padStart(2, '0');
                const creationMonth = String(dateCreation.getMonth() + 1).padStart(2, '0');
                const creationYear = dateCreation.getFullYear();
                const formattedCreationDate = `${creationDay}/${creationMonth}/${creationYear}`;
                setFormattedCreationDate(formattedCreationDate);
    
                const dateEnd = new Date(drawData.endDate);
                const endDay = String(dateEnd.getDate()).padStart(2, '0');
                const endMonth = String(dateEnd.getMonth() + 1).padStart(2, '0');
                const endYear = dateEnd.getFullYear();
                const formattedEndDate = `${endDay}/${endMonth}/${endYear}`;
                setFormattedEndDate(formattedEndDate);
    
                if (drawData.participantsId) {
                    Promise.all(
                        drawData.participantsId.map((id: string) =>
                            participantService.getParticipantById(id)
                        )
                    )
                    .then((responses) => {
                        const participantsData = responses.map(response => response.data);
                        setParticipants(participantsData);
                    });
                }

                if (drawData.auditLogsId) {
                    Promise.all(
                        drawData.auditLogsId.map((id: string) => 
                            logsService.getLogById(id)
                        )
                    )
                    .then((responsess) => {
                        const logsData = responsess.map(response => response.data);
                        setLogs(logsData);
                    })
                }

                updateTimeRemaining(drawData.endDate);
            })
    }, [params.drawId]);

    function handleDeleteClick() {
        prizeDrawService.deletePrizeDrawById(String(params.drawId));
        navigate("/admin");
        window.location.reload();
    }

    function handleWinnersClick() {
        prizeDrawService.winnersDraw(String(params.drawId))
            .then(response => {
                console.log(response.data);
                window.location.reload();
            })
    }

    return(
        <section className="prize-draw-details-section">
            <div className="prize-draw-details-name">
                <h2>{draw?.name}</h2>
                <Link to={`/draw/${params.drawId}`}>
                    <div className="prize-draw-details-link">
                        <p>Página para se registrar </p>
                        <CircleArrowRight size={14}/>
                    </div>
                </Link>
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
                <Link to={`/admin/draw/${params.drawId}`}>
                    <div className={"prize-draw-details-options-edit-delete"}>
                        <Pencil />
                        <p>Editar</p>
                    </div>
                </Link>
                <div className={`prize-draw-details-options-edit-delete ${selectedTab === 'delete' ? 'active' : ''}`}
                    onClick={() => setSelectedTab('delete')}>
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
                                <p className="prize-draw-details-text">{draw?.description}</p>
                            </div>
                            <div className="prize-draw-details-description">
                                <h5>Data de criação:</h5>
                                <p className="prize-draw-details-text">{formattedCreationDate}</p>
                            </div>
                            <div className="prize-draw-details-description">
                                <h5>Data de encerramento:</h5>
                                <p className="prize-draw-details-text">{formattedEndDate}</p>
                            </div>
                            <div className="prize-draw-details-description">
                                <h5>Criador:</h5>
                                <p className="prize-draw-details-text">{draw?.creator.name}</p>
                            </div>
                            <h5 className="prize-draw-details-awards">Prêmios:</h5>
                            {draw?.awards?.map((award, index) => (
                                <p key={index}>{index + 1}º - {award}</p>
                            ))}
                        </div>
                    </>
                }
                {
                    selectedTab === 'participants' &&
                    <>
                        {
                            participants.length > 0 ? (
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
                                            {participants.length > 0 ? (
                                                participants.map((participant, index) => (
                                                    <tr key={index}>
                                                        <td>{participant.name}</td>
                                                        <td>{participant.email}</td>
                                                        <td>{formatCPF(participant.document)}</td>
                                                    </tr>
                                                ))
                                            ) : (
                                                null
                                            )}
                                        </tbody>
                                    </table>
                                </div>
                            ) : (
                                <p className="prize-draw-details-list-participants-empty">Nenhum participante inscrito até o momento.</p>
                            )
                        }

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
                                    {logs.length > 0 ? (
                                        logs.map((log, index) => (
                                            <tr key={index}>
                                                <td>{log.action}</td>
                                                <td>{log.details} ({formatLogDate(log.date)})</td>
                                            </tr>
                                        ))
                                    ) : (
                                        null
                                    )}
                                </tbody>
                            </table>
                        </div>
                    </>
                }
                {
                    selectedTab === 'delete' && 
                    <>
                        <div className="prize-draw-details-delete">
                            <p>Tem certeza que deseja deletar este sorteio?</p>
                            <button onClick={handleDeleteClick} className="prize-draw-btn">
                                Sim
                            </button>
                            <button className="prize-draw-btn" onClick={() => setSelectedTab('information')}>
                                Não
                            </button>
                        </div>
                    </>
                }
                {
                  selectedTab === 'winners' && 
                  <>
                      <div className="prize-draw-details-participants-winners">
                        <h4>Vencedores:</h4>
                        {draw?.winners?.map((winner, index) => (
                            <p key={index}>
                            <strong>{`${index + 1}º Lugar:`}</strong> {winner.name}
                            </p>
                        ))} 
                      </div>
                  </>  
                }
            </div>
            <div className="prize-draw-details-timer">
                {
                    draw?.winners && draw.winners.length > 0 ? (
                        <div className="prize-draw-details-winners">
                            <span>Sorteio já foi realizado!</span>
                            <div className={`prize-draw-details-winners-btn ${selectedTab === 'winners' ? 'active' : ''}`}
                                onClick={() => setSelectedTab('winners')}>
                                <Trophy />
                                <p>Vencedores</p>
                            </div>
                        </div>
                    ) : (
                            timeRemaining === '00D:00H:00M' ? (
                                <>
                                    <p>{timeRemaining}</p>
                                    <button onClick={handleWinnersClick} className="prize-draw-btn">
                                        Sortear
                                    </button>
                                </> 
                            ) : (
                                <>
                                    <p>{timeRemaining}</p>
                                    <button className="prize-draw-details-btn">
                                        Sortear
                                    </button>
                                </>
                            )
                    )
                }
            </div>
        </section>
    )
}