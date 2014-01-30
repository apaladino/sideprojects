<?php 
   	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	$userName = $_SESSION['UserName'];
    
   	$showForm = true;
   	$message = "";
   	$allowedTags='<p><strong><em><u><h1><h2><h3><h4><h5><h6><img>';
 	$allowedTags.='<li><ol><ul><span><div><br><ins><del>';  
    $title = "";
    $content = "";
    $adminPW = "";
    
 	// Should use some proper HTML filtering here.
  	if($_POST['content']!='') {
    	
  		$message = "<div id=\"messages\">";
		$error = "<div id=\"errors\">";
		$title = $_POST["blogTitle"];
  		$content = strip_tags(stripslashes($_POST['content']),$allowedTags);
  		$adminPW = $_POST['adminPW'];
  		
   		if(!authorizeAdmin($_SESSION["UserName"], $adminPW)){
   			$error .= "Invalid Admin PW.</div>";
   		}else{
   		$result = createBlog($title, $content);
	  		if($result == "SUCCESS"){
	  			$message .= "Blog '" . $title . "' has been created.</div>";
	  			$showForm = false;
	  		}else{
	  			$error .= $result . "</div>";
	  		}
   		}
	} 
  
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <link rel="stylesheet" type="text/css" href="../css/mainStyle.css">
  <script language="javascript" type="text/javascript" src="../scripts/tiny_mce/tiny_mce.js"></script>
  <script language="javascript" type="text/javascript">
		  tinyMCE.init({
		    theme : "advanced",
		    mode: "exact",
		    elements : "content",
		    theme_advanced_toolbar_location : "top",
		    theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
	        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",

	        height:"350px",
		    width:"700px"
		});
	</script>
  <title>TechMuffin! Administrator</title>
</head>
<body>
    	
	<!-- Center Panel -->
	<div id="centerPanel">
		
		<!-- Title Panel -->
		<div>
		  <span>
		  	<p><h2>TechMuffin! Administrator</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Add New Blog.</h3></p>
			<?php print $message; ?>
			<?php print $error; ?>
			<hr>
			<p>
			<?php 
				if($showForm){
			?>
			<form action="addBlog.php" method="post"
				enctype="multipart/form-data">
				<p>
					<label for="blogTitle">Blog Title:</label>
					<input type="text" name="blogTitle" id="blogTitle" size="100" value="<?php print $title;?>"/>
				</p>
				<p>				
					<textarea id="content" name="content" rows="15" cols="80"><?php echo $content;?></textarea>
				</p>
				<p>
					<label for="adminPW">Admin Password</label>
					<input type="password" id="adminPW" name="adminPW" />
				</p>
				<input type="submit" name="submit" value="Submit" />
			</form>
			<?php }else{
			    print "<div class=\"blogEntry\"> " . $content . "</div>";
			}?>
			</p>
		</div>	
</body>
</html>