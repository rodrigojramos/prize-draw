import axios, { AxiosRequestConfig } from "axios";
import { BASE_URL } from "../utils/system";
import { ParticipantDTO } from "../models/participant";

export function getParticipantById(id: string) {
    const config : AxiosRequestConfig = {
        method: "GET",
        baseURL: BASE_URL,
        url: `/participants/${id}`,
    }

    return axios(config);
}

export function insertParticipant(obj: ParticipantDTO, id: string) {
    const config : AxiosRequestConfig = {
        method: "POST",
        baseURL: BASE_URL,
        url: "/participants",
        params: { prizeDrawId: id },
        data: obj,
    }

    return axios(config);
}