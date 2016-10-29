var mysql = require('mysql')
,   instance;

module.exports.getInstance = function(){
	if (instance) return instance;
	instance = mysql.createPool({
		host     : 'localhost',
		user     : 'pap',
		password : 'pap123',
		database : 'pap'
	});
	return instance;
};