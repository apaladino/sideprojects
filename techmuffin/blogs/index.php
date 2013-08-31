<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH .'/util/common/common.php');
    require_once(ROOT_PATH ."/util/mysql/dbutil.php");
  
    $status = getSiteStatus();
    if($status != 'Running'){
    	header("Location: downpage.php");
    }
	$singleBlogMode = false;
    if($_GET["blogId"] != null && $_GET["blogId"] != ""){
    	$currentBlogId = $_GET["blogId"];
    	$singleBlogMode = true;
    	$blog = getBlogById($currentBlogId);
    	$comments = loadAllComments($blog["blogid"], "blog");
    }else{
    	$currentBlogId = getCurrentBlogId();
    	$blogs = getBlogs($currentBlogId, 10);
    }
    
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <meta name="description" content="Brand New Online Comics Site">
  <meta name="keywords" content="Techmuffin, Online Comics, Comics, Tech, Funny, Hillarious, Humor, Funnies, Laugh, Comic, Techie, Joke, Jokes, Web Comic" >  
  <link rel="stylesheet" type="text/css" href="<?php echo HOST;?>css/mainStyle.css">
  <title><?php echo TITLE;?> - Blogs</title>
  <script type="text/javascript">
  	var host = "<?php echo HOST;?>";
  </script>
  <script type="text/javascript" src="<?php echo HOST;?>scripts/public_scripts.js" ></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.js"></script>
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
		
	    <div class="workspace">
	    	<div id="popup"></div>
	    	
	    	<?php
	    		if($singleBlogMode == false){
	    			if($blogs != null){
	    			
						foreach($blogs as $key => $blog){
				?>
						<div class="blog">
							<div class="blogContent">
								<?php echo $blog["content"];?>
							</div>
							<div class="blogPosted">
							<?php 
								$date = new DateTime($blog["created"]);
							?>
								Posted on: <?php echo $date->format('M d Y');?>
							</div>
							<div class="blogComments">
								<a href="<?php echo HOST;?>blogs?blogId=<?php echo $blog["blogid"];?>" style="color:Black">Comments</a>
							</div>
						</div>
			<?php 	
						}
					}
	    		}else{
			?>	
				<div class="blog">
					<div class="blogContent">
						<?php echo $blog["content"];?>
					</div>
					<div class="blogPosted">
					<?php 
						$date = new DateTime($blog["created"]);
					?>
						Posted on: <?php echo $date->format('M d Y');?>
					</div>
				</div>
			    <div id="comments" style="float:left;width:100%;">
				<p>
					<h2>Comments</h2>
					<ul>
						<li><a href="javascript:addCommentForm(<?php echo $blog["blogid"];?>, 'blog')">Add Comment</a></li>
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
			<?php }?>
	    </div>	
	    
		<?php include_once ROOT_PATH . "includes/footer.php";?>
	</div>
</body>
</html>