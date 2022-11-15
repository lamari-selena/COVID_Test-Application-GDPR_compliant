import React from 'react';
import GoogleMapReact from 'google-map-react';
import {SiGooglemaps} from 'react-icons/si'

const MapContainer = ({pos},{setPos}) =>  {
  
    const [center,setCenter] = React.useState( {
      lat: pos.lat,
      lng: pos.long
    });

    const onMapClick = React.useCallback((e) => {
      
      setCenter({
        lat: e.lat,
      lng: e.lng
      });
      
      setPos({
        lat: e.lat,
      lng: e.lng
      })
    }, []);

    


    return (
      // Important! Always set the container height explicitly
      <div class="self-auto" style={{ height: '50vh', width: '100%' }}>
        <GoogleMapReact
          bootstrapURLKeys={{ key:'AIzaSyDtTsGRxOEwFO-Ac3UcQcmheccCXpCfCaA'}}
          defaultCenter={{lat:48.856614,lng:2.3522219}}
          center = {center}
          defaultZoom={11}
          onClick={(e) => {onMapClick(e)}}
          
        >
          <SiGooglemaps
            lat={center.lat}
            lng={center.lng}
            size={30}
          />
         
        </GoogleMapReact>
        <button className="bg-yellow-400 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded flex justify-center items-center" type="button" onClick={getLocation} >
                        
                        Me localiser
                    </button>
      </div>
    );
  }


export default MapContainer;