<?php
require('connect_db.php');
if ($_SERVER['REQUEST_METHOD'] != 'POST')
    die('Invalid HTTP method!');

$id = $_POST['id'];
$name = $_POST['name'];
$position = $_POST['position'];
$nation = $_POST['nation'];
$ovr = $_POST['ovr'];
$oldteamid = $_POST['oldteamid'];
$teamid = $_POST['teamid'];
$squadnum = $_POST['squadnum'];

$tname = $_POST['tname'];
$league = $_POST['league'];

$sql = "UPDATE player SET name = '$name', position = '$position', nation = '$nation', ovr = '$ovr' WHERE id = '$id' ;
UPDATE playfor SET name = '$name', teamid = '$teamid', squadnum = '$squadnum' WHERE id = '$id' ";

$sql2 = "UPDATE team SET teamid = '$teamid', league = '$league', \"team name\" = '$tname' WHERE teamid = '$oldteamid' ;
UPDATE playfor SET teamid = '$teamid' WHERE teamid = '$oldteamid' ";
if(isset($id)){
	$dbh->exec($sql);
	header("Location: index.php");
}else{
	$dbh->exec($sql2);
	header("Location: team.php");
}
?>