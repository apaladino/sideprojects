<?php
	session_start();
   	require_once('../config.php');
   	require_once("mysql/dbutil.php");
   	require_once('common/common.php');
   	
    if(isset($_POST["action"]) && $_POST["action"] != ""){
		
		$yourEmail = filterXSS($_POST["yourEmail"]);
		$theirEmail = filterXSS($_POST["theirEmail"]);
		$message = "<div id=\"messages\">";
		$error = "<div id=\"errors\">";
		
		$emailCheck = validateEmail($yourEmail);
		if($emailCheck != "VALID"){
			echo $error . $emailCheck . "</div>";
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
		}else{
			$emailCheck = validateEmail($theirEmail);
			if($emailCheck != "VALID"){
				echo $error . $emailCheck . "</div>";
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
			}else{
				// everything checks out so send out the email
				$currentComic = loadCurrentComic();
				if(emailAFriend($yourEmail, $theirEmail, $currentComic)){
					echo $message .= "Comic successfully emailed to: " . $theirEmail . ".</div>";
				}else{
					echo $error . "Unable to send email at this time." . "</div>";
				}
			}
		}
		
    }
		
	
	
?>