<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	$userName = $_SESSION['UserName'];
   	$comicId = getCurrentComicId();
    
   	$message = "";
   	  	
   	/* handle post to add new comic */
   	if($_POST['quoteName'] != null && $_POST['quoteName'] != ''){
   		$adminPW = $_POST['adminPW'];
   		if(!authorizeAdmin($_SESSION["UserName"], $adminPW)){
   			header("Location: login.php");
   		}
   		if(upload_quote_file()){
   			$quoteName = $_POST["quoteName"];
   			$quoteFileName =  $_FILES["file"]["name"];
   			$description = $_POST["description"];
   			
   			if(add_quote_data($quoteName, $description, $quoteFileName)){
   				$message="Quote " . $quoteName . " has been successfully added.";
   			}else{
   				$message="Unable to add new quote to database.";
   			}
   		}else{
   			echo "Unable to upload quote";
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
		  	<p><h2>Add a Quote</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Add New Quote.</h3></p>
			<p><h4><?php print $message;?></h4>
			<hr>
			<p>
			<form action="addQuote.php" method="post"
				enctype="multipart/form-data">
				<p>
					<label for="quoteName">Quote Name:</label>
					<input type="text" name="quoteName" id="quoteName" />
				</p>
				<p>
					<label for="description">Description:</label>
					<input type="text" name="description" id="description" />
				</p>
				<p>
					<label for="file">Filename:</label>
					<input type="file" name="file" id="file" />
				</p>
				<p>
					<label for="adminPW">Admin Password</label>
					<input type="password" id="adminPW" name="adminPW" />
				</p>
				<input type="submit" name="submit" value="Submit" />
			</form>
			</p>
		</div>	
</body>
</html>