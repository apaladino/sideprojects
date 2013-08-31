<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	$userName = $_SESSION['UserName'];
   	$comicId = getCurrentComicId();
   	$currentComic = null;
   	if($comicId != null){
   		$currentComic = loadComicById($comicId);
   	}else{
   		$comicId = "EMPTY";
   	}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <link rel="stylesheet" type="text/css" href="../css/mainStyle.css">
  <title>TechMuffin! Administrator: Comics Page</title>
</head>
<body>
    	
	<!-- Center Panel -->
	<div id="centerPanel">
		
		<!-- Title Panel -->
		<div id="titlePanel">
		  <span>
		  	<img border="0" src="../images/muffin.png">
		  	<h1>TechMuffin Administrator</h1>
		  </span>
		</div>
	
		<?php include("includes/adminNav.php");?>


		<div class="workspace" >
			<div class="adminPanel">
			<p>
		  		<h2>Comics Control Panel</h2>
		  		<p>
		  			<div>
		  			    <h3>Current Comic Id:  <?php print("$comicId");?></h3>
		  			</div>
		  		</p>
				<ul>
					<li><a href="addComic.php">Add Comic</a></li>
					<li><a href="updateComic.php">Update Comic</a></li>
					<li><a href="removeComic.php">Remove Comic</a></li>
				</ul>
			</p>
			</div>
		</div>	
		
		<div id="footerPanel">
  	    </div>
	
</body>
</html>