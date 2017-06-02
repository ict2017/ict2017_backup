<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<?php
$dbuser = 'postgres';
$dbpass = 'a';
$host = 'localhost';
$dbname = 'futhead';
$dbh = new PDO("pgsql:host=$host;dbname=$dbname", $dbuser, $dbpass);

if($dbh == false){
    die('Can\'t Connect to database: ' . $dbh->errorCode() . "<br/>");
//} else {
//    echo "<p>Kết nối thành công</p>";
}
?>
</body>
</html>