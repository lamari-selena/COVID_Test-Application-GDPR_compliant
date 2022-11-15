import axios from "axios";
import config from "../config/config";

const API_URL = config.API_URL + `:8090`

class VaccinationService {

    
    findAll() {
        return axios
            .get(`${API_URL}/vaccinations`)
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    findById(id){
        return axios
        .get(`${API_URL}/vaccinations/${id}`)
        .then(response => {
            return response.data;
        })
        .catch(err => {
            throw err
        })
    }

    create(vaccineName, vaccineType, targetDisease, vaccinationCenter, country, numberOfDoses, vaccination_date, userId) {

        console.log(vaccineName)
        return axios
            .post(`${API_URL}/vaccinations`,{
                vaccine_name : vaccineName, 
                vaccine_type : vaccineType, 
                target_disease : targetDisease, 
                vaccination_center : vaccinationCenter, 
                country : country, 
                number_of_doses : numberOfDoses, 
                vaccination_date : vaccination_date, 
                user_id : userId})
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    delete(id) {
        return axios
            .delete(`${API_URL}/vaccinations/${id}`)
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    update(id, vaccine_name, vaccine_type, target_disease, vaccination_center, country, number_of_doses, vaccination_date, user_id){
        return axios
            .put(`${API_URL}/vaccinations/${id}` , {id, vaccine_name, vaccine_type, target_disease, vaccination_center, country, number_of_doses, vaccination_date, user_id})
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    findVaccinationByUser(id){
        return axios
        .get(`${API_URL}/vaccinations/by_user/${id}`)
        .then(response => {
            return response.data;
        })
        .catch(err => {
            throw err
        })
    }

    

}

export default new VaccinationService();