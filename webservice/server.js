var express = require('express');
var database = require('./lib/db.js');

app = express();
db = database.getInstance();

//Init
app.listen(4000);

console.log('SSM WebService @[port %s, pid %s] Conectando no servidor (db) MySQL...', 4000, process.pid.toString());

db.getConnection(function(err, connection) {});

// Players
console.log('SSM WebService @[port %s, pid %s] configurando players (db)...', 4000, process.pid.toString());

var players = require('./lib/players.js');
players.createTable();

console.log('SSM WebService @[port %s, pid %s] configurando players (routes)', 4000, process.pid.toString());

app.get('/api/players', function(req,res){
	players.actions(req.query,res);
});

 
//Seasons

console.log('SSM WebService @[port %s, pid %s] configurando seasons (db)', 4000, process.pid.toString());

var seasons = require('./lib/seasons.js');
seasons.createTable();

console.log('SSM WebService @[port %s, pid %s] configurando seasons (routes)', 4000, process.pid.toString());

app.get('/api/seasons', function(req,res){
	seasons.actions(req.query,res);
});

//Ranking

console.log('SSM WebService @[port %s, pid %s] configurando ranking (db)', 4000, process.pid.toString());

var ranking = require('./lib/ranking.js');
ranking.createTable();

console.log('SSM WebService @[port %s, pid %s] configurando ranking (routes)', 4000, process.pid.toString());

app.get('/api/ranking', function(req,res){
	ranking.actions(req.query,res);
});


//TEST: integração com a APK (hardcode) -- remover após correções de conexão com o NodeJS-->MySQL
// app.get('/api/tests/:email', function(req,res){
	// var email = req.params.email;
	// res.status(200).json([{"id":"1","name":"ssm","email":"ssm@gmail.com","pass":"1234"}]);
// });

console.log('SSM WebService @[port %s, pid %s] running', 4000, process.pid.toString()); 



