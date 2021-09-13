//Imports:
import './Card.css'
import React from 'react';

//Component:
export function Card(props) {
    return(
        <div className="card">
            <div className="data">
                {props.children}
            </div>

            <div className="footer">
                <p>{props.title}</p>
            </div>
        </div>
    )
}

//Export:
export default Card;