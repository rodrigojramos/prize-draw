import { CircleChevronRight } from 'lucide-react';
import './styles.css';
import { PrizeDrawDTO } from '../../models/prizeDraw';

type Props = {
    draw: PrizeDrawDTO;
}

export function PrizeDrawCard({ draw }: Props) {
    return(
        <div className="prize-draw-card">
            <p>{draw.name}</p>
            <CircleChevronRight className="prize-draw-icon-size"/>
        </div>
    )
}