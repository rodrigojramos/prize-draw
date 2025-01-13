import axios, { AxiosRequestConfig } from "axios";
import { BASE_URL } from "../utils/system";
import { PrizeDrawDTO } from "../models/prizeDraw";

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

export function getPrizeDrawById(id: string) {
    const config : AxiosRequestConfig = {
        method: "GET",
        baseURL: BASE_URL,
        url: `/draws/${id}`,
    }

    return axios(config);
}

export function deletePrizeDrawById(id: string) {
    const config : AxiosRequestConfig = {
        method: "DELETE",
        baseURL: BASE_URL,
        url: `/draws/${id}`,
    }

    return axios(config);
}

export function updatePrizeDraw(obj: PrizeDrawDTO) {
    const config : AxiosRequestConfig = {
        method: "PUT",
        baseURL: BASE_URL,
        url: `/draws/${obj.id}`,
        data: obj,
    }

    return axios(config);
}

export function insertPrizeDraw(obj: PrizeDrawDTO, token: string) {
    const config : AxiosRequestConfig = {
        method: "POST",
        baseURL: BASE_URL,
        url: "/draws",
        data: obj,
        headers: {
            Authorization: token,
        },
    }

    return axios(config);
}