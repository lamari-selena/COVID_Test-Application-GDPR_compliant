import React from 'react'
import Vaccination from './Vaccination'

function VaccinationList({vaccinations, fetchVaccinations}){

    return (
        <div>
            
                
                {vaccinations ? vaccinations.map(vaccination => <Vaccination key={vaccination.vaccination_id} vaccination={vaccination} fetchVaccinations={fetchVaccinations}/>) : <h1>Vaccinations</h1>}
                
          
        </div>
    )
}

export default VaccinationList