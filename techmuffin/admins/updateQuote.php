<?php
	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	
   	$message = "";
   	
   	
    if($_POST['quoteTitle'] != null && $_POST['quoteTitle'] != ''){
	   	$adminPW = $_POST['adminPW'];
	   	if(!authorizeAdmin($_SESSION["UserName"], $adminPW)){
	   		header("Location: login.php");
	   	}
	   	$quoteId = $_POST["quoteId"];
	   	$quoteTitle = $_POST["quoteTitle"];
	   	$quoteDescription = $_POST["quoteDescription"];
	    $hasError = false;
	   	$fileChanged = false;
	   	$quoteFileName = null;
	    
	   	if(isset($_FILES["file"]["name"]) && $_FILES["file"]["name"] != ""){
	   	  $fileChanged = true;
	   	}

	   	if($fileChanged){
	   		$oldQuote = getQuoteById($quoteId);
	   		$result = deleteQuoteFile($oldQuote["filename"]);
	   		$result2 = upload_quote_file();
	   		if( $result == "SUCCESS" && $result2 == "SUCCESS"){
	   			$quoteFileName =  $_FILES["file"]["name"];
	   		}else{
	   			$message = "Unable to upload quote";
	   			$hasError = true;
	   		}
	  	}
	   
	  	if(!$hasError && update_quote_data($quoteTitle, $quoteDescription, $quoteFileName, $quoteId, $fileChanged)){
			$message="Quote " . $quoteTitle . " has been successfully updated.";
		}else{
	   		$message="Unable to update quote: " . $quoteTitle;
	   	}
	   
   	}
   	
   	$quotes = loadAllQuotes();
   	
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  <script language="javascript">
  	function loadquoteForm(){
		var quoteSelect = document.getElementById("quotesToUpdate");
		loadquote(quoteSelect.value);
  	}

  	function loadquote(quoteId)
  	{
	  	if (quoteId.length==0){
	  	  document.getElementById("quoteInfo").innerHTML="?";
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
	  	    	document.getElementById("quoteInfo").innerHTML=xmlhttp.responseText;
	  	    }
	  	  }
	  	xmlhttp.open("GET","getAjaxInfo.php?function=loadQuoteForm&quoteId="+quoteId,true);
	  	xmlhttp.send();
  	}
  </script>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <link rel="stylesheet" type="text/css" href="../css/mainStyle.css">
  <title>TechMuffin! Administrator</title>
</head>
<body>
    	
	<!-- Center Panel -->
	<div id="centerPanel">
		
		<!-- Title Panel -->
		<div>
		  <span>
		  	<p><h2>Update a quote</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Update quote.</h3></p>
			<p><h4><?php print $message;?></h4>
			<hr>
			<p>
			
			<?php 
				if(isset($quotes)){  
			?>
					<input type="hidden" id="action" name="action" value="update"/>				
					<select id="quotesToUpdate" name="quotesToUpdate" onfocus="javascript:loadquoteForm()">

					<?php 
						foreach( $quotes as $key => $value){ 
					    	echo "<option value=\"" . $value["quoteid"] . "\">" . $value["name"] . "</option>";
				   		} 
					?> 
					</select> 
			
				    <div id="quoteInfo">
				    	
				    </div>
			<?php 
				}
			?>
			</p>
		</div>	
</body>
</html>