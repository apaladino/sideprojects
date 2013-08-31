<?php 
   	session_start();
   	require_once('config.php');
   	require_once('util/common/common.php');
    require_once("util/mysql/dbutil.php");
  
    $status = getSiteStatus();
    if($status != 'Running'){
    	header("Location: downpage.php");
    }
   	
   	if(isset($_GET["comicId"]) && $_GET["comicId"] != ""){
   	    $comicId = $_GET["comicId"];
   	    $currentComic = loadComicById($comicId);	
   	}else{
   		$currentComic = loadCurrentComic();
   	}
   	
   	$comidId = "";
   	$comicImg = "";
   	$comments = array();
   	if(isset($currentComic)){
   		$comicId = $currentComic["comicid"];
   		$comicTitle = $currentComic["title"];
   		$comicImg = "comics/" . $currentComic["location"];
   		$comments = loadAllComments($comicId, "comics");
   	}
   	
    $maxComicId = getCurrentComicId();  
    $minComicId = getMinComicId();	
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <meta name="description" content="Brand New Online Comics Site">
  <meta name="keywords" content="Techmuffin, Online Comics, Comics, Tech, Funny, Hillarious, Humor, Funnies, Laugh, Comic, Techie, Joke, Jokes, Web Comic" >  
  <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
  <title><?php echo TITLE;?> - Home Page</title>
  <script type="text/javascript">
  	var host = "<?php echo HOST;?>";
  </script>
  <script type="text/javascript" src="scripts/public_scripts.js" ></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.js"></script>
</head>
<body>
    	
	<!-- Center Panel -->
	<div id="centerPanel">
		
		<!-- Title Panel -->
		<?php include_once("includes/titlebar.php"); ?>
		
		<!-- Main Nav -->
		<?php include_once("includes/mainnav.php"); ?>
		
	    <div class="workspace" >
			<?php 
				if($comicImg != ""){
					
			?>
			<div class="comic">
			    <div>
			    	<h3><?php print("$comicTitle");?></h3>
			    	<div class="arrows">
			    		<?php 
			    			if($comicId > $minComicId){
			    			    $targetComicId = $comicId - 1;	
			    		?>
			    		<a href="index.php?comicId=<?php echo "$targetComicId";?>">
			    			<img src="images/leftarrow_white.png" width="25px" alt="Previous Comic">
			    		</a>
			    		<?php }
			    			if($comicId < $maxComicId){
			    				$targetComicId = $comicId + 1;
			    		?>
			    		<a href="index.php?comicId=<?php echo "$targetComicId";?>">
			    			<img src="images/rightarrow_white.png" width="25px" alt="Next Comic">
			    		</a>
			    		
			    		<?php }?>
			    	</div>
			    </div>
				<img src="<?php print("$comicImg");?>" alt="Comic">
			</div>
			
			<div id="popup"></div>
			
			<div id="comments">
				<p>
					<h2>Comments</h2>
					<ul>
						<li><a href="javascript:addCommentForm(<?php echo $comicId;?>, 'comics')">Add Comment</a></li>
					</ul>
				</p>
				<div id="addCommentForm"></div>
					
				<div id="commentsList">
					<?php 
					    if($comments != null && isset($comments)){
							foreach($comments as $key => $value){
					?>
							<p>
								<h4><?php echo $value["comment"];?></h4>
							    <h5>-<?php echo $value["name"];?></h5>
							</p>
					<?php 
							}
					    }
					?>
				</div>
			</div>
			<?php 
				}
			?>
			
	    </div>	
	</div>
</body>
</html>