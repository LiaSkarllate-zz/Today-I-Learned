//Imports:
import React from 'react';

import If from './Function_Conditional'

//Component:
export function Conditional(props) {
    return(
        <div>
            <h2>The number passed is {props.number}</h2>

            {props.number % 2 === 0 ?
                <span>Even</span> :
                <span>Odd</span>
            }

            <If test={props.number % 2 === 0}> 
                <span>Even</span>
            </If>

            <br/>

            <If test={props.number % 2 === 1}> 
                <span>Odd</span>
            </If>
        </div>
    )
}

//Export:
export default Conditional;