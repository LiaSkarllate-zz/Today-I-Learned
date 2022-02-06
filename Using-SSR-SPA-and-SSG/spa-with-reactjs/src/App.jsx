//Imports:
import {useEffect, useState} from "react"

function App() {
    //States:
    const [users, setUsers] = useState([])

    //Effects:
    useEffect(() => {
        //Variables:
        const headers = new Headers();

        //Logic:
        headers.set('Accept', 'application/json')

        //API consume:
        fetch('http://localhost:3333/users', {headers})
            .then(response => response.json())
            .then(data => {
                setUsers(data.data)
            })
        }, []);

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

export default App