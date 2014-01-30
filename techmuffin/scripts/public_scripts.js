function getCaption(captionId, min, max){
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
  	    	document.getElementById("caption").innerHTML=xmlhttp.responseText;
  	    }
  	  }
  	var params = host + "util/publicGetAjaxInfo.php?function=getCaptionById&captionId=" + captionId;
  	params += "&mn=" + min + "&mx=" + max;
  	xmlhttp.open("GET", params, true);
  	xmlhttp.send();	
}

function addQuoteForm(quoteId){
		
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
  	    	document.getElementById("addQuoteForm").innerHTML=xmlhttp.responseText;
  	    }
  	  }
  	xmlhttp.open("GET", host + "util/publicGetAjaxInfo.php?function=addQuoteForm&quoteId=" + quoteId, true);
  	xmlhttp.send();
}

function addQuote(quoteId, name, quote){
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

	var params ="action=addQuote&function=addQuote&quoteId=" + quoteId;
  	params += "&name=" + name + "&quote=" + quote;
	xmlhttp.open("POST",host + "util/addQuote.php",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xmlhttp.setRequestHeader("Content-length", params.length);
  	xmlhttp.setRequestHeader("Connection", "close");
  	 
  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
	  		document.getElementById("addQuoteForm").innerHTML=xmlhttp.responseText;
  	    }
  	}
   
  	xmlhttp.send(params);

}

function quoteAjaxPost(){
	var quoteId= document.getElementById("field_quoteId").value;
	var name = document.getElementById("field_name").value;
	var Quote = document.getElementById("field_quote").value;
	addQuote(quoteId, name, Quote);
}

function loadFrame(panel)
{
	var workspace = document.getElementById('workspace');
	var targetPnl = document.getElementById(panel);
	workspace.innerHTML = targetPnl.innerHTML;
}

function addCommentForm(ownerId, type){
	
	if(document.getElementById("addCommentForm").innerHTML != ""){
		document.getElementById("addCommentForm").innerHTML = "";
		return;
	}
	
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
  	    	document.getElementById("addCommentForm").innerHTML=xmlhttp.responseText;
  	    }
  	  }
  	  
  	xmlhttp.open("GET",host + "util/publicGetAjaxInfo.php?function=addCommentForm&ownerId=" + ownerId + "&type=" + type, true);
  	xmlhttp.send();
}

function addComment(ownerId, name, comment, type){

	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

	var params ="action=addComment&function=addComment&ownerId=" + ownerId;
  	params += "&name=" + name + "&comment=" + comment + "&type=" + type;
	
	xmlhttp.open("POST",host + "util/addComment.php",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xmlhttp.setRequestHeader("Content-length", params.length);
  	xmlhttp.setRequestHeader("Connection", "close");
  	 
  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
	  		document.getElementById("addCommentForm").innerHTML=xmlhttp.responseText;
  	    }
  	}
   
  	xmlhttp.send(params);

}

function ajaxPost(){
	var ownerId= document.getElementById("field_ownerId").value;
	var name = document.getElementById("field_name").value;
	var type= document.getElementById("field_type").value;
	var comment = document.getElementById("field_comment").value;
	addComment(ownerId, name, comment, type);
}

function showSubscribeForm(){
	if(document.getElementById("popup").innerHTML != ""){
		document.getElementById("popup").innerHTML = "";
		return;
	}
	
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
  	    	document.getElementById("popup").innerHTML=xmlhttp.responseText;
  	    }
  	  }
  	xmlhttp.open("GET", host + "util/publicGetAjaxInfo.php?function=showSubscribeForm", true);
  	xmlhttp.send();
}

function subscribe(){
	
	var email = document.getElementById("email").value;

	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

	var params ="action=subscribeUser&email="+email;
	xmlhttp.open("POST",host + "util/subscribeUser.php",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xmlhttp.setRequestHeader("Content-length", params.length);
  	xmlhttp.setRequestHeader("Connection", "close");
  	 
  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
	  		document.getElementById("popup").innerHTML=xmlhttp.responseText;
  	    }
  	}
  	
  	xmlhttp.send(params);
}

function showContactUsForm(){
	if(document.getElementById("popup").innerHTML != ""){
		document.getElementById("popup").innerHTML = "";
		return;
	}
	
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
  	    	document.getElementById("popup").innerHTML=xmlhttp.responseText;
  	    }
  	  }
  	  
  	xmlhttp.open("GET",host + "util/publicGetAjaxInfo.php?function=showContactUsForm", true);
  	xmlhttp.send();
}

function showEmailFriendForm(){
	
	if(document.getElementById("popup").innerHTML != ""){
		document.getElementById("popup").innerHTML = "";
		return;
	}
	
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
  	    	document.getElementById("popup").innerHTML=xmlhttp.responseText;
  	    }
  	  }
  	  
  	xmlhttp.open("GET",host + "util/publicGetAjaxInfo.php?function=emailFriendForm", true);
  	xmlhttp.send();
}

function emailToFriend(){
	
	var yourEmail = document.getElementById("yourEmail").value;
	var theirEmail = document.getElementById("theirEmail").value;
	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

	var params ="action=emailToFriend&yourEmail="+yourEmail+"&theirEmail="+theirEmail;
	xmlhttp.open("POST",host + "util/emailToFriend.php",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xmlhttp.setRequestHeader("Content-length", params.length);
  	xmlhttp.setRequestHeader("Connection", "close");
  	 
  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
	  		document.getElementById("popup").innerHTML=xmlhttp.responseText;
  	    }
  	}
  	
  	xmlhttp.send(params);
}

function sendContactMessage(){
	
	var name = document.getElementById("name").value;
	var email = document.getElementById("email").value;
	var message = document.getElementById("message").value;

	if (window.XMLHttpRequest){
	  	// code for IE7+, Firefox, Chrome, Opera, Safari
  	  	xmlhttp=new XMLHttpRequest();
  	}else{
	  	// code for IE6, IE5
  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}

	var params ="action=contactUs&name="+name+"&email="+email+"&message="+message;
	xmlhttp.open("POST",host + "util/contactUs.php",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xmlhttp.setRequestHeader("Content-length", params.length);
  	xmlhttp.setRequestHeader("Connection", "close");
  	 
  	xmlhttp.onreadystatechange=function(){
  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
	  		document.getElementById("popup").innerHTML=xmlhttp.responseText;
  	    }
  	}
  	
  	xmlhttp.send(params);

}
