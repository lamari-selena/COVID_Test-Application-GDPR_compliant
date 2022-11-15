import { faSpinner } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { getSession } from 'next-auth/react'
import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import UserService from '../services/user'

function register() {

    const [username, setUsername] = useState('')
    const [mail, setMail] = useState('')
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [password, setPassword] = useState('')
    const [password2, setPassword2] = useState('')
    const [errorMessage, setErrorMessage] = useState('')
    const [successMessage, setSuccessMessage] = useState('')
    const [isLoading, setIsLoading] = useState(false)

    const handleInput = () => {
        // TODO : Check input
        setErrorMessage("")
        setSuccessMessage("")
        setIsLoading(true)
        UserService.register(username, firstName, lastName, mail, password)
            .then(res => {
                console.log(res)
                setSuccessMessage("Compte crée, connectez-vous")
                setUsername('')
                setMail('')
                setFirstName('')
                setLastName('')
                setPassword('')
                setPassword2('')
                setIsLoading(false)
            })
            .catch(err => {
                console.log(err)
                setErrorMessage("Une erreur est survenue")
                setIsLoading(false)
            })
    }

    return (
        <div>
            <Navbar />
            <div className="flex justify-center w-full h-80v flex-col">
                <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col self-center w-96">
                    <div className="mb-4">
                        <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="username">
                            Pseudo
                        </label>
                        <input className="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker" id="username" type="text" placeholder="Pseudo" value={username} onChange={e => setUsername(e.target.value)} />
                    </div>
                    {/* Section identité utilisateur */}
                    <div className="mb-4">
                        <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="username">
                            Mail
                        </label>
                        <input className="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker" id="mail" type="text" placeholder="example@gmail.com" value={mail} onChange={e => setMail(e.target.value)} />
                    </div>
                    <div className="mb-6 flex justify-between">
                        <div className="flex-col w-5/12">
                            <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="password">
                                Prénom
                            </label>
                            <input className="shadow appearance-none border rounded w-full py-2 px-2 text-grey-darker" id="firstName" type="text" placeholder="Prénom" value={firstName} onChange={e => setFirstName(e.target.value)} />
                        </div>
                        <div className="flex-col w-5/12">
                            <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="password">
                                Nom
                            </label>
                            <input className="shadow appearance-none border rounded w-full py-2 px-2 text-grey-darker" id="lastName" type="text" placeholder="Nom" value={lastName} onChange={e => setLastName(e.target.value)} />
                        </div>
                    </div>
                    {/* Section mot de passe */}
                    <div className="mb-6 flex justify-between">
                        <div className="flex-col w-5/12">
                            <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="password">
                                Mot de passe
                            </label>
                            <input className="shadow appearance-none border border-red rounded w-full py-2 px-3 text-grey-darker mb-3" id="password" type="password" placeholder="******************" value={password} onChange={e => setPassword(e.target.value)} />
                        </div>
                        <div className="flex-col w-5/12">
                            <label className="block text-grey-darker text-sm font-bold mb-2" htmlFor="password">
                                Confirmez
                            </label>
                            <input className="shadow appearance-none border border-red rounded w-full py-2 px-3 text-grey-darker mb-3" id="password" type="password" placeholder="******************" value={password2} onChange={e => setPassword2(e.target.value)} />
                        </div>
                    </div>
                    <button disabled={isLoading} className="bg-yellow-400 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded flex justify-center items-center disabled:opacity-50" type="button" onClick={handleInput}>
                        {
                            isLoading && <FontAwesomeIcon className="mr-2 animate-spin" icon={faSpinner} />
                        }
                        Créer compte
                    </button>
                    {errorMessage && <p className="text-red-600 text-center">{errorMessage}</p>}
                    {successMessage && <p className="text-green-600 text-center">{successMessage}</p>}
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

export default register
