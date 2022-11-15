import 'tailwindcss/tailwind.css'
import { SessionProvider } from "next-auth/react"
import AppContext from "../AppContext";
import { useState } from 'react';

function MyApp({ Component, pageProps: { session, ...pageProps } }) {

  // const [notifications, setNotifications] = useState([{"content": "Notification 1"}, {"content": "Notification 2"}]);
  const [notifications, setNotifications] = useState([]);

  return (
    <SessionProvider session={session}>
      <AppContext.Provider
        value={{
          state: {
            notifications: notifications,
          },
          setNotifications: setNotifications,
        }}
      >
        <Component {...pageProps} />
      </AppContext.Provider>
    </SessionProvider>
  )
}

export default MyApp
