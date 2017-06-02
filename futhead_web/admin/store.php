<?php
require('connect_db.php');

if ($_SERVER['REQUEST_METHOD'] != 'POST')
    die('Invalid HTTP method!');


$id = $_POST['id'];
$name = $_POST['name'];
$position = $_POST['position'];
$nation = $_POST['nation'];
$ovr = $_POST['ovr'];
$teamid = $_POST['teamid'];
$squadnum = $_POST['squadnum'];

$tname = $_POST['tname'];
$league = $_POST['league'];

$sql = "INSERT INTO player(id, name, position, nation, ovr) VALUES('$id', '$name', '$position', '$nation', '$ovr') ; 
INSERT INTO playfor(id, name, teamid, squadnum) VALUES('$id', '$name', '$teamid', '$squadnum') ";

$sql2 = "INSERT INTO team VALUES('$teamid', '$tname', '$league')";

if(isset($id)){
	$dbh->exec($sql);
	header("Location: index.php");
}else{
	$dbh->exec($sql2);
	header("Location: team.php");
}
	
?>
