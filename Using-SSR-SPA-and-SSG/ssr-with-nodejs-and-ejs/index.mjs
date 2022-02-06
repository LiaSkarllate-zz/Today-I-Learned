//Imports:
import express from "express";
import cors from 'cors'

//App initialization:
const app = express();
app.listen(3333);
app.set('view engine', 'ejs');
app.use(cors())

//Routes:
app.get('/users', (req, res) => {
    const users = [
        {id: 1, name: 'José Augusto'},
        {id: 2, name: 'Gabriel Pato'},
        {id: 3, name: 'Bruno Fraga'},
    ];

    if(req.header('Accept') === 'application/json'){
        //It returns the data:
        return res.json({data: users})
    }

    //It returns a full HTML page rendered:
    return res.render('users/list', {users})
});