/* eslint-disable @typescript-eslint/no-explicit-any */
import { useParams } from 'react-router-dom';
import './styles.css';
import { useEffect, useState } from 'react';
import { PrizeDrawDTO } from '../../../models/prizeDraw';
import * as forms from "../../../utils/forms";
import * as prizeDrawService from '../../../services/prizeDraw-service';
import * as participantService from '../../../services/participant-service';
import { ParticipantDTO } from '../../../models/participant';

export function PrizeDrawPage() {

    const params = useParams();

    const [prizeDraw, setPrizeDraw] = useState<PrizeDrawDTO>();

    const [formattedEndDate, setFormattedEndDate] = useState<string>('');
    const [timeRemaining, setTimeRemaining] = useState<string>('00D:00H:00M');

    const [participant, setParticipant] = useState<ParticipantDTO>();
    const [registeredParticipant, setRegisteredParticipant] = useState<boolean>(false);

    const [formData, setFormData] = useState<any>({
        name: {
            value: "",
            id: "name",
            name: "name",
            type: "text",
            placeholder: "Nome",
        },
        document: {
            value: "",
            id: "document",
            name: "document",
            type: "text",
            placeholder: "Documento",
        },
        email: {
            value: "",
            id: "email",
            name: "email",
            type: "text",
            placeholder: "E-mail",
        },
    })

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
                const prizeDrawData = response.data;
                setPrizeDraw(prizeDrawData);
    
                const dateEnd = new Date(prizeDrawData.endDate);
                const endDay = String(dateEnd.getDate()).padStart(2, '0');
                const endMonth = String(dateEnd.getMonth() + 1).padStart(2, '0');
                const endYear = dateEnd.getFullYear();
                const formattedEndDate = `${endDay}/${endMonth}/${endYear}`;
                setFormattedEndDate(formattedEndDate);

                updateTimeRemaining(prizeDrawData.endDate);
            })
    },[])

    function handleInputChange(event: any) {
            setFormData(forms.update(formData, event.target.name, event.target.value));
        }

    function handleSubmit(event: any) {
        event.preventDefault();

        const requestBody = forms.toValues(formData);

        participantService.insertParticipant(requestBody, String(params.drawId))
            .then(response => {
                console.log(response.data);
                setParticipant(requestBody);
                setRegisteredParticipant(true);
            })
    }

    function handleNewRegisterClick() {
        setRegisteredParticipant(false);
        setFormData({
            name: "",
            document: "",
            email: "",
          });
    }

    return(
        <section className="prize-draw-page-section">
            <div className="prize-draw-page-card">
                <div className="prize-draw-page-title">
                    <h3>{prizeDraw?.name}</h3>
                </div>
                <div className="prize-draw-page-creator">
                    <h4>Sorteio criado por:</h4>
                    <p>{prizeDraw?.creator.name}</p>
                </div>
                <div className="prize-draw-page-end-date">
                    <h4>As inscrições finalizam no dia:</h4>
                    <p>{formattedEndDate}</p>
                </div>
                <div className="prize-draw-page-description">
                    <p><strong>Descrição:</strong>{prizeDraw?.description}</p> 
                </div>
                <div className="prize-draw-page-awards">
                    <h4>Premiação:</h4>
                    <div className="prize-draw-page-awards-organization">
                        {prizeDraw?.awards?.map((award, index) => (
                            <p key={index}>{index + 1}º - {award}</p>
                        ))}
                    </div>
                </div>
                <div className="prize-draw-page-timer">
                    <p>{timeRemaining}</p>
                </div>
                <div className="prize-draw-page-obs">
                    <p><strong>Observações:</strong>As inscrições serão encerradas assim que o timer chegar a zero, porém o sorteio só será realizado quando o criador do sorteio quiser.</p>
                </div>
            </div>
            {
                registeredParticipant == false ? (
                    <form onSubmit={handleSubmit}>
                        <div className="prize-draw-page-register">
                            <h4>Para se inscrever preencha seus dados abaixo:</h4>
                            <div className="prize-draw-page-register-inputs">
                                <input 
                                    name="name"
                                    value={formData.name.value}
                                    type="text"
                                    placeholder="Nome"
                                    onChange={handleInputChange}
                                />
                                <input 
                                    name="document"
                                    value={formData.document.value}
                                    type="text"
                                    placeholder="Documento"
                                    onChange={handleInputChange}
                                />
                                <input 
                                    name="email"
                                    value={formData.email.value}
                                    type="text"
                                    placeholder="E-mail"
                                    onChange={handleInputChange}
                                />
                            </div>
                            <div className="prize-draw-page-register-btn">
                                <button>Registrar</button>
                            </div>
                        </div>
                    </form>
                ) : (
                    <div className="prize-draw-page-register">
                        <div className="prize-draw-page-participant-registered">
                            <p>{participant?.name}, você foi registrado no sorteio com sucesso.</p>
                            <span>Para realizar um novo registro clique no botão abaixo:</span>
                            <div className="prize-draw-page-register-btn">
                                <button onClick={handleNewRegisterClick}>
                                    Novo Registro
                                </button>
                            </div>
                        </div>
                    </div>
                )
            }
        </section>
    )
}