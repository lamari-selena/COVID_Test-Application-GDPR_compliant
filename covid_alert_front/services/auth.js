import axios from "axios"
import config from "../config/config"

const API_URL = config.API_URL + ':28081'
const REALM_NAME = 'microservices-realm'
const CLIENT_ID = 'web-front'
const GRANT_TYPE = 'password'


class AuthService {
    login(username, password) {
        const params = new URLSearchParams()
        params.append('username', username)
        params.append('password', password)
        params.append('client_id', CLIENT_ID)
        params.append('grant_type', GRANT_TYPE)

        const config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }
        return axios
            .post(`${API_URL}/auth/realms/${REALM_NAME}/protocol/openid-connect/token`, params, config)
            .then(res => {
                return res;
            }).catch(err => {
                switch (err.response.status) {
                    case 401:
                        throw new Error("Erreur d'authentification")
                    case 500:
                        throw new Error("Une erreur est survenue")
                }
            })
    }
}

export default new AuthService();