import axios, { AxiosRequestConfig } from "axios";
import { BASE_URL } from "../utils/system";

export async function login(email: string): Promise<string> {

    const config : AxiosRequestConfig = {
        method: "POST",
        baseURL: BASE_URL,
        url: "/auth/login",
        data: {email},
    }

    try {
        const response = await axios(config);
        const token = response.data;
        localStorage.setItem("authToken", token);
        return token;
    } catch (error) {
        console.error("Erro ao fazer login:", error);
        throw error;
    }
}