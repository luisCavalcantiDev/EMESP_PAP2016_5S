var database = require('./db.js')
,   db       = database.getInstance();

function createTable(){
	getConn(function(connection){
		var query = 'CREATE TABLE IF NOT EXISTS seasons (' + 
						'id int NOT NULL AUTO_INCREMENT, ' + 
						'name VARCHAR(100) NOT NULL, ' + 
						'adminID int NOT NULL, ' + 
					'PRIMARY KEY (id))';

		connection.query(query);
	})
}

function insert(data, connection, res){

	console.log('/api/seasons?action=insert --> data: ');
	console.log(data);

	var query = 'INSERT into seasons (name,adminID) VALUES ("'+ data.name + '","' + data.adminID + '")';
			
	connection.query(query, function(err, data){
		if(err){
			console.log(err);
			res.send('[]');
		} else {
			res.send([data]);
		}
	});
}

function select(data, connection, res){

	console.log('/api/seasons?action=select --> data: ');
	console.log(data);

	var query = 'SELECT id, name, adminID FROM seasons WHERE name = "' + data.name + '"';
			
	connection.query(query, function(err, data){
		if(data){
			res.send(data);
		} else {
			res.send('[]');
		}
	});
}

function selectAdmin(data, connection, res){

	console.log('/api/seasons?action=selectAdmin --> data: ');
	console.log(data);

	var query = 'SELECT id, name FROM seasons WHERE adminID = "' + data.adminID + '"';
			
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
	select    	: select,
	insert 	  	: insert,
	selectAdmin	: selectAdmin 
}

module.exports.actions = actions;
module.exports.createTable = createTable;