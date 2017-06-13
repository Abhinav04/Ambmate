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
	console.log('Home request post');
	console.log(req.body.userid);
	console.log(req.body.password)
	


con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");

con.query("Select password from logvalidity where name = req.body.userid ", function(err,result){

    if (err) throw err;
    console.log(result);
  });
});
 


	//res.set('Content-Type', 'text/html');
        if(req.body.userid=='Abhi' && req.body.password=='abhinav')
        res.send("OK");
	// console.log(req.body.abc);
	else 
	res.send("Fail");
	
});
