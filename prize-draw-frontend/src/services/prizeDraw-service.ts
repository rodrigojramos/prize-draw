import axios, { AxiosRequestConfig } from "axios";
import { BASE_URL } from "../utils/system";

export function getOngoingPrizeDrawsByUser(id: string) {
    const config : AxiosRequestConfig = {
        method: "GET",
        baseURL: BASE_URL,
        url: `/draws/${id}/ongoing`,
    }

    return axios(config);
}

export function getFinishedPrizeDrawsByUser(id: string) {
    const config : AxiosRequestConfig = {
        method: "GET",
        baseURL: BASE_URL,
        url: `/draws/${id}/finished`,
    }

    return axios(config);
}