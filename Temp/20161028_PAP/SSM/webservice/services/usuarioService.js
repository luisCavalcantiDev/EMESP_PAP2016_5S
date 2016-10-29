var database = require('../lib/db.js')
,   db       = database.getInstance();


function autenticar(req,res){
	//TODO consultar a base com base nos parametros

	res.status(200).json([{"id":"580fb5e9452a6e03002793db","name":"John","role":"Developer"}]);
}

module.exports.autenticar = autenticar;