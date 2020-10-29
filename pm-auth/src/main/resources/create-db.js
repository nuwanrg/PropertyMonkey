var db = connect('54.87.53.249:27017/pm');
pm=null;
db.roles.createIndex( { "name": 1 }, { unique: true } );
db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_AGENT" },
   { name: "ROLE_ADMIN" },
   { name: "ROLE_DEVELOPER"} 	
]);

