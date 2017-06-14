var express = require ('express');
var bodyParser = require ('body-parser');
var request = require('request').defaults({json:'true'});
var httpProxy = require('http-proxy');
var fileupload = require('express-fileupload');
var util = require('util');
var path = require('path');


var app = express();
app.use('/login', bodyParser.json());
app.use(bodyParser.json());
app.use('/upload', fileupload());



var mysql = require ('mysql');
var con = mysql.createConnection({
  host: "localhost",
  user: "Abhinav",
  password: "abhi4227",
  database: "ambdata"
});

app.use(bodyParser.json({extended:true}));

app.use(bodyParser.urlencoded({ 
   extended: true 
}));

app.listen(3001);

app.use('/', bodyParser.json());

app.post('/',function(req,res){
	console.log('Home request');

	res.send("Welcome");
});

app.post('/login',function(req,res){
	console.log(req.body.userid);
	console.log(req.body.password)
	


  console.log("Connected!");

con.query('select password from logvalidity where name = ? ',[req.body.userid], function(err,result){

    if (err) throw err;


	//console.log(result[0].password);
	if(result[0].password == req.body.password)
	res.send("OK")

	else 
	res.send("Incorrect Uid or password")


	  });
	
	
});

app.post('/info', function(req,res){
	
console.log(req.body.src);
console.log(req.body.dest);

res.send("OK");

});


