<?php
  /* Check to see if the user has a valid session, and if not, then log them out
   * and redirect them to the login page.
   */
   require_once('../config.php');
   require_once(ROOT_PATH . 'util/common/common.php');
   
				
   // check to see if the admin is logged in
   if( empty($_SESSION['UserName'])){
   	   
   	  // log_out();
      header("Location: login.php");
   }
?>