<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	$userName = $_SESSION['UserName'];
   	$message = "";
   	   	
   	/* handle post to add new subtitle */
   	if($_POST['phrase'] != null && $_POST['phrase'] != ''){
   		$adminPW = $_POST['adminPW'];
   		if(!authorizeAdmin($_SESSION["UserName"], $adminPW)){
   			header("Location: login.php");
   		}
   		
   		$phrase = $_POST["phrase"];
   		$category = $_POST["category"];
   		
   		$existingSubtitle = getSubtitleByPhrase($phrase);
   		if($existingSubtitle != null && $existingSubtitle["phrase"] != ""){
   			$message = "Subtitle: \"" . $phrase . "\" already exists.";
   		}else{
	   		if(add_subtitle_data($phrase, $category)){
	   			$message="subtitle '" . $phrase . "' has been successfully added.";
	   		}else{
	   			$message="Unable to add new subtitle to database.";
	   		}
   		}
   	}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  
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
		  	<p><h2>Add a Subtitle</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>


		<div class="workspace" >
			<p><h3>Add New Subtitle.</h3></p>
			<p><h4><?php print $message;?></h4>
			<hr>
			<p>
			<form action="addSubtitle.php" method="post"
				enctype="multipart/form-data">
				<label for="phrase">Phrase:</label>
				<input type="text" name="phrase" id="phrase" />
				<br/>
				<label for="category">Category:</label>
				<input type="text" name="category" id="category" />
				<br/>
				<label for="adminPW">Admin Password</label>
				<input type="password" id="adminPW" name="adminPW" />
				<input type="submit" name="submit" value="Submit" />
			</form>
			</p>
		</div>	
</body>
</html>