import React from 'react'
import Navbar from '../components/Navbar'
import Localisation from '../components/Localisation';
import { getSession } from 'next-auth/react';

function locations({ user }) {

    return (
        <div>
            <Navbar />
            <Localisation />
        </div>
    )

}

export async function getServerSideProps(context) {
    const session = await getSession(context);

    if (!session) return { redirect: { destination: "/login", permanent: false, } }

    return {
        props: { user: session.user }
    }
}

export default locations
