import axios, { AxiosRequestConfig } from "axios";
import { BASE_URL } from "../utils/system";

export function getLogById(id: string) {
    const config : AxiosRequestConfig = {
        method: "GET",
        baseURL: BASE_URL,
        url: `/logs/${id}`,
    }

    return axios(config);
}