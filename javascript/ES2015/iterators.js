/* #### Iterators #### */

// first define an object.
var hobbit = {
	firstName: "Bilbo",
	lastName: "Baggins",
	homeTown: "The Shire",
	greeting() {
		console.log("Good Day!");
	}
};

// next define a custom iterator for that object
hobbit[Symbol.iterator] = function(){
	// must return an object with format { done: <boolean>, value: <some value> }
	
	// get properties for object
	let properties = Object.keys(this);
	let count = 0; 
	let done = false;
	let next = () => {
		if(count >= properties.length){
			done = true;
		}
		
		// set the value to the current property value, then increment the property count
		let value = this[properties[count++]];
	
		return { done, value};
	};
	
	return {next };
};

// now lets try reading all of the properties for the hobbit object in a for of loop
for(prop of hobbit){
	console.log(prop);
}

// now we can use the spread operator on the hobbit object
let hobbitProps = [...hobbit];
console.log("HobbitProps: " + hobbitProps);

// also can leverage array destructoring 
let [firstName, lastName] = hobbit;
console.log(`Hobbit name ${firstName} ${lastName}`);