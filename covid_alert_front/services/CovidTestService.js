import axios from "axios";
import config from "../config/config";

const API_URL = config.API_URL + `:8090`

class CovidTestService {

    
    findAll() {
        return axios
            .get(`${API_URL}/covidtests`)
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    findById(id){
        return axios
        .get(`${API_URL}/covidtests/${id}`)
        .then(response => {
            return response.data;
        })
        .catch(err => {
            throw err
        })
    }

    create(covidtestType, covidtestResult, covidtestValidDuration, covidtestDate, userId) {

        return axios
            .post(`${API_URL}/covidtests`,{
                covidtest_type : covidtestType, 
                covidtest_result : covidtestResult, 
                covidtest_valid_duration : covidtestValidDuration, 
                covidtest_date : covidtestDate, 
                user_id : userId})
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    delete(id) {
        const covidtest_id = parseInt(id)
        console.log(covidtest_id)
        console.log(typeof(covidtest_id))
        return axios
            .delete(`${API_URL}/covidtests/${covidtest_id}`)
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    update(){
        return axios
            .put(`${API_URL}/covidtests/${id}` , {})
            .then(response => {
                return response.data;
            })
            .catch(err => {
                throw err
            })
    }

    findCovidTestByUser(id){
        return axios
        .get(`${API_URL}/covidtests/by_user/${id}`)
        .then(response => {
            return response.data;
        })
        .catch(err => {
            throw err
        })
    }

    

}

export default new CovidTestService();