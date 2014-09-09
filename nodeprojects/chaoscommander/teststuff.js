require('colors')
var jsdiff = require('diff');

var one = '######	COMMSESSIONROLES	######,\
	JDOVERSION,ROLEID,ROLETOKEN,SESSIONKEY,\
	null,3,test/LG5ECfxH2DE5ueH9A==,61,\
	null,2,zY8AOikIlieuC4eThDfFlQ==,61,\
	null,1,sU3YxDeG423NDpiy9OekRg==,99,\
	null,3,QZsmRaoG9fzRs0FrbAblKQ==,42,\
	null,2,uIdjrczQzAjGsm4vdxdq0Q==,99,\
	null,1,YjcL0euDb6L9X+D9Sx28NQ==,42,\
	null,3,ynWNGmoalHUUTZ6LlULeEQ==,99,\
	null,2,bW+4Dd9dFmalVTMYqAXqlA==,11,\
	null,1,26+TEST==,11,\
	null,3,tRm5CHiIGhXIOtMawz+WXg==,7,\
	null,2,75ZqwUgvG0M5y+RCyy7gGg==,7,\
	null,1,aiiaAFsPI8T4QFI0Gzob1w==,7,';
var other = '######	COMMSESSIONROLES	######,\
	JDOVERSION,ROLEID,ROLETOKEN,SESSIONKEY,\
	null,3,135S/LG5ECfxH2DE5ueH9A==,61,\
	null,2,zY8AOikIlieuC4eThDfFlQ==,61,\
	null,2,uIdjrczQzAjGsm4vdxdq0Q==,42,\
	null,1,YjcL0euDb6L9X+D9Sx28NQ==,42,\
	null,3,ynWNGmoalHUUTZ6LlULeEQ==,11,\
	null,2,bW+4Dd9dFmalVTMYqAXqlA==,11,\
	null,1,26+krBhnnD0WuYnpgiDpjQ==,11,\
	null,3,tRm5CHiIGhXIOtMawz+WXg==,7,\
	null,2,75ZqwUgvG0M5y+RCyy7gGg==,7,\
	null,1,aiiaAFsPI8T4QFI0Gzob1w==,7,';

var diff = jsdiff.diffChars(one, other);

for(var i=0; i < diff.length; i++){
	console.log(JSON.stringify(diff[i]));
	
	if(diff[i].added){
		console.log("added");
	}
}

diff.forEach(function(part){
  // green for additions, red for deletions
  // grey for common parts
  var color = part.added ? 'green' :
    part.removed ? 'red' : 'grey';
  process.stderr.write(part.value[color]);
});

console.log()
