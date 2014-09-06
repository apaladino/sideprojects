<?php
  	session_start();
   	require_once('../config.php');
   	require_once("mysql/dbutil.php");
   	require_once('common/common.php');
   
   	if(!isset($_GET["function"])){
		echo "-";
	}else{
		$function = $_GET["function"];
		
		if($function == "addCommentForm"){
			$ownerId = $_GET["ownerId"];
			$error = $_GET["error"];
			$type = $_GET["type"];
			
			if(isset($error) && $error != ""){
				?>
					<div class="error">
						<h3><?php echo $error;?></h3>
					</div>
				<?php 	
			}
			
		?>
			<form >
				<input type="hidden" name="field_action" id="field_action" value="addComment"/>
				<input type="hidden" name="field_ownerId" id="field_ownerId" value="<?php echo $ownerId;?>"/>
				<input type="hidden" name="field_type" id="field_type" value="<?php echo $type; ?>"/>
				<label for="field_name">Your Name:</label>
				<input type="text" name="field_name" id="field_name"/>
				<br/>
				<label for="field_comment">Comment:</label>
				<TEXTAREA name="field_comment" id="field_comment" rows="10" cols="30">Enter your comment here.</TEXTAREA>
				<br/>
				<input type="button" name="submit" value="Submit" onclick="javascript:ajaxPost()"/>				
			</form>
		
		<?php 	
		}
		
		if($function == "addComment"){
			$comicId = $_POST["comicId"];
			$name = $_POST["name"];
			$comment = $_POST["comment"];
			echo($comicId . "," . $name . "," . $comment);	
		}
		
		if($function == "loadComments"){
			$comicId = $_GET["comicId"];
			$comments = array();
			$comments = loadAllComments($comicId, "comics");

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
		}
		
	if($function == "getCaptionById"){
		$captionId = $_GET["captionId"];
        $minCaptionId = $_GET["mn"];
        $maxCaptionid = $_GET["mx"];
		$caption = getCaptionById($captionId);
		$randStatement = $caption["quote"];
	   ?>
		<h5>
			<?php if($randStatement != ""){ 
				echo "\"" . $randStatement . "\"";
			}?>
		</h5>
		<div class="arrows">
			<?php 
    			if($captionId > $minCaptionId){
    				$targetCaptionId = $captionId - 1;	
    				$params = HOST . ",'" . $targetCaptionId . "'";
    				$params .= ",'" . $minCaptionId . "'";
    				$params .= ",'" . $maxCaptionid . "'";
    		?>
    		<a href="javascript:getCaption(<?php echo "$params";?>)">
    			<img src="images/leftarrow_white.png"/>
    		</a>
    		<?php }
    			if($captionId < $maxCaptionid){
    			    $targetCaptionId = $captionId + 1;
    			    $params = HOST . ",'" . $targetCaptionId . "'";
    				$params .= ",'" . $minCaptionId . "'";
    				$params .= ",'" . $maxCaptionid . "'";
    		?>
    		<a href="javascript:getCaption(<?php echo "$params";?>)">
    			<img src="images/rightarrow_white.png"/>
    		</a>
    		<?php }?>
		</div>
	<?php 
	}
	
	if($function == "addQuoteForm"){
			$quoteId = $_GET["quoteId"];
			$error = $_GET["error"];
			if(isset($error) && $error != ""){
				?>
					<div class="error">
						<h3><?php echo $error;?></h3>
					</div>
				<?php 	
			}
			
		?>
		
			<form >
				<input type="hidden" name="field_action" id="field_action" value="addQuote"/>
				<input type="hidden" name="field_quoteId" id="field_quoteId" value="<?php print($quoteId); ?>"/>
				<label for="field_name">Your Name:</label>
				<input type="text" name="field_name" id="field_name"/>
				<br/>
				<label for="field_quote">Quote:</label>
				<TEXTAREA name="field_quote" id="field_quote" rows="10" cols="30">Enter your quote here.</TEXTAREA>
				<br/>
				<input type="button" name="submit" value="Submit" onclick="javascript:quoteAjaxPost()"/>				
			</form>
		
		<?php 	
		}
		
		if($function == "emailFriendForm"){
		?>
			<script language="javascript">
				function onKeyPress(e){
					var keycode;
					if (window.event) keycode = window.event.keyCode;
					else if (e) keycode = e.which;
					else return false;
					return (keycode == 13);
				}
			</script>
			<h3>Send today's comic to a friend.</h3>
			<form>
				<p>
					<input type="hidden" name="action" value="emailToFriend"/>
					<label for="yourEmail">Your Email:</label>
					<input type="text" name="yourEmail" id="yourEmail"/>
				</p>
				<p>
					<label for="theirEmail">Their Email:</label>
					<input type="text" name="theirEmail" id="theirEmail"/>
				</p>
				<input type="button" value="Submit" onclick="javascript:emailToFriend()"/>
			</form>
		<?php 	
		}
		
		if($function == "showSubscribeForm"){
		?>
				<script language="javascript">
						function onKeyPress(e){
							var keycode;
							if (window.event) keycode = window.event.keyCode;
							else if (e) keycode = e.which;
							else return false;
							return (keycode == 13);
						}
				</script>
			    <form>
			    	<input type="hidden" name="action" value="subcribe" />
			    	<p><h3>Please enter your email address below:</h3></p>
			    	<p>
			    		<label for="email">Email:</label>
			    		<input type="text" name="email" id="email""/>
			    	</p>
			    	<input type="button" value="Submit" onclick="javascript:subscribe()"/>
			    </form>	
				
		<?php
		}

		if($function == "showContactUsForm"){
		?> 
			<form>
				<input type="hidden" name="action" value="contactUs"/>
				<p><h3>Send us an email</h3></p>
				<p>
					<span style="width:50%"><label for="name">Your Name:</label></span>
					<input type="text" id="name" size="25" />
				</p>
				<p>
					<label for="email">Your Email Address:</label>
					<input type="text" id="email" size="25"/>
				</p>
				<p>
					<label for="message">Message:</label>
					<textArea id="message" rows="12" cols="35"></textArea>
				</p>			
				<input type="button" value="Send" onclick="javascript:sendContactMessage()"/>
			</form>
		<?php 
		}
	}
?>