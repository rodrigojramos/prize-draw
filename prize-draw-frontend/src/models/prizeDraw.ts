import { ParticipantDTO } from "./participant";
import { UserDTO } from "./user";

export type PrizeDrawDTO = {
    id: string;
    name: string;
    description: string;
    creationDate: string;
    endDate: string;
    creator: UserDTO;
    awards: string[];
    participantsId: Set<string>;
    auditLogsId: string[];
    winners: ParticipantDTO[];
};