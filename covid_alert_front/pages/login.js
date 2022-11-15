import React, { useState } from 'react'
import Navbar from '../components/Navbar';
import { getSession, signIn } from 'next-auth/react';
import { useRouter } from 'next/router';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function login() {

    const router = useRouter()

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [isLoading, setIsLoading] = useState(false)
    const [errorMessage, setErrorMessage] = useState('')

    const handleLogin = async () => {

        setErrorMessage('')
        setIsLoading(true)

        let res = await signIn('credentials', { redirect: false, username, password })

        if (res.ok) {
            router.push("/")
        } else {
            setErrorMessage("Echec d'authentification")
        }

        setIsLoading(false)

    }


    return (
        <div>
            <Navbar />
            <div className="flex justify-center w-full h-80v flex-col">
                <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col h-72 self-center">
                    <div className="mb-4">
                        <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="username">
                            Nom d'utilisateur
                        </label>
                        <input className="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker" id="username" name="username" type="text" placeholder="Nom d'utilisateur" value={username} onChange={e => setUsername(e.target.value)} />
                    </div>
                    <div className="mb-6">
                        <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="password">
                            Mot de passe
                        </label>
                        <input className="shadow appearance-none border border-red rounded w-full py-2 px-3 text-grey-darker mb-3" id="password" name="password" type="password" placeholder="******************" value={password} onChange={e => setPassword(e.target.value)} />
                    </div>
                    <button className="bg-yellow-400 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded flex justify-center items-center disabled:opacity-50" type="button" onClick={handleLogin}>
                        {
                            isLoading && <FontAwesomeIcon className="mr-2 animate-spin" icon={faSpinner} />
                        }
                        Connexion
                    </button>
                    {errorMessage && <p className="text-red-600 text-center">{errorMessage}</p>}
                </div>
            </div>
        </div>
    )

}

export async function getServerSideProps(context) {
    const session = await getSession(context);

    if (session) return { redirect: { destination: "/", permanent: false, } }

    return {
        props: {}
    }
}

export default login
