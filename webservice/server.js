var express = require('express');
var bodyparser = require('body-parser');
var database = require('./lib/db.js');

app = express();
app.use(bodyparser.urlencoded({extended: true}));
app.use(bodyparser.json());

db = database.getInstance();

//Init
app.listen(3000);

console.log('SSM WebService @[port %s, pid %s] Conectando no servidor (db) MySQL...', 3000, process.pid.toString());

db.getConnection(function(err, connection) {});

// Players
console.log('SSM WebService @[port %s, pid %s] configurando players (db)...', 3000, process.pid.toString());

var players = require('./lib/players.js');
players.createTable();

console.log('SSM WebService @[port %s, pid %s] configurando players (routes)', 3000, process.pid.toString());

app.get('/ssm/api/players', function(req,res){
	players.actions(req.query,res);
});


// Seasons TODO: revisar modelagem
/*
console.log('SSM WebService @[port %s, pid %s] configurando seasons (db)', 3000, process.pid.toString());

var seasons = require('./lib/seasons.js');
seasons.createTable();

console.log('SSM WebService @[port %s, pid %s] configurando seasons (routes)', 3000, process.pid.toString());

app.get('//ssm/api/seasons', function(req,res){
	seasons.actions(req.query,res);
});
*/

//TEST: integração com a APK (hardcode) -- remover após correções de conexão com o NodeJS-->MySQL
app.get('/ssm/api/tests/:email', function(req,res){
	var email = req.params.email;
	res.status(200).json([{"id":"1","name":"ssm","email":"ssm@gmail.com","pass":"1234"}]);
});

console.log('SSM WebService @[port %s, pid %s] running', 3000, process.pid.toString());




