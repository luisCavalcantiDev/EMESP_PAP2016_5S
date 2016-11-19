var database = require('./db.js')
,   db       = database.getInstance();

function createTable(){
	getConn(function(connection){
		var query = 'CREATE TABLE IF NOT EXISTS ranking (' + 
						'id int NOT NULL AUTO_INCREMENT, ' + 
						'campeonato VARCHAR(100) NOT NULL, ' + 
						'time VARCHAR(100) NOT NULL, ' + 
						'userID int NOT NULL, ' + 
						'seasonID int NOT NULL, ' + 
						'points int NOT NULL, ' + 
					'PRIMARY KEY (id))';

		connection.query(query);
	})
}

function insert(req, connection, res){

	console.log('/api/ranking?action=insert --> request: ');
	console.log(req);

	var query = 'INSERT into ranking (campeonato,time,userID,seasonID,points) VALUES ("'+ req.campeonato + '","' + req.time + '", "' + req.userID + '", "' + req.seasonID + '", "' + req.points + '")';
			
	connection.query(query, function(err, data){
		if(err){
			console.log(err);
			res.send('[]');
		}

		console.log('/api/ranking?action=insert --> response: ');
		console.log(data);
	});

	var query = 'SELECT id, campeonato, time, userID, seasonID, points FROM ranking ORDER BY id DESC LIMIT 1';
			
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

	console.log('/api/ranking?action=select --> request: ');
	console.log(req);

	var query = 'SELECT id, campeonato, time, userID, seasonID, points FROM ranking WHERE id = "' + req.id + '"';
			
	connection.query(query, function(err, data){
		if(data){
			console.log(data);
			res.send(data);
		} else {
			res.send('[]');
		}
	});
}

function selectSeason(req, connection, res){

	console.log('/api/ranking?action=selectSeason --> request: ');
	console.log(req);

	var query = 'SELECT id, campeonato, time, userID, seasonID, points FROM ranking WHERE seasonID = "' + req.seasonID + '"';
			
	connection.query(query, function(err, data){
		if(data){
			console.log(data);
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
	select    	 : select,
	insert 	  	 : insert,
	selectSeason : selectSeason 
}

module.exports.actions = actions;
module.exports.createTable = createTable;