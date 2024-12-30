import axios, { AxiosRequestConfig } from "axios";
import { BASE_URL } from "../utils/system";

export function findMe(token: string) {
    const config : AxiosRequestConfig = {
        method: "GET",
        baseURL: BASE_URL,
        url: "/users/me",
        headers: {
            Authorization: token,
        },
    }

    return axios(config);
}