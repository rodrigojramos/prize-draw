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

export async function logout(): Promise<void> {

    const token = localStorage.getItem("authToken");

    if (!token) {
        throw new Error("Token não encontrado no localStorage.");
    }

    const config: AxiosRequestConfig = {
        method: "POST",
        baseURL: BASE_URL,
        url: "/auth/logout",
        data: { token },
    };

    try {
        await axios(config);
        localStorage.removeItem("authToken");
        console.log("Logout realizado com sucesso.");
    } catch (error) {
        console.error("Erro ao fazer logout:", error);
        throw error;
    }
}