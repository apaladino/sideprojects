import {Hobbit, Baggins, ProudFoot} from './hobbits.js';

export function printHobbitSayings(){
	let bilbo = new Baggins("Bilbo", "Baggins", "The Shire");
	let frodo = new Baggins("Frodo", "Baggins", "The Shire");
	let sam = new Hobbit("Samwise", "Gamgee", "The Shire");
	console.log(bilbo);
	return `${bilbo.printName()} says: ${bilbo.greeting()}.<br/>
${sam.printName()} says: ${sam.greeting()}<br/>
${frodo.printName()} says: ${frodo.greeting()}`;
output.innerHTML = msg;
};
