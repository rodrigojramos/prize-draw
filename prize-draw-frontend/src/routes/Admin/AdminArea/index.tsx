import { useEffect, useState } from 'react';
import { PrizeDrawCard } from '../../../components/PrizeDrawCard';
import './styles.css';
import { PrizeDrawDTO } from '../../../models/prizeDraw';
import * as prizeDrawService from '../../../services/prizeDraw-service';
import * as userService from '../../../services/user-service';
import { UserDTO } from '../../../models/user';
import { Link } from 'react-router-dom';

export function AdminArea() {

    const [onGoingPrizeDraws, setOnGoingPrizeDraws] = useState<PrizeDrawDTO[]>([]);
    const [finishedPrizeDraws, setFinishedPrizeDraws] = useState<PrizeDrawDTO[]>([]);

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

    useEffect(() => {
        if(user?.id) {
            prizeDrawService.getOngoingPrizeDrawsByUser(user.id)
                .then(response => {
                    setOnGoingPrizeDraws(response.data);
                });
        }
    },[user?.id])

    useEffect(() => {
        if(user?.id) {
            prizeDrawService.getFinishedPrizeDrawsByUser(user.id)
                .then(response => {
                    setFinishedPrizeDraws(response.data);
                });
        }
    },[user?.id])

    return(
        <>
            <section className="prize-draw-admin-area-container">
                <div className="prize-draw-admin-area-welcome">
                    <p>Olá {user?.name}!</p>
                    <p>Bem-vindo à área administrativa! Aqui você pode visualizar e acompanhar todos os seus sorteios criados.</p>
                </div>
                <div className="prize-draw-containers">
                    <div className="prize-draw-container">
                        <h4>Sorteios em andamento</h4>
                        {
                            onGoingPrizeDraws.length >= 1 ? (
                                <span>Você possui {onGoingPrizeDraws.length} sorteio em andamento.</span>
                            ) : (
                                <span>Você possui {onGoingPrizeDraws.length} sorteios em andamento.</span>
                            )
                        }

                        {
                            onGoingPrizeDraws.map((draw => (
                                <PrizeDrawCard key={draw.id} draw={draw}/>
                            )))
                        }
                        <Link to="/admin/draw/create">
                            <button className="prize-draw-btn">
                                Criar novo sorteio
                            </button>
                        </Link>
                    </div>

                    <div className="prize-draw-container">
                        <h4>Sorteios finalizados</h4>
                        {
                            finishedPrizeDraws.length >= 1 ? (
                                <span>Você possui {finishedPrizeDraws.length} sorteio finalizados.</span>
                            ) : (
                                <span>Você possui {finishedPrizeDraws.length} sorteios finalizados.</span>
                            )
                        }

                        {
                            finishedPrizeDraws.map((draw => (
                                <PrizeDrawCard key={draw.id} draw={draw}/>
                            )))
                        }
    
                    </div>
                </div>
            </section>
        </>
    )
}