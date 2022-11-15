import React, { useState } from 'react';
import vaccinations from '../../pages/vaccinations';
import VaccinationService from '../../services/VaccinationService';
import { format, parseISO, formatISO  } from 'date-fns'
//import DatePicker from "react-datepicker";
//import "react-datepicker/dist/react-datepicker.css";
//import Datepicker from '@themesberg/tailwind-datepicker/Datepicker';
//import '@themesberg/flowbite';



export function VaccinationForm({ fetchVaccinations, user }) {

    const [showModal, setShowModal] = useState(false);
    const [error, setError] = useState(null)
    const [loading, setLoading] = useState(false)
    const [vaccineName, setVaccineName] = useState("Vaccin Covid-19")
    const [vaccineType, setVaccineType] = useState("")
    const [targetDisease, setTargetDisease] = useState("Covid-19")
    const [vaccinationCenter, setVaccinationCenter] = useState("")
    const [country, setCountry] = useState("")
    const [numberOfDoses, setNumberOfDoses] = useState("")
    const [vaccinationDate, setVaccinationDate] = useState("")
    const [userId, setUserId] = useState(user.sub)


    const handleClose = () => {
        setShowModal(false);
        setError(null);
        setLoading(false);
        setVaccineName("Vaccin Covid-19");
        setVaccineType("")
        setTargetDisease("Covid-19");
        setVaccinationCenter("");
        setCountry("");
        setNumberOfDoses("");
        setVaccinationDate("");
    };

    const addVaccination = (vaccineName, vaccineType, targetDisease, vaccinationCenter, country, numberOfDoses, vaccination_date, userId) => {
        setError(null)
        setLoading(true)

        console.log(vaccineName)
        console.log(vaccination_date)
        console.log(vaccineType)
        console.log(targetDisease)
        console.log(vaccinationCenter)


        VaccinationService.create(vaccineName, vaccineType, targetDisease, vaccinationCenter, country, numberOfDoses, vaccination_date, userId).then(
            () => {
                fetchVaccinations()
                handleClose()
            },
            error => {
                console.log(error)
                setError(error.response.data.message)
            }
        )
    }


    const handleSubmit = (e) => {
        e.preventDefault();
        setError(null)
        setLoading(true)

        const vaccination_date = parseISO(vaccinationDate) ? format(parseISO(vaccinationDate), "yyyy-MM-dd'T'HH:mm:ss") : null;
        //console.log(parseISO(vaccinationDate))
        // const vaccination = {
        //     vaccineName: vaccineName,
        //     vaccinationDate: parseISO(vaccinationDate) ? formatISO(parseISO(vaccinationDate)) : null,
        //     vaccineType: vaccineType,
        //     targetDisease: targetDisease,
        //     vaccinationCenter: vaccinationCenter,
        //     country: country,
        //     numberOfDoses: numberOfDoses,
        //     userId: userId
        // }

        addVaccination(vaccineName, vaccineType, targetDisease, vaccinationCenter, country, numberOfDoses, vaccination_date, userId)

    }



    return (
        <>
        <script src="../path/to/@themesberg/flowbite/dist/datepicker.bundle.js"></script>
            <button type="button" className="py-2 px-4  bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg " onClick={() => setShowModal(true)}>
                Ajouter un vaccin
            </button>
            {showModal ? (
                <>
                    <div
                        className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none"
                    >
                        <div className="relative w-auto my-6 mx-auto max-w-3xl">
                            {/*content*/}
                            <div className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                                {/*header*/}
                                <div className="flex items-start justify-between p-5 border-b border-solid border-blueGray-200 rounded-t">
                                    <h3 className="text-3xl font-semibold">
                                        Vaccination
                                    </h3>
                                    <button
                                        className="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none"
                                        onClick={() => setShowModal(false)}
                                    >
                                        <span className="bg-transparent text-black opacity-5 h-6 w-6 text-2xl block outline-none focus:outline-none">
                                            ×
                                        </span>
                                    </button>
                                </div>
                                {/*body*/}
                                <div className="relative p-6 flex-auto">

                                    <label htmlFor="vaccineName" class="block text-xs font-semibold text-gray-600 uppercase">Nom du vaccin</label>
                                    <input id="vaccineName" type="text" name="vaccineName" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" value={vaccineName} disabled />
                                    <br />
                                    <div class="flex justify-between gap-3">
                                        <span class="w-1/2">
                                            <label htmlFor="vaccineType" class="block text-xs font-semibold text-gray-600 uppercase">Type du vaccin</label>
                                            <input id="vaccineType" type="text" name="vaccineType" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" placeholder="type du vaccin" onChange={(e) => setVaccineType(e.target.value)} required />
                                        </span>
                                        <span class="w-1/2">
                                            <label htmlFor="targetDisease" class="block text-xs font-semibold text-gray-600 uppercase">Maladie visée</label>
                                            <input id="targetDisease" type="text" name="targetDisease" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" value={targetDisease} disabled />
                                        </span>
                                    </div>
                                    <br />
                                    <div class="flex justify-between gap-3">
                                        <span class="w-1/2">
                                            <label htmlFor="vaccinationCenter" class="block text-xs font-semibold text-gray-600 uppercase">Centre de vaccination</label>
                                            <input id="vaccinationCenter" type="text" name="vaccinationCenter" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" placeholder="centre de vaccination" onChange={(e) => setVaccinationCenter(e.target.value)} required />
                                        </span>
                                        <br />
                                        <span class="w-1/2">
                                            <label htmlFor="country" class="block text-xs font-semibold text-gray-600 uppercase">Pays</label>
                                            <input id="country" type="text" name="country" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" placeholder="pays" onChange={(e) => setCountry(e.target.value)} required />
                                        </span>
                                    </div>

                                    <br />
                                    <label for="numberOfDoses" class="block text-xs font-semibold text-gray-600 uppercase">Numéro de la dose (1 ou 2)</label>
                                    <input id="numberOfDoses" type="text" name="numberOfDoses" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" value={numberOfDoses} onChange={(e) => setNumberOfDoses(e.target.value)} required />
                                    <br />
                                    <label for="vaccinationDate" class="block text-xs font-semibold text-gray-600 uppercase">Date de vaccination</label>
                                    <input id="vaccinationDate" type="date" name="vaccinationDate" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" value={vaccinationDate} onChange={(e) => setVaccinationDate(e.target.value)} required />
                                    <br />

                                </div>
                                {/*footer*/}
                                <div className="flex items-center justify-end p-6 border-t border-solid border-blueGray-200 rounded-b">
                                    <button
                                        className="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={handleClose}
                                    >
                                        Close
                                    </button>
                                    <button
                                        className="text-green-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={handleSubmit}
                                    >
                                        Add
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
                </>
            ) : null}
        </>
    )
}