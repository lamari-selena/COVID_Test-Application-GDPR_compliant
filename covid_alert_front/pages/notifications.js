import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import { useContext } from "react";
import AppContext from "../AppContext";

function notifications() {

    const value = useContext(AppContext);
    let { notifications } = value.state;


    return (
        <div>
            <Navbar />
            <h4 className="text-3xl text-center font-bold leading-normal mt-0 mb-2">
                Notifications
            </h4>
            <div className="flex flex-col items-center">
                <ul className="w-1/2">
                    {notifications.map((notification, index) => {
                        return (
                            <div key={index} className="bg-white rounded-lg border-gray-300 border p-3 shadow-lg">
                                <div className="flex flex-row">
                                    <div className="px-2 fill-current text-red-600">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="feather feather-info w-5 h-5 mx-2">
                                            <circle cx="12" cy="12" r="10"></circle>
                                            <line x1="12" y1="16" x2="12" y2="12"></line>
                                            <line x1="12" y1="8" x2="12.01" y2="8"></line>
                                        </svg>
                                    </div>
                                    <div className="ml-2 mr-6">
                                        <span className="font-semibold">Attention !</span>
                                        <span className="block text-gray-500">{notification.message}</span>
                                        <span className="block text-gray-500">Date de contact: {new Date(Date.parse(notification.location_date)).toLocaleDateString('en-GB')}</span>
                                    </div>
                                </div>
                            </div>
                        )
                    }
                    )}
                </ul>
            </div>
        </div>
    )
}

export default notifications

