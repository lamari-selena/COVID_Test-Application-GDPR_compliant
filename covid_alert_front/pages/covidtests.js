import React, { useState, useCallback, useEffect } from 'react'
import Navbar from '../components/Navbar'
import CovidTestList from '../components/covidtest/CovidTestList';
import CovidTestService from '../services/CovidTestService';
import { getSession } from 'next-auth/react';
import { CovidTestForm } from '../components/covidtest/CovidTestForm';

function covidtests({ user }) {

    const [covidtests, setCovidtests] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

    const fetchCovidtests = useCallback(() => {
        setLoading(true)
        CovidTestService.findCovidTestByUser(user.sub).then(
            response => {
                setCovidtests(response)
                setLoading(false)
            },
            error => {
                setError(error.response)
                setLoading(false)
            }
        )
    })

    useEffect(fetchCovidtests, []);



    return (
        <div>
            <Navbar />
            <br />
            <br/>
            <div class="container flex flex-col mx-auto w-full items-center justify-center">
                <CovidTestForm fetchCovidtests={fetchCovidtests} user={user}/>
                <br/>
                <div class="px-4 py-5 sm:px-6 w-full border dark:bg-gray-800 bg-white shadow mb-2 rounded-md">
                    <h3 class="text-lg leading-6 font-medium text-gray-900 dark:text-white">
                        Liste des tests de {user.name}
                    </h3>
                </div>

                {loading ?
                    null :
                    <div>
                        <br />
                        <CovidTestList covidtests={covidtests} fetchCovidtests={fetchCovidtests} />
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

export default covidtests