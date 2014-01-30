<?php
	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	
   	$message = "";
	$error = "";   	
   	
	if($_POST['action']){
   		$blogs = array();
   		$i = 0;
   		foreach( $_POST as $key => $value){
   			$idCheck = strpos($key, 'blogid_');
   			if($idCheck >= 0 && $value == "on"){
   				$blogIds[$i] = str_replace("blogid_", "", $key);
   			}
   			$i++;
   		}
   		
   		if(count($blogIds) > 0){
   			
   			$result = deleteblogs($blogIds);
   			if($result == "SUCCESS"){
   				$message = "blogs successfully deleted.";
   			}else{
   				$message = $result;
   			}
   		}else{
   			$message = "No blogs selected.";
   		}
   	}
   	
	$blogs = loadAllBlogs();
       	
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
		  	<p><h2>Remove a Blog</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Remove Blog.</h3></p>
			<p>
				<?php print $message;?>
				<?php print $error; ?>
			<hr>
			<p>
			<form action="removeBlog.php" method="post">
				<input type="hidden" id="action" name="action" value="delete"/>
			<?php 
				echo "<table>";
				echo "<th><td>Title</td></th>";
				if(isset($blogs)){
					foreach( $blogs as $key => $value){ 
					    echo "<tr>";
					    echo "<td>";
						echo "<input type=\"checkbox\" name=\"blogid_" . $value["blogid"] . "\" id=\"blogid_" . $value["blogid"] . "\" /></td>";	
						echo "<td>" . $value["title"] . "</td></tr>";
					}
				}
				echo "</table>";
			?>	

				<br />
				<input type="submit" name="submit" value="Submit" />
			</form>
		
			</p>
		</div>	
</body>
</html>