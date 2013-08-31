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
  <title>TechMuffin! Administrator: Quotes Page</title>
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
			<p>
		  		<h2>Quotes Control Panel</h2>
		  		<hr/>
				<ul>
					<li><a href="addQuote.php">Add Quote</a></li>
					<li><a href="updateQuote.php">Update Quote</a></li>
					<li><a href="removeQuote.php">Remove Quote</a></li>
				</ul>
			</p>
		</div>	
		
		<div id="footerPanel">
  	    </div>
	
</body>
</html>