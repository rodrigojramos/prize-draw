/* eslint-disable @typescript-eslint/no-explicit-any */
import { useEffect, useState } from 'react';
import './styles.css';
import * as forms from "../../../utils/forms";
import * as userService from "../../../services/user-service";
import * as prizeDrawService from "../../../services/prizeDraw-service";
import { UserDTO } from '../../../models/user';
import { useNavigate, useParams } from 'react-router-dom';
import { CircleX } from 'lucide-react';
import { Link } from 'react-router-dom';

export function DrawForm() {

    const params = useParams();

    const navigate = useNavigate();

    const isEditing = params.drawId !== 'create';

    const [user, setUser] = useState<UserDTO>();

    const [awards, setAwards] = useState<string[]>([]);
    const [newAward, setNewAward] = useState<string>("");

    const [formData, setFormData] = useState<any>({
        name: {
            value: "",
            id: "name",
            name: "name",
            type: "text",
            placeholder: "Nome",
        },
        description: {
            value: "",
            id: "description",
            name: "description",
            type: "text",
            placeholder: "Descrição",
        },
        endDate: {
            value: "",
            id: "endDate",
            name: "endDate",
            type: "datetime-local",
            placeholder: "Data final do sorteio",
        },
        creator: {
            value: "",
            id: "creator",
            name: "creator",
            type: "text",
            placeholder: "Criador",
        },
        awards: {
            value: "",
            id: "awards",
            name: "awards",
            type: "text",
            placeholder: "Prêmio",
        }
    })

    useEffect(() => {
            const token = localStorage.getItem("authToken");
    
            if(token) {
                userService.findMe(token)
                    .then(response => {
                        setUser(response.data);
                    })
            }
    
        },[])

    useEffect(() => {
        if (isEditing) {
            prizeDrawService.getPrizeDrawById(String(params.drawId))
                .then(response => {
                    setFormData(forms.updateAll(formData, response.data));
                    setAwards(response.data.awards || []);
                    console.log(response.data);
                })
        }
    },[])

    function handleInputChange(event: any) {
        setFormData(forms.update(formData, event.target.name, event.target.value));
    }

    function handleNewAwardChange(event: any) {
        setNewAward(event.target.value);
      }
    
      function handleAddAward(event: any) {
        event.preventDefault();
        if (newAward.trim()) {
            const updatedAwards = [...awards, newAward.trim()];
            setAwards(updatedAwards);
            setNewAward("");
            setFormData(forms.update(formData, "awards", updatedAwards));
        }
    }

    function handleRemoveAward(index: number) {
        const updatedAwards = awards.filter((_, i) => i !== index);
        setAwards(updatedAwards);
        setFormData(forms.update(formData, "awards", updatedAwards));
    }

    function handleSubmit(event: any) {
        event.preventDefault();

        const requestBody = forms.toValues(formData);
        const token = localStorage.getItem("authToken");

        if(isEditing) {
            requestBody.id = params.drawId;
        }

        requestBody.creator = user;

        const request = isEditing 
        ? prizeDrawService.updatePrizeDraw(requestBody)
        : prizeDrawService.insertPrizeDraw(requestBody, String(token));

        request
            .then(() => {
                console.log(requestBody);
                if (isEditing) {
                    navigate(`/admin/draw-details/${requestBody.id}`)
                } else {
                    navigate("/admin");
                }
            })
    }

    return(
        <section className="prize-draw-section-form">
            <form onSubmit={handleSubmit}>
                <div className="prize-draw-form">
                    <h2>DADOS DO SORTEIO</h2>
                    <input 
                        name="name"
                        value={formData.name.value}
                        type="text"
                        placeholder="Nome"
                        className="prize-draw-form-control"
                        onChange={handleInputChange}
                    />
                    <textarea 
                        name="description"
                        value={formData.description.value}
                        placeholder="Descrição"
                        className="prize-draw-form-text-area"
                        onChange={handleInputChange} 
                    />
                    <input 
                        name="endDate"
                        value={formData.endDate.value} 
                        type="datetime-local"
                        placeholder="Data final do sorteio"
                        className="prize-draw-form-control"
                        onChange={handleInputChange}
                    />
                    <input 
                        name="creator"
                        value={user?.name}
                        type="text"
                        placeholder="Criador"
                        className="prize-draw-form-control"
                        readOnly={true}
                        onChange={handleInputChange}
                    />

                    <div className="prize-draw-form-awards">
                        <div className="prize-draw-form-awards-input">
                            <input
                                value={newAward}
                                type="text"
                                placeholder="Adicione um prêmio"
                                className="prize-draw-form-control"
                                onChange={handleNewAwardChange}
                            />
                            <button className="prize-draw-btn-add-award" onClick={handleAddAward}>Adicionar</button>
                        </div>
                        <div className="prize-draw-awards">
                            <ul className="prize-draw-form-awards-list">
                                {awards.map((award, index) => (
                                <li key={index} className="prize-draw-form-award-item">
                                    {award}
                                    <CircleX className="prize-draw-btn-remove-award" onClick={() => handleRemoveAward(index)}/>
                                </li>
                                ))}
                            </ul>
                        </div>

                    </div>

                    <div className="prize-draw-form-btns">
                        {
                            isEditing ? (
                                <Link to={`/admin/draw-details/${params.drawId}`}>
                                    <button className="prize-draw-btn">
                                        Cancelar
                                    </button>
                                </Link>
                            ) : (
                                <Link to="/admin">
                                    <button className="prize-draw-btn">
                                        Cancelar
                                    </button>
                                </Link>
                            )
                        }
                        <button className="prize-draw-btn">
                            Salvar
                        </button>
                    </div>
                </div>
            </form>
        </section>
    )
}