<?php
	session_start();
   	require_once('../config.php');
   	require_once("mysql/dbutil.php");
   	require_once('common/common.php');
   	
    if(isset($_POST["action"]) && $_POST["action"] != ""){
		
		$ownerId = filterXSS($_POST["ownerId"]);
		$name = filterXSS($_POST["name"]);
		$comment = filterXSS($_POST["comment"]);
		$type = filterXSS($_POST["type"]);
		
		$ipAddress = $_SERVER['REMOTE_ADDR'];
		$nameCheck = validateField("Name" , $name, 50);
		if($nameCheck != "VALID"){
			echo $nameCheck;
		}
		
		$commentCheck = validateField("Comment", $comment, 500);
		if($commentCheck != "VALID"){
			echo $commentCheck;
		}
		
		if($nameCheck == "VALID" && $commentCheck == "VALID"){
			$result = addComment($type, $ownerId, $name, $comment, $ipAddress);
			if($result){
				if(SEND_EMAILS){
					$message = "Comment for " . $type . ": " . $ownerId . "\r";
					$message .= "Name: " . $name . "\r";
					$message .= "Comment: " . $comment;
					mailEventNotice("TechMuffin: Comment Added", $message);
				}
				echo "Comment Added.";
			}else{
				echo "Unable to add comment at this time.";
			}	
		}
			
	}
	
	
?>