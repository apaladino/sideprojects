<?php
	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	
   	$message = "";
   	
   	if($_POST['action']){
   		$quoteIds = array();
   		$i = 0;
   		foreach( $_POST as $key => $value){
   			$idCheck = strpos($key, 'quoteid_');
   			if($idCheck >= 0 && $value == "on"){
   				$quoteIds[$i] = str_replace("quoteid_", "", $key);
   			}
   			$i++;
   		}
   		
   		if(count($quoteIds) > 0){
   			$result = deleteQuotes($quoteIds, true);
   			if($result == "SUCCESS"){
   				$message = "Quotes successfully deleted.";
   			}else{
   				$message = $result;
   			}
   		}else{
   			echo "No Quotes selected.";
   		}
   	}
   	
   	$quotes = loadAllQuotes();
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
		  	<p><h2>Remove a Quote</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Remove Quote.</h3></p>
			<p><h4><?php print $message;?></h4>
			<hr>
			<p>
			
			<form action="removeQuote.php" method="post">
				<input type="hidden" id="action" name="action" value="delete"/>
			<?php 
				echo "<table>";
				echo "<th><td>Name</td><td>Description</td><td>File Name</td><td>Created</td></th>";
				if(isset($quotes)){
					foreach( $quotes as $key => $value){ 
					    echo "<tr><td>";
						echo "<input type=\"checkbox\" name=\"quoteid_" . $value["quoteid"] . "\" id=\"quoteid_" . $value["quoteid"] . "\" /></td>";
						echo "<td>" . $value["name"] . "</td>";
						echo "<td>" . $value["description"] . "</td><td>" . $value["filename"] . "</td><td>" . $value["created"] . "</td></tr>";
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