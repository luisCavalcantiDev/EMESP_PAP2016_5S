var express = require('express')
,   app = express();

//Configurações SSM - Base Dados WebSerice (TESTAR COM MYSQL)////////

// Players
//var players = require('./lib/players.js');
//players.createTable();
// app.get('/players', function(req,res){
// 	players.actions(req.query,res);
// });

// // Seasons
// var seasons = require('./lib/seasons.js');
// seasons.createTable();
// app.get('/seasons', function(req,res){
// 	seasons.actions(req.query,res);
// });

///////////////////////////////////////////////////

//Services da API//////////////////////////////////
var usuarioService = require('./services/usuarioService.js');

app.get('/ssm/api/test',function(req,res){
	res.json({test: "teste chamando api"});
});


//Usuário
app.get('/ssm/api/usuario',function(req,res){
	usuarioService.autenticar(req,res);
});


///////////////////////////////////////////////////

app.listen(3000);

console.log('SSM API running...');