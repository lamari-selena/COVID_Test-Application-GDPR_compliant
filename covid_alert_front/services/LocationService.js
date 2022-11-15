import axios from "axios";
import { useSession } from 'next-auth/react';
import config from "../config/config";



const API_URL = config.API_URL + ':8090'

class LocationService {

    
    findAll(id) {
        return axios
            .get(`${API_URL}/locations/by_user/${id}`)
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    create(latitude, longitude,location_date,user_id) {
        return axios
            .post(`${API_URL}/locations/`,{latitude, longitude,location_date,user_id})
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }



    

}

export default new LocationService();