<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
    require_once(ROOT_PATH . "util/mysql/dbutil.php");
  
    $status = getSiteStatus();
    if($status != 'Running'){
    	header("Location: downpage.php");
    }
   	
   	$comics = loadAllComics();
   	
    $maxComicId = getCurrentComicId();  
    $minComicId = getMinComicId();	
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
    <meta name="description" content="Brand New Online Comics Site" />
    <meta name="keywords" content="Techmuffin, Online Comics, Comics, Tech, Funny, Hillarious, Humor, Funnies, Laugh, Comic, Techie, Joke, Jokes, Web Comic" />  
  
  <link rel="stylesheet" type="text/css" href="../css/mainStyle.css">
  <title><?php echo TITLE;?> - Archive</title>
  <script type="text/javascript">
  	var host = "<?php echo HOST;?>";
  </script>
  <script type="text/javascript" src="../scripts/public_scripts.js"></script>
</head>
<body>
    	
	<!-- Center Panel -->
	<div id="centerPanel">
		
		<!-- Title Panel -->
		<?php 
		$subtitle = getRandomSubtitle();
		?>
		<div id="titlePanel">
		    <div id="icon">
		    	<img src="<?php echo HOST;?>images/blue_ascii_muffin.png" alt="ascii muffin" >
		    </div>
		    <div id="welcomeText">
			    <h5>Welcome To</h5>
			    <span>
					<img src="<?php echo HOST;?>images/welcome_text_blue.png" style="width:350px;border:0;" alt="TechMuffin">
		  			<h4><?php print($subtitle["phrase"]);?></h4>
		  		</span>
			</div>
		</div>
		<!-- Main Nav -->
		<?php include_once(ROOT_PATH . "includes/mainnav.php"); ?>
		
	    <div class="workspace" >
	    	<div id="archive">
			<p><h4>Archive</h4></p>
			<p>
				<table>
				<?php 
					$numComics = count($comics);
					$i = 0;
					
					for($i = 0; $i < $numComics; $i++){
						$date = new DateTime($comics[$i]["created"]);
						?>
						<tr>
							<td>
							    <a href="<?php echo HOST; ?>?comicId=<?php echo $comics[$i]['comicid'];?>"><?php echo $date->format('M d Y');?></a>
							</td><td>
							 	<a href="<?php echo HOST; ?>?comicId=<?php echo $comics[$i]['comicid'];?>"><h3>"<?php echo $comics[$i]['title'];?>"</h3></a>
							</td>
						</tr>
					<?php }	?>
					
				</table>
			</p>
			</div>
			
			<div id="popup"></div>
			
	    </div>
	    	
	</div>
</body>
</html>