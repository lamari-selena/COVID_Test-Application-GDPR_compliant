import React, { useState,useEffect,useCallback } from 'react';
import LocationService from "../services/LocationService";
import MapContainer from './MapContainer';
import GoogleMapReact from 'google-map-react';
import {SiGooglemaps} from 'react-icons/si'
import { useSession } from 'next-auth/react';






const Localisation = () => {
  const [pos, setPos] = useState({lat: 48.856614, long: 2.3522219})
  const [locations, setLocations] = useState([])
  const [isLoading, setIsLoading] = useState(true)
  const [user_id, setUserId] = useState('')

  const [navigator, setNavigator] = useState({});

  useEffect(() => {
    setNavigator(window.navigator);
    setUserId(session.user.sub);
    
    LocationService.findAll(session.user.sub).then(data => {
      setLocations(data)
      console.log(data)
      setIsLoading(false)
      getLocation()
      
  })
  .catch(err => {
      console.log(err)
  })
    
  }, []);



 


  const  getLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(getCoords)
      

    } else {
      console.log('GeoLocation not enabled');
    }
  }
          
  const getCoords = (pos) => {
    console.log(pos)
    setPos({
      lat: pos.coords.latitude,
      long: pos.coords.longitude
    })
  
  }
  
  const saveCoords = () => {
    LocationService.create(pos.lat,pos.long,Date.now(),session.user.sub).then(data => {
      console.log(data)
      
  })
  .catch(err => {
      console.log(err)
  })

  }
  

const onMapClick = React.useCallback((e) => {
  
  
  setPos({
    lat: e.lat,
    long: e.lng
  })
}, []);

const { data: session } = useSession()


 





   

    return (
     
      <div class="text-center">

        <div class="self-auto" style={{ height: '50vh', width: '100%' }}>
        <GoogleMapReact
          bootstrapURLKeys={{ key:'AIzaSyDtTsGRxOEwFO-Ac3UcQcmheccCXpCfCaA'}}
          defaultCenter={{lat:48.856614,lng:2.3522219}}
          center = {{lat:pos.lat,lng:pos.long}}
          defaultZoom={11}
          onClick={(e) => {onMapClick(e)}}
          
        >
          <SiGooglemaps
            lat={pos.lat}
            lng={pos.long}
            size={30}
            color= 'red'
          />
         
        </GoogleMapReact>

      </div>






      <div class="flex max-w-md mx-auto overflow-hidden bg-white rounded-lg shadow dark:bg-gray-800">
        <div class="w-2/3 p-4 md:p-4">
            <h1 class="text-2xl font-bold text-gray-800 dark:text-white" >Localisation</h1>
            <p class="mt-2 text-sm text-gray-600 dark:text-gray-400">Latitude : {pos.lat}</p>
            <p class="mt-2 text-sm text-gray-600 dark:text-gray-400">Longitude : {pos.long}</p>
            <div class="flex justify-between mt-3 item-center">
            <button class="px-2 py-1 text-xs font-bold text-white uppercase transition-colors duration-200 transform bg-gray-800 rounded dark:bg-gray-700 hover:bg-gray-700 dark:hover:bg-gray-600 focus:outline-none focus:bg-gray-700 dark:focus:bg-gray-600" onClick={getLocation}>Me Localiser</button>
            <button class="px-2 py-1 text-xs font-bold text-white uppercase transition-colors duration-200 transform bg-gray-800 rounded dark:bg-gray-700 hover:bg-gray-700 dark:hover:bg-gray-600 focus:outline-none focus:bg-gray-700 dark:focus:bg-gray-600" onClick={saveCoords}>Enregistrer</button>
            </div>
        </div>
    </div>

            

<div class="w-2/3 mx-auto">
  <div class="bg-white shadow-md rounded my-6">
    <table class="text-left w-full border-collapse"> 
      <thead>
        <tr>
          <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">Date</th>
          <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">Localisation</th>
        </tr>
      </thead>
      { isLoading ? <li class="p-3">Loading ...</li> : locations.map((location)=> {
                return (<tbody>
                  <tr class="hover:bg-grey-lighter">
                    <td class="py-4 px-6 border-b border-grey-light">{new Date(Date.parse(location.location_date)).toLocaleDateString('en-GB')}</td>
                    <td class="py-4 px-6 border-b border-grey-light">
                      <a  class="text-grey-lighter font-bold py-1 px-3 rounded text-xs bg-green hover:bg-green-dark">{location.longitude.toFixed(3)}</a>
                      <a  class="text-grey-lighter font-bold py-1 px-3 rounded text-xs bg-blue hover:bg-blue-dark">{location.latitude.toFixed(3)}</a>
                    </td>
                  </tr>   
                </tbody>)
              })}
    </table>
  </div>
</div>


            

      </div>
    )
}

export default Localisation
