import React, { useState, useCallback, useEffect } from 'react'
import Navbar from '../components/Navbar'
import VaccinationList from '../components/vaccination/VaccinationList';
import VaccinationService from '../services/VaccinationService';
import { getSession } from 'next-auth/react';
import { VaccinationForm } from '../components/vaccination/VaccinationForm';

function vaccinations({ user }) {

    const [vaccinations, setVaccinations] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

    const fetchVaccinations = useCallback(() => {
        setLoading(true)
        VaccinationService.findVaccinationByUser(user.sub).then(
            response => {
                setVaccinations(response)
                setLoading(false)
            },
            error => {
                setError(error.response)
                setLoading(false)
            }
        )
    })

    useEffect(fetchVaccinations, []);



    return (
        <div>
            <Navbar />
            <br />
            <br/>
            <div class="container flex flex-col mx-auto w-full items-center justify-center">
                <VaccinationForm fetchVaccinations={fetchVaccinations} user={user}/>
                <br/>
                <div class="px-4 py-5 sm:px-6 w-full border dark:bg-gray-800 bg-white shadow mb-2 rounded-md">
                    <h3 class="text-lg leading-6 font-medium text-gray-900 dark:text-white">
                        Liste des vaccins de {user.name}
                    </h3>
                </div>

                {loading ?
                    null :
                    <div>
                        <br />
                        <VaccinationList vaccinations={vaccinations} fetchVaccinations={fetchVaccinations} />
                    </div>

                }
            </div>
        </div>
    )
}

export async function getServerSideProps(context) {
    const session = await getSession(context);

    if (!session) return { redirect: { destination: "/login", permanent: false, } }

    return {
        props: { user: session.user }
    }
}

export default vaccinations