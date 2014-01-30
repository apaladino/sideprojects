<?php 
   session_start();
   require_once('../config.php');
   require_once(ROOT_PATH . 'util/common/common.php');
   require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	
   require_once("loginCheck.php");
   $userName = $_SESSION['UserName'];
   $message = "";
   if(isset($_POST["action"] ) && $_POST["action"] != ""){
   	
       $newStatus = $_POST["statusField"];   
       if(!updateSiteSettings("status", $newStatus)){
       	  $message = "Unable to update site status.";
       }else{
       	  $message = "Site status updated.";
       }	
   }
   $status = getSiteStatus();
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
		<div id="titlePanel">
		  <span>
		  	<img border="0" src="../images/muffin.png">
		  	<h1>TechMuffin Administrator</h1>
		  	<p><h5>Welcome back <?php echo "$userName";?>.</h5></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>


		<div class="workspace" >
		   <p><h2>Settings</h2></p>
		   <p><h4>Site is currently:  <?php echo $status;?></h4></p>
		   <div><?php echo $message;?></div>
		   <hr/>
		   <p>
		       <form action="home.php" method="post">
		       		<input type="hidden" name="action" value="updateSettings"/>
		       		<input type="radio" name="statusField" value="Running"
		       			<?php 
		       				if($status == "Running"){
		       					echo " checked";
		       				}
		       			?>
		       		/> Running  
		       		<input type="radio" name="statusField" value="Down"
		       			<?php 
		       				if($status == "Down"){
		       					echo " checked";
		       				}
		       			?>
		       		/> Down <br/>
		       		<input type="submit" value="Submit"/> 
		       </form>
		   </p>
		  	
		</div>	
		
		<div id="footerPanel">
  	    </div>
	
</body>
</html>