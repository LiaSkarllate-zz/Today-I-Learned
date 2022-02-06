//Imports:
//import {useEffect, useState} from "react"

function App({users}) {
    return (
        <ul>
            {users.map(user => {
                return (
                    <li key={user.id}>{user.name}</li>
                )
            })}
        </ul>
    )
}

export const getStaticProps/*getServerSideProps*/ = async () => {
    //Variables:
    const headers = new Headers();

    //Logic:
    headers.set('Accept', 'application/json')

    //API consume: 
    const response = await fetch('http://localhost:3333/users', {headers});
    const data = await response.json();

    return {
        props: {
            users: data.data
        },
        revalidate: 5
    }
}

export default App