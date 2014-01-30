<?php 

define( 'ROOT_PATH', dirname( __FILE__ ) ."/" );
define(APP_NAME, "techmuffin");
//define(HOST, "www.techmuffin.com");
define( HOST, "http://localhost/techmuffin/");
define(TITLE,"TechMuffin!");
define(ADMIN_EMAIL, "webmaster@techmuffin.com");
define(ROOT_PATH, $_SERVER['DOCUMENT_ROOT'] . '/' . APP_NAME );


define(SEND_EMAILS, false);

// db settings
define(DB_DATABASE, APP_NAME);
/*define(DB_USER, "muffinman");
define(DB_PASS, "andy2pal");
define(DB_HOST, "techmuffincom.ipagemysql.com");
*/define(DB_HOST, "localhost");
define(DB_USER, "root");
define(DB_PASS, "");

// encryption settings 
define(SALT_ONE, "someRandomstuffforsaltone");
define(SALT_TWO, "someOtherRandomStuffforsalttwo");
define(SUPER_PW, "4c74d5ae571aba5b07845b553157fff598f8fd4fca17aca454e446232713b1a671fa1e7b039b448922b6f6ba7fad613d");


$invalidChars = array(";", "*" );
$invalidWords = array("drop ", "delete ");
$swearWords = array(" bad "," word ", " list ");		
?>
