<?php 
    
	// ###### common utility functions #####
	function is_empty(&$val) {
	  return empty($val) && $val !== "0";
	}

	function encrypt($val){
		$enc_val = md5(SALT_ONE);
		$enc_val .= md5($val);
		$enc_val .= md5(SALT_TWO);
		return $enc_val;		
	}
	
	function upload_quote_file(){
	if ((($_FILES["file"]["type"] == "image/gif")
		|| ($_FILES["file"]["type"] == "image/jpeg")
		|| ($_FILES["file"]["type"] == "image/pjpeg"))
		|| ($_FILES["file"]["type"] == "image/bmp")
		|| ($_FILES["file"]["type"] == "image/png")
		&& ($_FILES["file"]["size"] < 200000)){
		  if ($_FILES["file"]["error"] > 0){
		    echo "Return Code: " . $_FILES["file"]["error"] . "<br />";
		  }else{		
		    if (file_exists("../quotes/pics/" . $_FILES["file"]["name"])){
		      echo $_FILES["file"]["name"] . " already exists. ";
		      return "ERROR: " . $_FILES["file"]["name"] . " already exists.";
		    }else{
		      	move_uploaded_file($_FILES["file"]["tmp_name"],
		      	"../quotes/pics/" . $_FILES["file"]["name"]);
		        return "SUCCESS";
		      }
		    }
		  }
		else{
			echo "Invalid file";
			return "Invalid file";
		 }
	}
	
	function upload_comic_file(){
	if ((($_FILES["file"]["type"] == "image/gif")
		|| ($_FILES["file"]["type"] == "image/jpeg")
		|| ($_FILES["file"]["type"] == "image/pjpeg"))
		|| ($_FILES["file"]["type"] == "image/bmp")
		|| ($_FILES["file"]["type"] == "image/png")
		&& ($_FILES["file"]["size"] < 200000)){
		  if ($_FILES["file"]["error"] > 0){
		    echo "Return Code: " . $_FILES["file"]["error"] . "<br />";
		  }else{		
		    if (file_exists("../comics/" . $_FILES["file"]["name"])){
		      echo $_FILES["file"]["name"] . " already exists. ";
		      return false;
		    }else{
		      	move_uploaded_file($_FILES["file"]["tmp_name"],
		      	"../comics/" . $_FILES["file"]["name"]);
		        return true;
		      }
		    }
		  }
		else{
			echo "Invalid file";
			return false;
		 }
	}
	
	function deleteComics($comicIds, $deleteFiles){
		
		foreach( $comicIds as $key => $value){
			// load the comic to be deleted
			$comic = getComicById($value);
			
			// delete the comic in database
			$result = removeComicById($value);
			
			if( $result != "SUCCESS"){
				return $result;
			}
			
			// if deleteFiles is set, then remove the image file
			if($deleteFiles){
				$result = deleteComicFile($comic["location"]);
				if($result != "SUCCESS"){
					return $result;
				}
			}
		}
		return "SUCCESS";
	}
	
	function deleteComments($commentIds, $comicId){
		
		// build comma separated list of comment ids
		$comments = "";
		foreach( $commentIds as $key => $value){
			if($comments != ""){
				$comments .= ",";
			}
			$comments .= $value; 
		}

		// delete the comment in database
		return removeCommentsList($comments, $comicId);
	}

	function deleteQuotes($quoteIds, $deleteFiles){
		
		foreach( $quoteIds as $key => $value){

			// load the comic to be deleted
			$quotes = getQuoteById($value);

			// delete the comic in database
			$result = removeQuoteById($value);
			
			if( $result != "SUCCESS"){
				return $result;
			}
			
			// if deleteFiles is set, then remove the image file
			if($deleteFiles){
				$result = deleteQuoteFile($quotes["filename"]);
				if($result != "SUCCESS"){
					return $result;
				}
			}
		}
		return "SUCCESS";
	}
	
	function deletesubtitles($subtitleIds){
		
		foreach( $subtitleIds as $key => $value){
			
			// delete the subtitle in database
			$result = removeSubtitleById($value);
			
			if( $result != "SUCCESS"){
				return $result;
			}
		}

		return "SUCCESS";	
	}

	function deleteblogs($blogIds){
		foreach( $blogIds as $key => $value){
			$result = removeBlogById($value);
			
			if($result != "SUCCESS"){
				return $result;
			}
		}	
		return "SUCCESS";
	}
	
	function deleteComicFile($fileName){
		return deleteFile("comics", $fileName);
	}

	function deleteQuoteFile($fileName){
		return deletefile("quotes", $fileName);
	}
	
	/* deletes a file from the file($fileName) system directory ($dir) */
	function deleteFile($dir, $fileName){
		 if (file_exists("../".$dir."/" . $fileName)){
		 	if(unlink("../".$dir."/" . $fileName)){
		 		return "SUCCESS";
		 	}else{
		 		return "Unable to delete file: " . $fileName;
		 	}
		 }

		 return "Unable to delete file: " . $fileName; 
	}
	function update_comic($comicTitle, $comicFileName, $comicId){
		$comic = getComicById($comidId);
		$fileUpdated = ($comicFileName != "" && $comicFileName != $comic["location"]);
		$result = update_comic_data($comicTitle, $comicFileName, $comicId, $fileUpdated);

		if($result != "SUCCESS"){
			return $result;
		}
		
		if($fileUpdated){
			deleteComicFile($comic["location"]);
			upload_comic_file();	
		}
	}
	
	function getRandomSubtitle(){
		$subtitles = loadAllsubtitles();
		$numSubtitles = count($subtitles);
		$randIndex = rand(0, $numSubtitles - 1);
		return $subtitles[$randIndex];
	}
	
	function getRandomQuoteStatement($quoteId){
		$statements = getStatementsForQuote($quoteId);
		$numStatements = count($statements);
		$randIndex = rand(0, $numStatements - 1);
		return $statements[$randIndex];
	}
	
		
	/*
	 * Checks a string for any bad language or swear words, etc.
	 */
	function hasBadLanguage($str){
		$str = strtolower($str);
		
		global $swearWords;
		
		for($i = 0; $i < count($swearWords); $i++){
			if(strpos($str, $swearWords[$i]) != false && strpos($str, $swearWords[$i]) >= 0 ){
				return true;
			}
		}
		return false;
	}
	
	function filterXSS($text){
		$text = strip_tags($text);
		$text = htmlentities($text);
   		$text = htmlspecialchars($text, ENT_QUOTES);
   		return $text;	
	}
	
	function filterEmail($email){
		$email = filter_var($email, FILTER_SANITIZE_EMAIL);
		$email = filterText($email);
		$email = filterXSS($email);
		return $email;
	}
    
	function mailEventNotice($subject, $message){
		
 		$to = ADMIN_EMAIL;
 		$headers = "From: " . ADMIN_EMAIL . "\r\n" .
    	'Reply-To: ' . ADMIN_EMAIL . "\r\n" .
    	'X-Mailer: PHP/' . phpversion();


	 	if (mail($to, $subject, $message, $headers)) {
	   		return true;
	  	} else {
	   		return false;	
		}
	}
	
	function printObj($object){
		foreach($object as $key => $value){
			if(is_array($value)){
				echo "[<br/>";
				foreach($value as $key1 => $value1){
					echo $key1 . "=>" . $value1 . "<br/>";
				}
				echo "<br/>]";
			}else{
				echo $key . "=>" . $value . "<br/>";
			}
		}
	}
	
	function validateEmail($email){
		$result = validateField("Email address", $email, 50);
		if( $result != "VALID"){
			return $result;
		}else{
			if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
				return "Invalid Email address";
			}
		}
		
		return $result;
	}
	function validateField($field, $str, $len){
		
		if(strlen($str) == 0){
			return $field . " field is empty.";
		}
		if(strlen($str) > $len){
			return $field ." field must be less than " . $len . " characters";
		}
		
		if(hasBadLanguage($str)){
			return $field . " field must not contain any foul language";
		}
		
		return "VALID";
	}
	
	function notifySubscriptions($comicTitle, $comicId){
		$subscriptions = getAllSubscriptions();
		
		if($subscriptions != null){
			foreach($subscriptions as $key => $value){
				$email = $value["email"];
				sendSubscriptionEmail($email, $comicTitle, $comicId);
			}
		}
	}
	
	function sendSubscriptionEmail($email, $comicTitle, $comicId){
		$to = $email;
		$headers = "From: " . ADMIN_EMAIL . "\r\n" .
    	'Reply-To: ' . ADMIN_EMAIL . "\r\n" .
    	'X-Mailer: PHP/' . phpversion();
		$message = "Hello, there has been a new comic added to TechMuffin.com: \"";
		$message .= $comicTitle . "\"\r\r";
		$message .= "www.techmuffin.com?comicId=" . $comicId;
		$subject = "New Comic added to techmuffin.com!";

		return mail($to, $subject, $message, $headers);
	}
	
	function sendContactUsEmail($name, $email, $message){
		
		$to = ADMIN_EMAIL;
		$headers = "From: " . $email . "\r\n" .
    	'Reply-To: ' . $email . "\r\n" .
    	'X-Mailer: PHP/' . phpversion();
		$subject = "Contact us email from " . $name . " sent to techmuffin.com!";
        $content = "Name: " . $name . "\r----------\r" . $message;
        
		return mail($to, $subject, $content, $headers);
	}
	
	function deleteSubscriptions($subIds){
		$ids = "";
		$i = 0;
		foreach($subIds as $key => $value){
			if($i > 0){
				$ids .= ",";
			}
			$ids .= $value;
			$i = $i + 1;
		}
		return deleteSubscriptionList($ids);
	}

	function emailAFriend($yourEmail, $theirEmail, $currentComic){
		$url = "http://www.techmuffin.com?comicId=" . $currentComic["comicid"];
		$message = "A friend at " . $yourEmail . " has sent you a funny comic from TechMuffin.\r";
		$message .= "\r" . $url;
		
		$to = $theirEmail;
 		$headers = "From: " . $yourEmail . "\r\n" .
    	'Reply-To: ' . $yourEmail . "\r\n" .
    	'X-Mailer: PHP/' . phpversion();
		$subject = "Funny Comic sent to you from TechMuffin.com";
	
		if(SEND_EMAILS == false){
			return true;
		}
		
	 	if (mail($to, $subject, $message, $headers)) {
	   		return true;
	  	} else {
	   		return false;	
		}
	}

	function createBlog($title, $content){
		$title = filterText($title);
		$existingBlog = getBlogByTitle($title);
		if($existingBlog != null){
			return "Blog '" . $title . "' already exists. Unable to create duplicate blog.";
		}
		return addBlog($title, $content);
	}
	
?>