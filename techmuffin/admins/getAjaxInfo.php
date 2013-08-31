<?php
  	session_start();
    require_once('../config.php');
   	require_once(ROOT_PATH . 'util/common/common.php');
   	require_once(ROOT_PATH . "util/mysql/dbutil.php");
   	require_once("loginCheck.php");

	if(!isset($_GET["function"])){
		echo "";
	}else{
		$function = $_GET["function"];
		if( $function == "loadComicForm"){
			$comicId = $_GET["comicId"];
			$comic = loadComicById($comicId);
		?>
			<form action="updateComic.php" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="comicId" value="<?php print($comicId);?>"/>
				<p>
					<label for="comicName">Comic Name:</label>
					<input type="text" name="comicName" id="comicName" value="<?php print($comic["comicname"]);?>"/>
				</p>
				<p>
					<label for="comicTitle">Title:</label>
					<input type="text" name="comicTitle" id="comicTitle" value="<?php print($comic["title"]);?>"/>
				</p>
				<p>
					<label for="file">Filename:</label>
					<input type="file" name="file" id="file" />
				</p>
				<label for="adminPW">Admin Password</label>
				<input type="password" id="adminPW" name="adminPW" />
				<br/>
				<input type="submit" name="submit" value="Submit" />
			</form>
			<?php 
		}

		if( $function == "loadCommentsForm"){
			$comicId = $_GET["comicId"];
			$comments = loadAllComments($comicId, "comics");
		?>
			<form action="comments.php" method="post">
				<input type="hidden" name="comicId" value="<?php print($comicId);?>"/>
				<?php 
				    if(isset($comments)){
						echo "<table>";
						echo "<th><td>Comment ID</td><td>Name</td><td>Comment</td><td>Created</td></th>";
					
						foreach( $comments as $key => $value){ 
					    	echo "<tr><td>";
							echo "<input type=\"checkbox\" name=\"commentid_" . $value["commentid"] . "\" id=\"commentid_" . $value["commentid"] . "\" /></td>";
							echo "<td>" . $value["commentid"] . "</td><td>" . $value["name"] . "</td>";
							echo "<td>" . $value["comment"] . "</td><td>" . $value["created"] . "</td></tr>";
						}
					}
					echo "</table>";
				?>	
		
				<input type="submit" name="submit" value="Submit" />
			</form>
			<?php 
		}
		if( $function == "loadQuoteForm"){
			$quoteId = $_GET["quoteId"];
			$quote = loadQuoteById($quoteId);
		?>
			<form action="updateQuote.php" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="quoteId" value="<?php print($quoteId);?>"/>
				<label for="quoteTitle">Name:</label>
				<input type="text" name="quoteTitle" id="quoteTitle" value="<?php print($quote["name"]);?>"/>
				<br/>
				<label for="quoteDescription">Description:</label>
				<input type="text" name="quoteDescription" id="quoteDescription" value="<?php print($quote["description"]);?>"/>
				<br/>
				
				<label for="file">Filename:</label>
				<input type="file" name="file" id="file" />
				<br />
				<label for="adminPW">Admin Password</label>
				<input type="password" id="adminPW" name="adminPW" />
				<br/>
				<input type="submit" name="submit" value="Submit" />
			</form>
			<?php 
		}
		
			if( $function == "loadSubtitleForm"){
			$subtitleId = $_GET["subtitleId"];
			$subtitle = getSubtitleByID($subtitleId);
			
		?>
			<form action="updateSubtitle.php" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="subtitleId" value="<?php print($subtitleId);?>"/>
				<label for="phrase">Phrase:</label>
				<input type="text" name="phrase" id="phrase" value="<?php print($subtitle["phrase"]);?>"/>
				<br/>
				<label for="category">Category:</label>
				<input type="text" name="category" id="category" value="<?php print($subtitle["category"]);?>"/>
				<br/>
				<label for="adminPW">Admin Password</label>
				<input type="password" id="adminPW" name="adminPW" />
				<br/>
				<input type="submit" name="submit" value="Submit" />
			</form>
			<?php 
		}
		
		if( $function == "loadBlogForm"){
			$blogId = $_GET["blogId"]; 
			$blog = getBlogById($blogId);
			print $blog["content"];
		}
		
	}
	
?>