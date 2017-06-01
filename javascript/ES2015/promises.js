/* ##### Promises #####*/
function play21Hand(cards){
	let myPromise = new Promise(
        (resolve, reject) => {
           window.setTimeout(
                function() {
					if(cards !== undefined){
						// evaluate cards value and generate the appropriate message
			
						let msg = "";
			
						if(cards === 21){
							msg = "You got a 21. You Win!!";
						}else if(cards > 21){
							msg = "You got " + cards + ", you lose!";
						}else if( cards >= 17){
							msg = "You got " + cards + ". You should stay.";
						}else{
							msg = "You got " + cards + ". You should hit.";
						}
						resolve(msg);
					}else{
						reject(new Error("Missing cards parameter."));
					}
                }, Math.random() * 2000 + 1000);
        }
    )
	.then( 
		function(msg) {
			console.log(msg);
        }
	)
	.catch(function(error){
		console.log("Error occurred: " + error);
	})
}

play21Hand(21);
play21Hand(23);
play21Hand(18);
play21Hand();
play21Hand(7);