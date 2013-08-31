<?php
	session_start();
   	require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");
   	
   	$message = "";
	$error = "";   	
	$blogId = "";
	$title = "";
	$content = "";
	
	if($_POST['content']!='') {
	    	
	  	$message = "<div id=\"messages\">";
		$error = "<div id=\"errors\">";
		$title = $_POST["blogTitle"];
		$blogId = $_POST["blogId"];
	  	$content = $_POST['content'];
	  	$adminPW = $_POST['adminPW'];
	  	
	   	if(!authorizeAdmin($_SESSION["UserName"], $adminPW)){
	   		$error .= "Invalid Admin PW.</div>";
	   	}else{
			if(updateBlog($blogId, $title, $content)){
				$message .= "Blog '" . $title . "' has successfully been updated.</div>";
			}else{
				$error .= "Unable to update blog: " . $result . "</div>";
			}
	   	}
	}
	
	$blogs = loadAllBlogs();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  <script language="javascript"><!--

  function loadBlogForm(){
  	  	var blogselect = document.getElementById("blogsToUpdate");
  	  	var w = blogselect.selectedIndex;
  		var selected_text = blogselect.options[w].text;
		document.getElementById("blogTitle").value=selected_text;
		document.getElementById("blogId").value=blogselect.value;
		loadBlog(blogselect.value);
  	}

  	function loadBlog(blogId)
  	{
  	  	if (blogId.length==0){
	  	  document.getElementById("blogInfo").innerHTML="?";
	  	  return;
	  	}
	  	if (window.XMLHttpRequest){
		  	// code for IE7+, Firefox, Chrome, Opera, Safari
	  	  	xmlhttp=new XMLHttpRequest();
	  	}else{
		  	// code for IE6, IE5
	  	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  	}

	  	xmlhttp.onreadystatechange=function(){
	  		if (xmlhttp.readyState==4 && xmlhttp.status==200){
	  			var ed = tinyMCE.get('content');
	  	        ed.setProgressState(1); // Show progress

	  	        ed.setProgressState(0); // Hide progress
	  	        ed.setContent(xmlhttp.responseText);
	  	    }
	  	  }
	  	  
	  	xmlhttp.open("GET","getAjaxInfo.php?function=loadBlogForm&blogId="+blogId,true);
	  	xmlhttp.send();
  	}
  </script>
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
		  	<p><h2>Update a Blog</h2></p>
		  </span>
		</div>
		
		<?php include("includes/adminNav.php");?>

		<div class="workspace" >
			<p><h3>Update Blog.</h3></p>
			<p>
				<?php print $message;?>
				<?php print $error; ?>
			<hr>
			<p>
			
			<?php 
				if(isset($blogs)){  
			?>
					<input type="hidden" id="action" name="action" value="update"/>				
					<select id="blogsToUpdate" name="blogsToUpdate" onchange="javascript:loadBlogForm()">

					<?php 
						foreach( $blogs as $key => $value){ 
					    	echo "<option value=\"" . $value["blogid"] . "\">" . $value["title"] . "</option>";
				   		} 
					?> 
					</select> 
			
				    <div id="blogInfo">
		  
			<form  action="updateBlog.php" method="POST">
				<input type="hidden" name="blogId" id="blogId" value="<?php echo $blogId;?>"/>
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
				<input type="submit" name="Save" value="Save"/>
			</form>
				    </div>
			<?php 
				}
			?>
			</p>
		</div>	
</body>
</html>