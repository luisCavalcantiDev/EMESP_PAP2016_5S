var database = require('./db.js')
,   db       = database.getInstance();

function createTable(){
	getConn(function(connection){
		var query = 
			'CREATE TABLE IF NOT EXISTS seasons '
			+'(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, '
			+'name varchar(100), '
			+'adminID int NOT NULL)';
			
		connection.query(query);
	})
}

function get(data,connection,res){
	var query = 'SELECT id,name,adminID FROM players';
			
	connection.query(query,function(err,data){
		if(data){
			res.send(data);
		} else {
			res.send([]);
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
	get : get
}

module.exports.actions = actions;
module.exports.createTable = createTable;