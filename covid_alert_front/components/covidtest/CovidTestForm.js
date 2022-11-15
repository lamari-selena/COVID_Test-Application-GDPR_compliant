import React, { useState } from 'react';
import covidtests from '../../pages/covidtests';
import CovidTestService from '../../services/CovidTestService';
import { format, parseISO, formatISO  } from 'date-fns'




export function CovidTestForm({ fetchCovidtests, user }) {

    const [showModal, setShowModal] = useState(false);
    const [error, setError] = useState(null)
    const [loading, setLoading] = useState(false)
    const [covidtestType, setCovidtestType] = useState("")
    const [covidtestResult, setCovidtestResult] = useState("")
    const [covidtestValidDuration, setCovidtestValidDuration] = useState("72")
    const [covidtestDate, setCovidtestDate] = useState("")
    const [userId, setUserId] = useState(user.sub)


    const handleClose = () => {
        setShowModal(false);
        setError(null);
        setLoading(false);
        setCovidtestType("");
        setCovidtestResult("")
        setCovidtestValidDuration("");
        setCovidtestDate("");
    };

    const addCovidTest = (covidtestType, covidtestResult, covidtestValidDuration, covidtestDate, userId) => {
        setError(null)
        setLoading(true)


        CovidTestService.create(covidtestType, covidtestResult, covidtestValidDuration, covidtestDate, userId).then(
            () => {
                fetchCovidtests()
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

        const covidtest_date = parseISO(covidtestDate) ? format(parseISO(covidtestDate), "yyyy-MM-dd'T'HH:mm:ss") : null;

        addCovidTest(covidtestType, covidtestResult, covidtestValidDuration, covidtestDate, userId)

    }



    return (
        <>
        <script src="../path/to/@themesberg/flowbite/dist/datepicker.bundle.js"></script>
            <button type="button" className="py-2 px-4  bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg " onClick={() => setShowModal(true)}>
                Ajouter un Test Covid
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
                                        Test Covid
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

                                    <label htmlFor="covidtestType" class="block text-xs font-semibold text-gray-600 uppercase">Type du test (PCR ou antigénique)</label>
                                    <input id="covidtestType" type="text" name="covidtestType" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" placeholder="Type du test" onChange={(e) => setCovidtestType(e.target.value)} required/>
                                    <br />
                                    <div class="flex justify-between gap-3">
                                        <span class="w-1/2">
                                            <label htmlFor="covidtestResult" class="block text-xs font-semibold text-gray-600 uppercase">Résultat</label>
                                            <input id="covidtestResult" type="text" name="covidtestResult" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" placeholder="Résultat du test" onChange={(e) => setCovidtestResult(e.target.value)} required />
                                        </span>
                                        <span class="w-1/2">
                                            <label htmlFor="covidtestValidDuration" class="block text-xs font-semibold text-gray-600 uppercase">Durée de validité</label>
                                            <input id="covidtestValidDuration" type="text" name="covidtestValidDuration" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" placeholder="Durée de validité" value={covidtestValidDuration} onChange={(e) => setCovidtestValidDuration(e.target.value)} required />
                                        </span>
                                    </div>

                                    <br />
                                    <label for="covidtestDate" class="block text-xs font-semibold text-gray-600 uppercase">Date du test</label>
                                    <input id="covidtestDate" type="date" name="covidtestDate" class="block w-full p-3 mt-2 text-gray-700 bg-gray-200 appearance-none focus:outline-none focus:bg-gray-300 focus:shadow-inner" value={covidtestDate} placeholder = "Choisir une date" onChange={(e) => setCovidtestDate(e.target.value)} required />
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