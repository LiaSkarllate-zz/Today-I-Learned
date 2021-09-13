//Imports:
import React from 'react';

import products from '../data/products'

//Component:
export function Repetetion(props) {
    //Functions:
    function getProducts() {
        return products.map(product => {
            return <li key={product.id}>
                {product.name} ({product.id}) is U${product.price}
            </li>
        })
    }

    //Return:
    return(
        <div>
            <ul>
                {getProducts()}
            </ul>
        </div>
    )
}

//Export:
export default Repetetion;