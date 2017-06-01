/* ##### Classes 	#####

*/
class Hobbit{
	
	
	constructor(fn, ln, ht){
		this.firstName = fn;
		this.lastName = ln;
		htis.homeTown = ht;
	}
	
	greeting(){
		console.log("Hallo there!");
	}
	
	printName(){
		console.log(`${this.firstName} ${this.lastName}.`);
	}
};

class Baggins extends Hobbit{
	
	constructor(fn, ln, ht){
		super(fn, ln, ht);
	}
	
	greeting(){
		console.log("Good Day!");
	}
};

class ProudFoot extends Hobbit{
	
	constructor(fn, ln, ht){
		super(fn, ln, ht);
	}
	
	greeting(){
		console.log("That's proud FEET!");
	}
};

var bilbo = new Baggins("Bilbo", "Baggins", "The Shire");
var odoProudfoot = new ProudFoot("Odo", "Proudfoot", "Hobbiton");
var sam = new Hobbit("Samwise", "Gamgee", "The Shire");