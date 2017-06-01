/* ####	Maps ####
	The Map object holds key-value pairs. Any value (both objects and primitive values) 
	may be used as either a key or a value.
*/
var randomString = 'a random string',
    user1 = { "name" : "bob jones", "age": 42},
    user2 = { "name" : "sally jenkins", "age" : 26},
    someFunc = function() { return "hello world!"; };

// setting the values
var myMap = new Map();
myMap.set(randomString, "value associated with a random string");
myMap.set(user1, 'value associated with user1');
myMap.set(user2, 'value associated with user2');
myMap.set(someFunc, 'value associated with someFunc');

myMap.size; // 4

// getting the values
console.log(myMap.get(randomString));    
console.log(myMap.get(user1))
console.log(myMap.get(user2));
console.log(myMap.get(someFunc));

// you can loop through a map using a for of loop and array deserialization
for( var [key, value] of myMap){
	console.log(`Key: ${key} , value: ${value}`);
}

/* #### Weak Maps ####
	Weak maps are much more memory efficient, but only allow us to use objects as keys
	and also are not iterable using for of/in loops 
*/
var myWeakMap = new WeakMap();
myWeakMap.set(user1, "some value for user 1");
myWeakMap.set(user2, "some other value for user 2");
// myWeakMap.set("Some primitive string key", "Oh Noos");   ** won't work. 