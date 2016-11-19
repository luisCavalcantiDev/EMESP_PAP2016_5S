var database = require('./db.js')
,   bcrypt   = require('bcrypt-nodejs')
,   salt     = bcrypt.genSaltSync(10)
,   db       = database.getInstance();

function createTable(){
	getConn(function(connection){
		var query = 'CREATE TABLE IF NOT EXISTS players (' +
						'id int NOT NULL AUTO_INCREMENT, ' + 
						'email VARCHAR(100) NOT NULL, ' + 
						'name VARCHAR(100) NOT NULL,' + 
						'pass VARCHAR(100) NOT NULL,' + 
					 'PRIMARY KEY (id))';

		connection.query(query);
	})
}

function insert(req, connection, res){

	console.log('/api/players?action=insert --> request: ');
	console.log(req);
	
	var hashPass = bcrypt.hashSync(req.pass, salt);
	var query = 'INSERT into players (name,email,pass) VALUES ("'+ req.name + '","' + req.email + '", "' + hashPass + '")';
			
	connection.query(query, function(err, data){
		if(err){
			console.log(err);
			res.send('[]');
		}

		console.log('/api/players?action=insert --> response: ');
		console.log(data);
	});

	query = 'SELECT id, name, email, pass FROM players WHERE email = "' + req.email + '"';
			
	connection.query(query, function(err, data){
		if(data){
			console.log(data);
			res.send(data);
		} else {
			res.send('[]');
		}
	});

}

function select(req, connection, res){

	console.log('/api/players?action=select --> request: ');
	console.log(req);
	
	var query = 'SELECT id, name, email, pass FROM players WHERE email = "' + req.email + '"';
			
	connection.query(query, function(err, data){
		if(data){
			console.log('/api/players?action=select --> response: ');
			console.log(data);

			bcrypt.compare(req.pass, data[0].pass, function(err, validPass) {
				if(validPass){
					res.send(data);
				} else {
					res.send('[]');
				}
			});
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