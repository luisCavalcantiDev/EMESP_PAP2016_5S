var mysql = require('mysql')
,   instance;

module.exports.getInstance = function(){
	if (instance) return instance;
	instance = mysql.createPool({
		host     : 'localhost',
		user     : 'root',
		password : 'root',
		database : 'pap'
	})
	
	return instance;
};