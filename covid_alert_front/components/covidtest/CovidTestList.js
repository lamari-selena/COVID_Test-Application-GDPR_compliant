import React from 'react'
import CovidTest from './CovidTest'

function CovidTestList({covidtests, fetchCovidtests}){

    return (
        <div>
            
                
                {covidtests ? covidtests.map(covidtest => <CovidTest key={covidtest.covidtest_id} covidtest={covidtest} fetchCovidtests={fetchCovidtests}/>) : null}
                
          
        </div>
    )
}

export default CovidTestList