var database = require('./db.js')
,   db       = database.getInstance();

function createTable(){
	getConn(function(connection){
		var query = 'CREATE TABLE IF NOT EXISTS players (' +
						'id int NOT NULL AUTO_INCREMENT, ' + 
						'email VARCHAR(100) NOT NULL, ' + 
						'name VARCHAR(100) NOT NULL,' + 
						'pass VARCHAR(50) NOT NULL,' + 
					 'PRIMARY KEY (id))';

		connection.query(query);
	})
}

function get(data,connection,res){
	var query = 'SELECT id,name,email FROM players';
			
	connection.query(query,function(err,data){
		if(data){
			res.send(data);
		} else {
			res.send([]);
		}
	});
}

function insert(data, connection, res){

	console.log('/api/players --> data: ');
	console.log(data);

	var query = 'INSERT into players (name,email,pass) VALUES ("'+ data.name + '","' + data.email + '", "' + data.pass + '")';
			
	connection.query(query, function(err, data){
		if(err){
			res.send({"processado" : false, "msg" : err});
		} else {
			res.send({"processado" : true, "msg" : ""});
		}
	});
}

function select(data, connection, res){

	console.log('/api/players --> data: ');
	console.log(data);

	var query = 'SELECT id, name FROM players WHERE email = "' + data.email + '" AND pass = "' + data.pass + '"';
			
	connection.query(query, function(err, data){
		if(data){
			res.send(data);
		} else {
			res.send('[]');
		}
	});
}

function actions(data, res){
	if(data && action[data.action]){
		getConn(function(connection){
			action[data.action](data, connection, res);
		})
	}
};

function getConn(cb){
	db.getConnection(function(err, connection) {
		cb(connection);
	});
}

var action = {
	select    : select,
	insert 	  : insert
}

module.exports.actions = actions;
module.exports.createTable = createTable;