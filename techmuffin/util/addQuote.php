<?php
	session_start();
   	require_once('../config.php');
   	require_once("mysql/dbutil.php");
   	require_once('common/common.php');
   	
    if(isset($_POST["action"]) && $_POST["action"] != ""){
		
		$quoteId = filterXSS($_POST["quoteId"]);
		$name = filterXSS($_POST["name"]);
		$quote = filterXSS($_POST["quote"]);
		$ipAddress = filterXSS($_SERVER['REMOTE_ADDR']);

		$nameCheck = validateField("Name" , $name, 50);
		if($nameCheck != "VALID"){
			echo $nameCheck;
		}
		
		$quoteCheck = validateField("Quote", $quote, 500);
		if($quoteCheck != "VALID"){
			echo $quoteCheck;
		}

		if($nameCheck == "VALID" && $quoteCheck == "VALID"){
			$result = addQuote($quoteId, $quote, $name, $ipAddress);
			if($result){
				if(SEND_EMAILS){
					$message = "Quote phrase added for quote: " . $quoteId . "\rQuote Added:\r" . $quote;
					mailEventNotice("Quote phrase added."  , $message);
				}
				echo "Quote Added.";
			}else{
				echo "Unable to add quote at this time.";
			}	
		}
			
	}
	
	
?>