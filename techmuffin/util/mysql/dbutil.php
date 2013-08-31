<?php

	
	/*
	 * Attempts to pull the db connection from the session, and if not there, it will
	 * create a new db connection and store it in the session for next time.
	 */
    function getDBConnection(){
		$con = $_SESSION['dbCon'];
    	if($con == null){
    		$con = mysql_connect(DB_HOST,DB_USER,DB_PASS);
    		if (!$con){
	  			die('Could not connect: ' . mysql_error());
	  		}
	  		$_SESSION['dbCon'] = $con;
    	}
    	selectDB($con);
    	return $con;
	}
	
	function selectDB($db){
		mysql_select_db(DB_DATABASE, $db) or die ("Couldn't select the database.");
	}
	/*
	 * Validate admin username and password
	 */
	function authorizeAdmin($userName, $password){
		
		// filter sql injection
		$userName = filterText($userName);
		$password = filterText($password);
		
		$user = $userName;
		$pass = encrypt($password);
		
		//connect to the database
		$db = getDBConnection();
		
		$result=mysql_query("select * from admins where username='$user' AND password='$pass'", $db);
	
		$rowCheck = mysql_num_rows($result);
	
		if($rowCheck > 0){
			while($row = mysql_fetch_array($result)){
				return true;
			}
		}else {
	
			//if nothing is returned by the query, unsuccessful login code goes here...
			echo 'Incorrect login name or password. Please try again.';
			return false;
		}
	}
	
	function logInAdmin($userName, $password){
		
		if(authorizeAdmin($userName, $password)){
			// TODO: update their login time 
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * Looks up the current comic id, and returns the comic info for that comic id.
	 */
	function loadCurrentComic(){
		
		//connect to the database
		$db = getDBConnection();
		$currentComicNumber = getCurrentComicId($db);
		if(isset($currentComicNumber) && $currentComicNumber != null){
			return loadComicById($currentComicNumber, $db);
		}else{
			return null;
		}		
	}
	
	/*
	 * Looks up the current quote id, and returns the quote info for that quote id.
	 */
	function loadCurrentQuote(){
		// connect to the database
		$db = getDBConnection();
		$currentQuoteId = getCurrentQuoteId($db);
		if(isset($currentQuoteId) && $currentQuoteId != null){
			return loadQuoteById($currentQuoteId, $db);
		}else{
			return null;
		}
	}
	
	/*
	 * Gets min quote id
	 */
	function getMinQuoteId(){
		$db = getDBConnection();
		$sql = "select min(quoteid) from quoteimages";
		$result = mysql_query($sql, $db);

		if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return 1;	
	    }
	}
	
	/*
	 * Gets max quote id 
	 */
	function getMaxQuoteId(){
		$db = getDBConnection();
		$sql = "select max(quoteid) from quoteimages";
		$result = mysql_query($sql, $db);

		if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return 1;	
	    }
	}
	
	/*
	 * Looks up all of the inspirational statements for a given quoteId
	 */
	function getStatementsForQuote($quoteId){
		
		// filter sql injection
		$quoteId = filterInt($quoteId);
		
		$db = getDBConnection();
		$sql = "SELECT * from quotes where quoteid=" . $quoteId . " order by created desc";
		
		$result = mysql_query($sql, $db);
		$rowCheck =  mysql_num_rows($result);
		$statements = array();
		
		if( $result != false && $rowCheck > 0 ){
			$i = 0;
			while($i < $rowCheck){
				$statements[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $statements;
		}else{
			return null;
		}
	}
	
	/*
	 * Looks up a comic by comicid
	 */
	function loadComicById($comicId){
		
		// filter sql injection
		$comicId = filterInt($comicId);

		$db = getDBConnection();
		$result=mysql_query("select * from comics where comicid='". $comicId ."'", $db);
		
		$rowCheck = mysql_num_rows($result);
		if( $rowCheck > 0 ){
			return mysql_fetch_array($result);
		}else{
			return null;
		}
	}
	
	/*
	 * Looks up a quote by quoteId
	 */
	function loadQuoteById($quoteId){
		
		// filter sql injection
		$quoteId = filterInt($quoteId);
		
		$db = getDBConnection();
		$sql = "select * from quoteimages where quoteid=". $quoteId;
		$result=mysql_query($sql,$db);
		
		$rowCheck = mysql_num_rows($result);
		if( $rowCheck > 0 ){
			return mysql_fetch_array($result);
		}else{
			return null;
		}
	}
	
	/*
	 * Loads All comics into an array. This should be reworked to support pagination
	 * and filter queries
	 */
	function loadAllComics(){
		$db = getDBConnection();
		$result=mysql_query("select * from comics order by comicid", $db);
		$comics = array();
		
		$rowCheck = mysql_num_rows($result);
		
		if( $rowCheck > 0 ){
			$i = 0;
			while($i < $rowCheck){
				$comics[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $comics;
		}else{
			return null;
		}
	}
	
	/*
	 * Loads All Quotes into an array. This should be reworked to support pagination
	 * and filter queries
	 */
	function loadAllQuotes(){
		$db = getDBConnection();
		$result=mysql_query("select * from quoteimages order by quoteid", $db);
		$comics = array();
		
		$rowCheck = mysql_num_rows($result);
		
		if( $rowCheck > 0 ){
			$i = 0;
			while($i < $rowCheck){
				$comics[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $comics;
		}else{
			return null;
		}
	}
	
	/*
	 * Loads all subtitles into an array. This should be reworked to support pagination
	 * and filter queries.
	 */
	function loadAllsubtitles(){
		$db = getDBConnection();
		$result = mysql_query("select * from subtitles order by subtitleid", $db);
		
		$subtitles = array();
		
		$rowCheck = mysql_num_rows($result);
		if( $rowCheck > 0){
			$i = 0; 
			while($i < $rowCheck){
				$subtitles[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $subtitles;
		}
		
		return null;
	}
	
	/*
	 * Tries to look up the max comicid
	 */
	function getCurrentComicId(){
		$db = getDBConnection();
		
		$result=mysql_query("select max(comicid) from comics", $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return null;	
	    }
	}

	/*
	 * Looks up the min comicId
	 */
	function getMinComicId(){
		
		$db = getDBConnection();
		
		$result=mysql_query("select min(comicid) from comics", $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return 1;	
	    }
	}
	
	/*
	 * Tries to look up the max quoteid
	 */
	function getCurrentQuoteId(){
		$db = getDBConnection();
		
		$result=mysql_query("select max(quoteid) from quoteimages", $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return null;	
	    }	
	}
	
	/*
	 * Looks up the min quiteid
	 */
	function getMinCaptionId($quoteId){
		$db = getDBConnection();
		$sql = "select min(id) from quotes where quoteid=" . $quoteId;
		$result=mysql_query($sql, $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return 1;	
	    }
	}
	
	/*
	 * Looks up the min quiteid
	 */
	function getMaxCaptionId($quoteId){
		$db = getDBConnection();
		
		$result=mysql_query("select max(id) from quotes where quoteid=" . $quoteId, $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return 1;	
	    }
	}
	function add_comic_data($comicName, $comicTitle, $comicFileName, $comicId){
		
		// filter sql injection
		$comicName = filterText($comicName);
		$comicTitle = filterText($comicTitle);
		$comicFileName = filterText($comicFileName);
		$comicId = filterInt($comicId);
		
		$db = getDBConnection();
		$sql = "INSERT INTO comics (COMICID, COMICNAME, TITLE, LOCATION, CREATED) VALUES(";
		$sql .= $comicId . ",'" . $comicName . "','" . $comicTitle . "','" . $comicFileName . "', curdate() )";
		
		return mysql_query($sql, $db);
	}
	
	function add_quote_data($name, $description, $fileName){
		
		// filter sql injection
		$name = filterText($name);
		$description = filterText($description);
		$fileName = filterText($fileName);
		
		$db = getDBConnection();
		
		$sql = "INSERT INTO quoteimages (NAME, DESCRIPTION, FILENAME, ACTIVE, CREATED) VALUES('";
		$sql .= $name . "','" . $description . "','" . $fileName . "', 1, curdate() )";
		return mysql_query($sql, $db);	
	}
	
	function add_subtitle_data($phrase, $category){
		
		// filter sql injection
		$phrase = filterText($phrase);
		$category = filterText($category);
		
		$db = getDBConnection();
		
		$sql = "INSERT INTO subtitles (PHRASE, CATEGORY, CREATED) VALUES(";
		$sql .= "'" . $phrase . "', '" . $category . "', curdate()  )";
		return mysql_query($sql, $db);
	}
	
	function update_comic_data($comicName, $comicTitle, $comicFileName, $comicId, $fileUpdated){
		
		// filter sql injection
		$comicName = filterText($comicName);
		$comicTitle = filterText($comicTitle);
		$comicFileName = filterText($comicFileName);
		$comicId = filterInt($comicId);
		
		$db = getDBConnection();
		$sql = "Update comics set comicname='" . $comicName . "'";
		
		if(isset($comicTitle) && $comicTitle != ""){
			$sql .= ", title='" . $comicTitle . "'";
		}
		
		if(isset($fileUpdated) && $fileUpdated != "" && $comicFileName != ""){
			// the file name has been updated.
			$sql .= ", location='" . $comicFileName . "'";
		}
		$sql .= " where comicid=" . $comicId;
		return mysql_query($sql, $db);
	}
	
	function update_quote_data($quotename, $quoteDescription, $quoteFileName, $quoteId, $fileUpdated){
		
		// filter any sql injection
		$quotename = filterText($quotename);
		$quoteDescription = filterText($quoteDescription);
		$quoteFileName = filterText($quoteFileName);
		$quoteId = filterInt($quoteId);
		
		
		$db = getDBConnection();
		$sql = "Update quoteimages set name='" . $quotename . "'";
		
		if(isset($quoteDescription) && $quoteDescription != ""){
			$sql .= ", description='" . $quoteDescription . "'";
		}
		
		if(isset($fileUpdated) && $fileUpdated != "" && $quoteFileName != ""){
			// the file name has been updated.
			$sql .= ", filename='" . $quoteFileName . "'";
		}
		$sql .= " where quoteid=" . $quoteId;
		return mysql_query($sql, $db);
	}
	
	function update_subtitle_data($phrase, $category, $subtitleId){
		
		// filter sql injection
		$phrase = filterText($phrase);
		$category = filterText($category);
		$subtitleId = filterInt($subtitleId);
		
		$db = getDBConnection();
		$sql = "Update subtitles "; 
		$phraseUpdated = isset($phrase) && $phrase != "" ; 
		$categoryUpdated = isset($category) && $category != "";
		
		if($phraseUpdated){
			$sql .= "set phrase='" . $phrase . "'";
		}
		
		if($categoryUpdated){
			if($phraseUpdated){
				$sql .= ", ";
			}
			$sql .= "category='" . $category . "'";
		}
		
		$sql .= " where subtitleid = " . $subtitleId;
		return mysql_query($sql, $db);
	}
	
	function getComicById($comidId){
		
		// filter sql injection
		$comicId = filterInt($comicId);
		
		$db = getDBConnection();
		$sql = "select * from comics where comicid=" . $comidId;
		
		$result=mysql_query($sql, $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	return mysql_fetch_array($result, MYSQL_ASSOC);
	    }
	}
	
	function getQuoteById($quoteId){
		
		// filter sql injection
		$quoteId = filterInt($quoteId);
		
		$db = getDBConnection();
		$sql = "select * from quoteimages where quoteid=" . $quoteId;
		
		$result=mysql_query($sql, $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	return mysql_fetch_array($result, MYSQL_ASSOC);
	    }
	}
	
	function getSubtitleByPhrase($phrase){
		
		// filter sql injection
		$phrase = filterText($phrase);
		
		$db = getDBConnection();
		$sql = "select * from subtitles where phrase='" . $phrase . "'";
		$result = mysql_query($sql, $db);
		
		if( $result != null && $result != "" && isset($result) && mysql_num_rows($result) > 0 ){
			return mysql_fetch_array($result, MYSQL_ASSOC);
		}
		return null;
	}
	
	function getSubtitleByID($id){
		
		// filter sql injection
		$id = filterInt($id);
		
		$db = getDBConnection();
		$sql = "select * from subtitles where subtitleid=" . $id;
		$result = mysql_query($sql, $db);
		
		if( $result != null && $result != "" && isset($result) && mysql_num_rows($result) > 0 ){
			return mysql_fetch_array($result, MYSQL_ASSOC);
		}
		return null;
	}
	
	function removeComicById($comicId){
		
		// filter sql injection
		$comicId = filterInt($comicId);
		
		$db = getDBConnection();
		$sql = "DELETE FROM comics where comicid=".$comicId;
	    	
	   	if(mysql_query($sql, $db) && removeCommentsForObject($comicId, "comics")){
	   		return "SUCCESS";
	   	}else{
	   		return "Unable to remove comic entry: " . $comicId . " from database";
	   	}
	}
	
	function removeQuoteById($quoteId){
		
		// filter sql injection
		$quoteId = filterInt($quoteId);
		
		$db = getDBConnection();
		$sql = "DELETE FROM quoteimages where quoteid=" . $quoteId;
	   
	   	if(mysql_query($sql, $db) && removeQuoteEntries($quoteId)){
	   		return "SUCCESS";
	   	}else{
	   		return "Unable to remove quote entry: " . $quoteId . " from database";
	   	}
	}
	
	function removeSubtitleById($id){
		
		// filter sql injection
		$id = filterInt($id);
		
		$db = getDBConnection();
		$sql = "DELETE FROM subtitles where subtitleid=" . $id;

		if(mysql_query($sql, $db)){
	   		return "SUCCESS";
	   	}else{
	   		return "Unable to remove subtitle entry: " . $id . " from database";
	   	}
	}
	
	function removeBlogById($id){
		$id = filterInt($id);
		$db = getDBConnection();
		$sql = "DELETE FROM blogs where blogid=" . $id;
		
		if(mysql_query($sql, $db) && removeCommentsForObject($id, "blog")){
	   		return "SUCCESS";
	   	}else{
	   		return "Unable to remove blog entry: " . $id . " from database";
	   	}
	}
	
	function loadAllComments($id, $type){
		
		// filter sql injection
		$id = filterInt($id);
		$type = filterText($type);
		
		$db = getDBConnection();
		$sql = "SELECT * from comments where ownerid=" . $id . " and type='" . $type . "' order by commentid desc";
		$result = mysql_query($sql, $db);

		$comments = array();
		$rowCheck =  mysql_num_rows($result);
		if( $result != false && $rowCheck > 0 ){
			$i = 0;
			while($i < $rowCheck){
				$comments[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $comments;
		}else{
			return null;
		}
	}
	
	function addComment($ownerType, $ownerId, $name, $comment, $ipAddress){
		
		// filter sql injection
		$ownerType = filterText($ownerType);
		$ownerId = filterInt($ownerId);
		$name = filterText($name);
		$comment = filterText($comment);
		$ipAddress = filterText($ipAddress);
		
		$db = getDBConnection();
		$sql = "INSERT INTO comments (OWNERID, TYPE, COMMENT, NAME, IPADDRESS, CREATED) VALUES(";
		$sql .= "'" . $ownerId . "', '" . $ownerType . "', '" . $comment . "', '";
		$sql .= $name . "', '" . $ipAddress . "', curdate())";
		return mysql_query($sql, $db);
	}
	
	function addQuote($quoteId, $quote, $author, $ipAddress){
		
		// filter sql injection
		$quoteId = filterInt($quoteId);
		$quote = filterText($quote);
		$author = filterText($author);
		$ipAddress = filterText($ipAddress);
		
		$db = getDBConnection();
		$sql = "INSERT INTO quotes (QUOTEID, QUOTE, AUTHOR, IPADDRESS, CREATED) VALUES(";
		$sql .= $quoteId . ",'" . $quote . "', '" . $author . "', '";
		$sql .= $ipAddress . "', curdate())";
		return mysql_query($sql, $db);
	}
	
	function removeCommentsForObject($id, $type){
		$id = filterInt($id);
		
		$db = getDBConnection();
		$sql = "DELETE from comments where ownerid=" . $id . " and type='" . $type . "'";
		return mysql_query($sql, $db);
	}
	
	function removeCommentsList($comments, $comicId){
		$comicId = filterInt($comicId);
		
		$db = getDBConnection();
		$sql = "DELETE from comments where commentid in (" . $comments . ") and ownerid = " . $comicId;
		return mysql_query($sql, $db);
		
	}
	
	function removeQuoteEntries($quoteId){
		$quoteId = filterInt($quoteId);
		
		$db = getDBConnection();
		$sql = "DELETE from quotes where quoteid=" . $quoteId;
		return mysql_query($sql, $db);
	}

	/* SQL Injection filter methods */
	function filterInt($val){
		return filter_var($val, FILTER_SANITIZE_NUMBER_INT);	
	}
	
	function filterText($text){
		
		global $invalidChars;
		global $invalidWords;
		
   		$text = strip_tags($text);
   		$text = filter_var($text, FILTER_SANITIZE_MAGIC_QUOTES);
   		
   		for($i = 0; $i < count($invalidChars); $i++){
   			$text = str_replace($invalidChars[$i], "", $text);
   		}
   		
   		for($i = 0; $i < count($invalidWords); $i++){
   			$text = str_replace($invalidWords[$i], "", $text);
   		}
   		
   		return $text;
   }
   
   
   /*
    * Looks up the site status will return 'RUNNING' if site is up, 'DOWN' otherwise
    */
   function getSiteStatus(){
   	   $db = getDBConnection();

   	   $sql = "select value from settings where name='status'";
   	   $result = mysql_query($sql, $db);
		
   	   if( $result != null && $result != "" && isset($result) && mysql_num_rows($result) > 0 ){
   	   		$row = mysql_fetch_array($result);
	    	return $row[0];	
	   }
	    
	   return 'Down';
   }
   
   function getSettingByName($name){
       $db = getDBConnection();

   	   $sql = "select value from settings where name='" . $name . "'";
   	   $result = mysql_query($sql, $db);
		
   	   if( $result != null && $result != "" && isset($result) && mysql_num_rows($result) > 0 ){
   	   	   return mysql_fetch_array($result, MYSQL_ASSOC);
	   }
	    
	   return null;
   }
   
   /*
    * updates the site settings
    */
   function updateSiteSettings($field, $value){
   	   $db = getDBConnection();
   	   $field = filterText($field);
   	   $value = filterText($value);
   	   
   	   $setting = getSettingByName($field);
   	   if($setting != null){
   	   		// setting exists
   	   		$sql = "update settings set value='" . $value . "' where name='" . $field . "'";
   	   }else{
   	   		// setting doesn't exist
   	   		$sql = "insert into settings (NAME,VALUE) values('".$field."','".$value."')";
   	   }
   	   return mysql_query($sql, $db);
   }
   
   function getCaptionById($captionId){
   		$db = getDBConnection();
   		$captionId = filterInt($captionId);
   		
   		$sql = "select * from quotes where id=" . $captionId;
		$result = mysql_query($sql, $db);
		
		if( $result != null && $result != "" && isset($result) && mysql_num_rows($result) > 0 ){
			return mysql_fetch_array($result, MYSQL_ASSOC);
		}
		return null;   		
   }
   
   function getUserSubscription($email){
   	    $email = filterEmail($email);
   		$db = getDBConnection();
   		$sql = "select * from subscriptions where email='" . $email . "'";
   		
   		$result = mysql_query($sql, $db);
   		if( $result != null && $result != "" && isset($result) && mysql_num_rows($result) > 0 ){
			return mysql_fetch_array($result, MYSQL_ASSOC);
		}
		return null; 
   }
    
   function getAllSubscriptions(){
   		$db = getDBConnection();
		$result=mysql_query("select * from subscriptions where active=1", $db);
		$emails = array();
		
		$rowCheck = mysql_num_rows($result);
		
		if( $rowCheck > 0 ){
			$i = 0;
			while($i < $rowCheck){
				$emails[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $emails;
		}else{
			return null;
		}
   }
   
   function addUserEmailToSubscriptions($email){
   		$db = getDBConnection();
   		$subscription = getUserSubscription($email);
   		if($subscription != null){
   			return "Email: " . $email . " is already subscribed.";
   		}
   		
   		$sql = "insert into subscriptions (EMAIL, CREATED, ACTIVE) VALUES('";
   		$sql .= $email . "', curdate(), true)";
   		return mysql_query($sql, $db);
    }

   function deleteSubscriptionList($ids){
   		$db = getDBConnection();
   		$sql = "update subscriptions set active=0 where subscriptionid in(" . $ids .")";
		return mysql_query($sql, $db);
   }
   
   function addBlog($title, $content){
   		$db = getDBConnection();
   		$sql = "insert into blogs (title, created, content) VALUES('";
   		$sql .= $title . "', curdate(), '" . $content . "')";
   		return mysql_query($sql, $db);	
   	
   }
   
   function getBlogByTitle($title){
       $db = getDBConnection();
       $sql = "select * from blogs where title='" . $title ."'";
       $result = mysql_query($sql, $db);
       if( $result != null && $result != "" && isset($result) && mysql_num_rows($result) > 0 ){
       		return mysql_fetch_array($result, MYSQL_ASSOC);
	   }
	   return null;  
   }
   
   /*
	 * Loads All blogs into an array. This should be reworked to support pagination
	 * and filter queries
	 */
	function loadAllBlogs(){
		$db = getDBConnection();
		$result=mysql_query("select * from blogs order by blogid", $db);
		$comics = array();
		
		$rowCheck = mysql_num_rows($result);
		
		if( $rowCheck > 0 ){
			$i = 0;
			while($i < $rowCheck){
				$comics[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $comics;
		}else{
			return null;
		}
	}
	
	function getBlogById($blogId){
		$db = getDBConnection();
		
		// filter sql injection
		$blogIg = filterInt($blogId);
		
		$db = getDBConnection();
		$sql = "select * from blogs where blogid=" . $blogId;
		
		$result=mysql_query($sql, $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	return mysql_fetch_array($result, MYSQL_ASSOC);
	    }
	}
	
	function updateBlog($id, $title, $content){
		$db = getDBConnection();

		$id = filterInt($id);
		$title = filterText($title);
		
		$sql = "UPDATE blogs set TITLE = '" . $title ."', CONTENT = '" . $content . "' ";
		$sql .= "where blogid=" . $id;
		
		return mysql_query($sql, $db);		
	}
	
	/*
	 * Tries to look up the max blogid
	 */
	function getCurrentBlogId(){
		$db = getDBConnection();
		
		$result=mysql_query("select max(blogid) from blogs", $db);
	    if($result == null || $result == "" || !$result || !isset($result)){
	    	return null;
	    }
		$rowCheck = mysql_num_rows($result);
	    if( $rowCheck > 0){
	    	$row = mysql_fetch_array($result);
	    	return $row[0];	
	    }else{
	    	return null;	
	    }
	}
	
	/*
	 * Gets list of blogs 
	 */
	function getBlogs($id, $limit){
		$db = getDBConnection();
		$sql = "select * from blogs where blogid <= " . $id . " LIMIT 0," . $limit;
		$result = mysql_query($sql, $db);
		if($result == false){
			return null;
		}
		$rowCheck = mysql_num_rows($result);
		$blogs = array();
		
		if( $rowCheck > 0 ){
			$i = 0;
			while($i < $rowCheck){
				$blogs[$i] = mysql_fetch_array($result, MYSQL_ASSOC);
				$i++;
			}
			return $blogs;
		}else{
			return null;
		}
	}
?>