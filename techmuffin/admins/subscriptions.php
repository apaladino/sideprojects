<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");

   	if($_POST["action"] == "updateSubscriptions"){
   		$i = 0;
   		$subIds = array();
   		
   		foreach($_POST as $key => $value){
   			$idCheck = strpos($key, 'sid_');
   			if($idCheck >= 0 && $value == "on"){
   			  $subIds[$i] = str_replace("sid_", "", $key);
   			}
   			$i++;
   		}
   		if(count($subIds) > 0){
   			if(deleteSubscriptions($subIds)){
   				$message = "<div class=\"messages\">Subscriptions removed.</div>";
   			}else{
   				$error = "<div class=\"errors\">Unable to removes subscriptions.</div>";
   			}
   		}
   	}
   	
   	$subscriptions = getAllSubscriptions();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <link rel="stylesheet" type="text/css" href="../css/mainStyle.css">
  <title>TechMuffin! Administrator: Subscriptions Page</title>
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
		  		<h2>Subscriptions Control Panel</h2>
		  		<hr/>
		  		<?php echo $message;?>
				<?php echo $error; ?>
		
				<form action="subscriptions.php" method="POST">
					<input type="hidden" name="action" value="updateSubscriptions"/>	
					<table>
					<tr><td/><td>ID</td><td>Email</td><td>Created</td></tr>
					<?php 
						$i = 0;
						foreach($subscriptions as $key => $value){
					?>
						<tr>
							<td><input type="checkbox" id="sid_<?php echo $value["subscriptionid"];?>" name="sid_<?php echo $value["subscriptionid"];?>"/></td>
							<td><?php echo $value["subscriptionid"];?></td>
							<td><?php echo $value["email"];?></td>
							<td><?php echo $value["created"];?></td>
						</tr>						
					<?php 
							$i = $i + 1;
						}
					?>
					</table>
					<input type="submit" value="Submit"/>
				</form>
			</p>
		</div>	
	
</body>
</html>