<?php
	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	
   	$message = "";
   	
   	
    if($_POST['phrase'] != null && $_POST['phrase'] != ''){
	   	$adminPW = $_POST['adminPW'];
	   	if(!authorizeAdmin($_SESSION["UserName"], $adminPW)){
	   		header("Location: login.php");
	   	}
	   	$subtitleId = $_POST["subtitleId"];
	   	$phrase = $_POST["phrase"];
	   	$category = $_POST["category"];
	    
	   	if(update_subtitle_data($phrase, $category, $subtitleId)){
			$message="subtitle \"" . $phrase . "\" has been successfully updated.";
		}else{
	   		$message="Unable to update subtitle: \"" . $phrase . "\"";
	   	}
	   
   	}
   	
   	$subtitles = loadAllsubtitles();
   	
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  <script language="javascript">
  	function loadsubtitleForm(){
		var subtitleSelect = document.getElementById("subtitlesToUpdate");
		loadsubtitle(subtitleSelect.value);
  	}

  	function loadsubtitle(subtitleId)
  	{
	  	if (subtitleId.length==0){
	  	  document.getElementById("subtitleInfo").innerHTML="?";
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
	  	    	document.getElementById("subtitleInfo").innerHTML=xmlhttp.responseText;
	  	    }
	  	  }
	  	xmlhttp.open("GET","getAjaxInfo.php?function=loadSubtitleForm&subtitleId="+subtitleId,true);
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
		  	<p><h2>Update a subtitle</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Update subtitle.</h3></p>
			<p><h4><?php print $message;?></h4>
			<hr>
			<p>
			
			<?php 
				if(isset($subtitles)){  
			?>
					<input type="hidden" id="action" name="action" value="update"/>				
					<select id="subtitlesToUpdate" name="subtitlesToUpdate" onfocus="javascript:loadsubtitleForm()">

					<?php 
						foreach( $subtitles as $key => $value){ 
					    	echo "<option value=\"" . $value["subtitleid"] . "\">" . $value["phrase"] . "</option>";
				   		} 
					?> 
					</select> 
			
				    <div id="subtitleInfo">
				    	
				    </div>
			<?php 
				}
			?>
			</p>
		</div>	
</body>
</html>