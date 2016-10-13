 <?php 
$link = mysql_connect('heavenswirecom.ipagemysql.com', 'andy', 'andy2pal'); 
if (!$link) { 
    die('Could not connect: ' . mysql_error()); 
} 
echo 'Connected successfully'; 
mysql_select_db(hwire); 
?> 