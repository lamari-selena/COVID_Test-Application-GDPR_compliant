import React, {useState} from 'react'
import VaccinationService from '../../services/VaccinationService';


function Vaccination({ vaccination, fetchVaccinations }) {

    const [showModal, setShowModal] = useState(false);
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(null)

    const handleClickOpen = () => {
        setShowModal(true);
    };

    const handleClose = () => {
        setShowModal(false);
    };

    const handleDelete = () => {
        setLoading(true)
        VaccinationService.delete(vaccination.vaccination_id).then(
            () => {
                fetchVaccinations()
                handleClose()
            },
            error => {
                setError(error.response)
            }
        )
        setLoading(false)

    }

    return (
        <>
        <br/>
        <div class="shadow-lg px-4 py-6 w-full bg-white dark:bg-gray-800 relative">
            <p class="text-sm w-max text-gray-700 dark:text-white font-semibold border-b border-gray-200">
                Date de vaccination : {vaccination.vaccination_date}
            </p>
            <div class="flex items-end space-x-2 my-6">
                <p class="text-5xl text-black dark:text-white font-bold">
                    {vaccination.vaccine_name}
                </p>
                <span class="text-green-500 text-xl font-bold flex items-center">

                    dose {vaccination.number_of_doses} / 2
                </span>
            </div>
            <div class="dark:text-white">
                <div class="flex items-center pb-2 mb-2 text-sm space-x-12 md:space-x-24 justify-between border-b border-gray-200">
                    <p>
                        Type :
                    </p>
                    <div class="flex items-end text-xs">
                        {vaccination.vaccine_type}
                    </div>
                </div>
                <div class="flex items-center mb-2 pb-2 text-sm space-x-12 md:space-x-24 justify-between border-b border-gray-200">
                    <p>
                        Centre de vaccination :
                    </p>
                    <div class="flex items-end text-xs">
                        {vaccination.vaccination_center}
                    </div>
                </div>
                <div class="flex items-center text-sm space-x-12 md:space-x-24 justify-between">
                    <p>
                        maladie :
                    </p>
                    <div class="flex items-end text-xs">
                        {vaccination.target_disease}
                    </div>
                </div>
                <br/>
                <div class="flex items-center text-sm space-x-12 md:space-x-24 justify-between">
                    <p>
                    
                    </p>
                    <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-full" onClick={handleClickOpen}>
                        supprimer
                    </button>
                </div>
            </div>
        </div>
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
                                        Attention
                                    </h3>
                                    <button
                                        className="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none"
                                        onClick={() => setShowModal(false)}
                                    >
                                        <span className="bg-transparent text-black opacity-5 h-6 w-6 text-2xl block outline-none focus:outline-none">
                                            ??
                                        </span>
                                    </button>
                                </div>
                                {/*body*/}
                                <div className="relative p-6 flex-auto">
                                    <p>
                                    ??tes-vous sur de vouloir supprimer ce vaccin ?
                                    </p>
                                </div>
                                {/*footer*/}
                                <div className="flex items-center justify-end p-6 border-t border-solid border-blueGray-200 rounded-b">
                                    <button
                                        className="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={handleClose}
                                    >
                                        fermer
                                    </button>
                                    <button
                                        className="text-green-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={handleDelete}
                                    >
                                        supprimer
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

export default Vaccination