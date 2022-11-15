import { useSession, signIn, signOut } from 'next-auth/react';
import Head from 'next/head'
import Navbar from '../components/Navbar';
import News from '../components/News';



export default function Home() {

  const { data: session } = useSession()

  const handleClick = () => {
    console.log(session.user)
  }

  return (
    <div>
      <Head>
        <title>Covid Alert</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Navbar />

      <div className="py-10">
        {!session && (
          <div className="py-32 text-center">
            Non connecté <br />
            </div>
        )}

        {
          session && (
            <>
              <News></News>
            </>
          )
        }
      </div>

    </div>
  )
}
