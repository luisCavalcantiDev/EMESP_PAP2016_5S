var database = require('./db.js')
,   db       = database.getInstance();

function createTable(){
	getConn(function(connection){
		var query = 
			'CREATE TABLE IF NOT EXISTS players '
			+'(email VARCHAR(100) PRIMARY KEY NOT NULL, '
			+'name VARCHAR(100))';
				
		connection.query(query);
	})
}

function get(data,connection,res){
	var query = 'SELECT id,name,email FROM players';
			
	connection.query(query,function(err,data){
		if(data){
			res.send(data);
		} else {
			res.send('[]');
		}
	});
}

function insert(data,connection,res){
	var query = 'INSERT into players (name,email) VALUES ("'+data.name+'","'+data.email+'")';
			
	connection.query(query,function(err,data){
		if(err){
			res.send(false);
		} else {
			res.send(true);
		}
	});
}

function actions(data,res){
	if(data && action[data.action]){
		getConn(function(connection){
			action[data.action](data,connection,res);
		})
	}
};

function getConn(cb){
	db.getConnection(function(err, connection) {
		cb(connection);
	});
}

var action = {
	get    : get,
	insert : insert
}

module.exports.actions = actions;
module.exports.createTable = createTable;