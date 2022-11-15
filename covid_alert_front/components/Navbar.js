import { useRouter } from 'next/router'
import { signOut, useSession } from 'next-auth/react';
import SockJsClient from 'react-stomp';
import { useState, useContext } from 'react';
import AppContext from '../AppContext';
import config from '../config/config';

const SOCKET_URL = config.API_URL + ':7001/alerts';

function Navbar() {
  let state;
  state = {
      token: '',
      user_id: '1',
      openPersonalDataModal: false,
      openPersonalDataEditModal: false,
      id: '',
      name: '',
      desc: '',
      content: '',
      discount: '',
      file: '',
      fileName: '',
      page: 1,
      search: '',
      personalDatas: [],
      pages: 0,
      loading: false,
      apiKey : ''
    };
  const openGDPRPage = () => {
      console.log(state)
      fetch( /*process.env.REACT_APP_GDPRMS_API_ADDRESS+*/ 'http://localhost:3002/dataSubject/getByIdRef/' + state.user_id, {
          method: 'GET',
          headers: {'api-key': "adHmz2bowyu4hTUh6eKkJigVSy8InqZClGmin"},//   temp fix : normally it should be :    headers: {'api-key': this.state.apiKey},
      }).then(async (response) => {
          const res = await response.json()
          console.log(res.data)
          window.open(/*process.env.REACT_APP_GDPRMS_CLIENT_ADDRESS+*/'http://localhost:3002/user/' + res.data.acr + "?api-key=adHmz2bowyu4hTUh6eKkJigVSy8InqZClGmin");//should be  + this.state.apiKey instead (quickfix)
      }).catch((err) => {
          console.log(err);
      });
  }
  const router = useRouter()

  const value = useContext(AppContext);
  let setNotifications = value.setNotifications
  let { notifications } = value.state;

  // TODO : replace by real session by uncommenting
  const { data: session } = useSession()
  // const session = true

  const handleSignOut = async () => {
    await signOut({ redirect: false })
    router.push('/')
  }

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg) => {
    let updatedNotifications = [...notifications]
    updatedNotifications.push(msg)
    setNotifications(updatedNotifications)
  }

  const [clientRef, setClientRef] = useState(null);

  return (

    // <!-- navbar goes here -->
    <nav className="bg-gray-100">
      <SockJsClient
        url={SOCKET_URL}
        topics={['/users/queue/messages']}
        onConnect={onConnected}
        onDisconnect={console.log("Disconnected!")}
        onMessage={msg => onMessageReceived(msg)}
        debug={true}
        ref={(client) => setClientRef(client)}
        headers={{ userid: session ? session.user.sub : 101 }}
      />

      <div className="max-w-6xl mx-auto px-4">
        <div className="flex justify-between">
          <div className="flex space-x-4">
            {/* <!-- logo --> */}
            <div>
              <button className="flex items-center py-5 px-2 text-gray-700 hover:text-gray-900">
                <span onClick={() => router.push("/")} className="font-bold">Covid Alert</span>
              </button>
            </div>

            {/* <!-- primary nav --> */}
            {session && <div className="hidden md:flex items-center space-x-1">
              <button onClick={() => router.push("/locations")} className="py-5 px-3 text-gray-700 hover:text-gray-900">Me localiser</button>
              <button onClick={() => router.push("/notifications")} className="py-5 px-3 text-gray-700 hover:text-gray-900">
                Notifications
                {notifications.length != 0 && <span className="ml-1 inline-flex items-center justify-center px-2 py-1 mr-2 text-xs font-bold leading-none text-red-100 bg-red-600 rounded-full">{notifications.length}</span>}
              </button>



              <button onClick={() => router.push("/vaccinations")} className="py-5 px-3 text-gray-700 hover:text-gray-900">Mes vaccinations</button>
              <button onClick={() => router.push("/covidtests")} className="py-5 px-3 text-gray-700 hover:text-gray-900">Mes tests</button>
              <button onClick={openGDPRPage} className="py-5 px-3 text-gray-700 hover:text-gray-900">GDPR rights</button>
            </div>}
          </div>

          {/* <!-- secondary nav --> */}
          <div className="hidden md:flex items-center space-x-1">
            {!session && <button onClick={() => router.push("/login")} className="py-5 px-3">Connexion</button>}
            {!session && <button onClick={() => router.push("/register")} className="py-2 px-3 bg-yellow-400 hover:bg-yellow-300 text-yellow-900 hover:text-yellow-800 rounded transition duration-300">S'inscrire</button>}
            {session && <button onClick={handleSignOut} className="py-5 px-3">Déconnexion</button>}
          </div>

          {/* <!-- mobile button goes here --> */}
          <div className="md:hidden flex items-center">
            <button className="mobile-menu-button">
              <svg className="w-6 h-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16" />
              </svg>
            </button>
          </div>

        </div>
      </div>
    </nav>
  )
}

export default Navbar
