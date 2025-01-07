import axios, { AxiosRequestConfig } from "axios";
import { BASE_URL } from "../utils/system";

export function getParticipantById(id: string) {
    const config : AxiosRequestConfig = {
        method: "GET",
        baseURL: BASE_URL,
        url: `/participants/${id}`,
    }

    return axios(config);
}