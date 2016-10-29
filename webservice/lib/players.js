var database = require('./db.js')
,   db       = database.getInstance();

function createTable(){
	getConn(function(connection){
		var query = 'CREATE TABLE IF NOT EXISTS players(' +
						'id int NOT NULL AUTO_INCREMENT, ' + 
						'email VARCHAR(100) NOT NULL, ' + 
						'name VARCHAR(100) NOT NULL' + 
						'pass VARCHAR(50) NOT NULL' + 
					 'PRIMARY KEY (id))';

		connection.query(query);
	})
}

function insert(data, connection, res){
	var query = 'INSERT into players (name,email,pass) VALUES ("'+ data.name + '","' + data.email + ', ' + data.pass + '")';
			
	connection.query(query, function(err, data){
		if(err){
			res.send(false);
		} else {
			res.send(true);
		}
	});
}

function select(data, connection, res){
	var query = 'SELECT name FROM players WHERE email = "' + data.email + '"';
			
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