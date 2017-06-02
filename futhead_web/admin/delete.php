<?php
require('connect_db.php');

if ($_SERVER['REQUEST_METHOD'] != 'GET')
    die('Invalid HTTP method!');

$id = $_GET['id'];
$teamid = $_GET['teamid'];
if (isset($id)){
$sql = "DELETE FROM player WHERE id='".$id."' ; DELETE FROM playfor WHERE id='".$id."'";
$dbh->exec($sql); //no return
header("Location: index.php");
}else{
$sql = "DELETE FROM team WHERE teamid='".$teamid."'";
$dbh->exec($sql); //no return
header("Location: team.php");
}
?>
