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
  <title>TechMuffin! Administrator: Subtitles Page</title>
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
		  		<h2>Subtitles Control Panel</h2>
		  		<hr/>
				<ul>
					<li><a href="addSubtitle.php">Add Subtitle</a></li>
					<li><a href="updateSubtitle.php">Update Subtitle</a></li>
					<li><a href="removeSubtitle.php">Remove Subtitle</a></li>
				</ul>
			</p>
		</div>	
	
</body>
</html>