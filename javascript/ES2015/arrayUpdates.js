/* #### Array Destructuring ####*/
	
var hobbits = ["Sam", "Frodo", "Merry", "Pippen"];

// array destructuring will set 'Sam' to variable a, skip 'Frodo', and 
// assign 'Merry' to variable b
var [a,,b] = hobbits;
console.log("a=" + a + ", b=" + b);

// you can also use array destructuring with rest parameters
// 		Prints first index, and sets the rest of the array as a rest param
var [firstHobbit, ...allOtherHobbits] = hobbits;
console.log("firstHobbit=" + firstHobbit + ", allOtherHobbits=" + allOtherHobbits);

/* ##### Array.find 	#####
	 ### 	The find method executes the callback function once for each index of the 
	 ###	array until it finds one where callback returns a true value. If such an 
	 ###	element is found, find immediately returns the value of that element. 
	 ###	Otherwise, find returns undefined.
*/
console.log("Found hobbit = " + hobbits.find(function(hobbit){ return hobbit==="Frodo"}));


/* ##### For Of Loops ####
	 ###	loops over the collection's values, 
	 ###	vs for in loops which loop over the indexes. 
*/
for(hobbit of hobbits){
	console.log("Hobbit = " + hobbit);
}
