import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import config from '../config/config'



function news() {

    const [news, setNews] = useState([])
    const [errorMessage, setErrorMessage] = useState("")
    const [isLoading, setIsLoading] = useState(false)



    useEffect(() => {

        setErrorMessage("")
        setIsLoading(true)

        axios.get(config.API_URL + ":8090/news")
            .then(res => {
                setNews(res.data)
                setIsLoading(false)
            })
            .catch(e => {
                console.log(e)
                setErrorMessage("Erreur lors de la récupération des news")
                setIsLoading(false)
            })
    }, [])

    return (
        <div>
            <h4 className="text-3xl text-center font-bold leading-normal mt-0 mb-2">
                Actualités
            </h4>
            {isLoading ? <li class="p-3">Chargement ...</li> : news.map((n) => {
                return (
                <div>
                    <div class="w-2/3 mx-auto bg-white rounded-lg shadow-lg md:flex p-3">
                    <img src={n.image} alt="Laptop on Desk" class="md:w-1/3 rounded-t-lg md:rounded-l-lg md:rounded-t-none" />
                        <div class="p-6">
                            <h2 class="text-2xl font-bold mb-2 text-gray-800">{n.title}</h2>
                            <p class="text-gray-700">{n.content}</p>
                        </div>
                    </div>
                </div>
            
                )
            })}            
        </div>

        
    )
}

export default news
