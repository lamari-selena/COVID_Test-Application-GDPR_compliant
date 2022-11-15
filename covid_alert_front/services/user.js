import axios from "axios"
import config from "../config/config"

const API_URL = config.API_URL + ':8090'


class UserService {
    register(username, firstName, lastName, email, password) {
        return axios
            .post(`${API_URL}/users`, {
                username,
                first_name: firstName,
                last_name: lastName,
                email: email,
                password
            })
            .then(response => {
                return response;
            });
    }
}

export default new UserService();