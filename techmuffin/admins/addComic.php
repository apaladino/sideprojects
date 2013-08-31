<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	$userName = $_SESSION['UserName'];
   	$comicId = getCurrentComicId();
    
   	$message = "";
   	
   	if($comicId == null ){
   		$comicId = 0;
   	}
   	
   	/* handle post to add new comic */
   	if($_POST['comicName'] != null && $_POST['comicName'] != ''){
   		$adminPW = $_POST['adminPW'];
   		if(!authorizeAdmin($_SESSION["UserName"], $adminPW)){
   			header("Location: login.php");
   		}
   		if(upload_comic_file()){
   			$comicName = $_POST["comicName"];
   			$comicTitle = $_POST["comicTitle"];
   			$comicFileName =  $_FILES["file"]["name"];
   			$comicId += 1;
   			if(add_comic_data($comicName, $comicTitle, $comicFileName, $comicId)){
   				$message="Comic " . $comicName . " has been successfully added.";
   				echo $message . "<br/>";
   				notifySubscriptions($comicTitle, $comicId);
   			}else{
   				$message="Unable to add new comic to database.";
   			}
   		}else{
   			echo "Unable to upload comic";
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
		  	<p><h2>Add a Comic</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Add New Comic.</h3></p>
			<p><h4><?php print $message;?></h4>
			<hr>
			<p>
			<form action="addComic.php" method="post"
				enctype="multipart/form-data">
				<p>
					<label for="comicName">Name:</label>
					<input type="text" name="comicName" id="comicName" />
				</p>
				<p>
					<label for="comicTitle">Title:</label>
					<input type="text" name="comicTitle" id="comicTitle" size="30"/>
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