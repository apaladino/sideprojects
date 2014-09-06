<?php
	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	
   	$message = "";
   	
   	if($_POST['action']){
   		$subtitleIds = array();
   		$i = 0;
   		foreach( $_POST as $key => $value){
   			$idCheck = strpos($key, 'subtitleid_');
   			if($idCheck >= 0 && $value == "on"){
   				$subtitleIds[$i] = str_replace("subtitleid_", "", $key);
   			}
   			$i++;
   		}
   		
   		if(count($subtitleIds) > 0){
   			
   			$result = deletesubtitles($subtitleIds);
   			if($result == "SUCCESS"){
   				$message = "subtitles successfully deleted.";
   			}else{
   				$message = $result;
   			}
   		}else{
   			$message = "No subtitles selected.";
   		}
   	}
   	
   	$subtitles = loadAllsubtitles();
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
		  	<p><h2>Remove a subtitle</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Remove subtitle.</h3></p>
			<p><h4><?php print $message;?></h4>
			<hr>
			<p>
			
			<form action="removeSubtitle.php" method="post">
				<input type="hidden" id="action" name="action" value="delete"/>
			<?php 
				echo "<table>";
				echo "<th><td>Phrase</td><td>Category</td><td>Created</td></th>";
				if(isset($subtitles)){
					foreach( $subtitles as $key => $value){ 
						
					    echo "<tr><td>";
						echo "<input type=\"checkbox\" name=\"subtitleid_" . $value["subtitleid"] . "\" id=\"subtitleid_" . $value["subtitleid"] . "\" /></td>";
						echo "<td>" . $value["phrase"] . "</td>";
						echo "<td>" . $value["category"] . "</td><td>" . $value["created"] . "</td></tr>";
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