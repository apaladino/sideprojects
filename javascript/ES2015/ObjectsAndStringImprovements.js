var name = "samwise gamgee";
var age = 47;
var friends = ["Frodo", "Merry", "Pippen"];

/* ##### New Object Initializer Shorthand notation. 					#####
     ###	-replaces { name: name, age: age, friends: friends }
*/
var hobbit = { name, age, friends};

console.log("Hobbit: " + JSON.stringify(hobbit));


/*#####	Object descructuring	#####*/
function buildBook(title, description, author){
	return {title, description, author};
}

// Ojbect descruting automatically pulls out the matching fields from the buildBook function response object and populates
// three new variables: title, description, & author
let {title, description, author} = buildBook("The Hobbit", "A cool book about hobbits and stuff", "J.R.R. Tolkien");
console.log("New book: " + title + ", " + description + ", by " + author);

/*##### Function shorthand initializer ##### */
var myObjectWithFunc = { 
							first, 
							last, 
							showCatchPhrase(){  // function initializer shorthand:  replaces showCatchPhrase: function() { ... }
								console.log("It's no use, Mr. Baggins!");
							}
						};
console.log(myObjectWithFunc.showCatchPhrase());
						
/*##### Templated strings 																#####
	### 	allows you to build complex formatted or multi line strings using variables 
	###		using back tick ``
*/
var fullName = `${first} ${last}`; 
console.log(`Templated string example: ${fullName}`);

var multiLineStr = `Example 
    multi line String using 
	variable evaluation: $fullName}`;
	
console.log("Multi line templated string: " + multiLineStr);






/* ##### Object.assign 
	 ###	merges one or more classes together, by copying properties over from latest classes in 
*/
var defaultOptions = {
	title : "default title",
	size: "small",
	color: "beige"
};

var options1 = {
	title : "options 1 title",
	size : "medium",
	isEnabled : true
};

var options2 = {
	title: "options 2 title",
	size: "large",
	isEnabled: false
};

var options3 = {
	isRunning: false
};

// the very first object passed in will be overwritten by the later parameter objects.
//  -- the right most object properties will over ride the classes to the left.
var mergedOptions = Object.assign({}, defaultOptions, options1, options2, options3);
console.log("Merged Object: " + JSON.stringify(mergedOptions, null, 2));
