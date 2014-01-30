<?php
	session_start();
   	require_once('../config.php');
   	require_once("mysql/dbutil.php");
   	require_once('common/common.php');
   	
    if(isset($_POST["action"]) && $_POST["action"] != ""){
		
		$name = filterXSS($_POST["name"]);
		$email = filterXSS($_POST["email"]);
		$emailMessage = filterXSS($_POST["message"]);
		
		$message = "<div id=\"messages\">";
		$error = "<div id=\"errors\">";
		
		$emailCheck = validateEmail($email);
		
		if($emailCheck != "VALID"){
			echo $error . $emailCheck . "</div>";
			?>
			<form>
				<input type="hidden" name="action" value="contactUs"/>
				<p><h3>Send us an email</h3></p>
				<p>
					<span style="width:50%"><label for="name">Your Name:</label></span>
					<input type="text" name="name" size="25" />
				</p>
				<p>
					<label for="email">Your Email Address:</label>
					<input type="text" name="email" size="25"/>
				</p>
				<p>
					<label for="message">Message:</label>
					<textArea name="message" rows="12" cols="35"></textArea>
				</p>			
				<input type="button" value="Send" onclick="javascript:sendContactMessage()"/>
			</form>
			
			<?php 
		}else{
			$emailCheck = validateEmail($email);
			if($emailCheck != "VALID"){
				echo $error . $emailCheck . "</div>";
				?>
				<form>
					<input type="hidden" name="action" value="contactUs"/>
					<p><h3>Send us an email</h3></p>
					<p>
						<span style="width:50%"><label for="name">Your Name:</label></span>
						<input type="text" name="name" size="25" />
					</p>
					<p>
						<label for="email">Your Email Address:</label>
						<input type="text" name="email" size="25"/>
					</p>
					<p>
						<label for="message">Message:</label>
						<textArea name="message" rows="12" cols="35"></textArea>
					</p>			
					<input type="button" value="Send" onclick="javascript:sendContactMessage()"/>
				</form>
				<?php
			}else{
				
				$nameCheck = filterText($name);
				$emailMessage = filterText($emailMessage);

				if(SEND_EMAILS == false){
					echo $message .= "Your email has been sent.</div>";	
				}else{
					if(sendContactUsEmail($name, $email, $emailMessage)){
						echo $message .= "Your email has been sent.</div>";
					}else{
						echo $error . "Unable to send email at this time." . "</div>";
					}		
				}
			}
		}
		
    }
		
	
	
?>