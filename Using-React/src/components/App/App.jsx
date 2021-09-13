//Imports:
import './App.css'
import React from 'react';

import Card from '../Card/Card'
import Repetetion from '../Repetition';
import Conditional from '../Conditional';

//Component:
export function App() {
    return(
        <div className="app"> 
            <Card title="This is the card title.">This is a data.</Card>

            <Card title="Opa.">This is a data.</Card>

            <Card title="This is a title exemple passed as parameter.">
                <ul>
                    <li>Paul</li>
                    <li>Musk</li>
                    <li>Jesus</li>
                    <li>Jonh</li>
                </ul>
            </Card>

            <Card title="Repetition."><Repetetion/></Card>

            <Card title="Conditional."><Conditional number={11}/></Card>
        </div>
    )
}

//Export:
export default App;