<?php
	session_start();
   	require_once('../config.php');
   	require_once("mysql/dbutil.php");
   	require_once('common/common.php');
   	
   	//printObj($_POST);   	exit;
	if($_POST["action"] != null && $_POST["action"] == "subscribeUser"){
		$message = "<div id=\"messages\">";
		$error = "<div id=\"errors\">";
		
		$email = $_POST["email"];
		$email = filterEmail($email);
		$result = validateField("Email Address", $email, 50);
		
		if($result != "VALID"){
			return $result;
		}
		
		if(addUserEmailToSubscriptions($email)){
			echo $message .= "Email: " . $email . " has been subscribed. You will now receive emails when I post a new comic.</div>";
		}else{
			echo $error .= "Thank you for subscribing. You will now receive emails when I post a new comic.</div>";
		}
	}

?>