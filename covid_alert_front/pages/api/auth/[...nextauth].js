import axios from "axios"
import jwtDecode from "jwt-decode"
import NextAuth from "next-auth"
import CredentialsProvider from 'next-auth/providers/credentials'
import configuration from "../../../config/config"

export default NextAuth({
    // Configure one or more authentication providers
    providers: [
        CredentialsProvider({
            name: 'Credentials',

            credentials: {
                username: { label: "Username", type: "text", placeholder: "jsmith" },
                password: { label: "Password", type: "password" }
            },

            async authorize(credentials, req) {
                console.log(credentials)

                // TODO : clean code

                const API_URL = configuration.API_URL + ':28081'
                const REALM_NAME = 'microservices-realm'
                const CLIENT_ID = 'web-front'
                const GRANT_TYPE = 'password'


                const params = new URLSearchParams()
                params.append('username', credentials.username)
                params.append('password', credentials.password)
                params.append('client_id', CLIENT_ID)
                params.append('grant_type', GRANT_TYPE)

                const config = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }

                try {
                    const usr = await axios
                        .post(`${API_URL}/auth/realms/${REALM_NAME}/protocol/openid-connect/token`, params, config)

                    let token = usr.data.access_token
                    let user = jwtDecode(token)

                    console.log(user)

                    if (token) {
                        return user
                    }

                } catch (e) {
                    console.log(e)
                    return null
                }
            }
        })
    ],

    callbacks: {
        jwt: async ({ token, user }) => {

            user && (token.user = user)
            return token

        },
        session: ({ session, token }) => {

            session.user = token.user
            return session
        }
    },
    pages: {
        signIn: '/login',
        // error: '/login',
        // signOut: '/login'
    }

})

