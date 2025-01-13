import { CircleChevronRight } from 'lucide-react';
import './styles.css';
import { PrizeDrawDTO } from '../../models/prizeDraw';
import { Link } from 'react-router-dom';

type Props = {
    draw: PrizeDrawDTO;
}

export function PrizeDrawCard({ draw }: Props) {
    return(
        <Link to={`/admin/draw-details/${draw.id}`}>
            <div className="prize-draw-card">
                <p>{draw.name}</p>
                <CircleChevronRight className="prize-draw-icon-size"/>
            </div>
        </Link>
    )
}