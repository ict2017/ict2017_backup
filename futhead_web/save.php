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

$sql = "UPDATE player SET name = '$name', position = '$position', nation = '$nation', ovr = '$ovr' WHERE id = '$id' ;
UPDATE playfor SET name = '$name', teamid = '$teamid', squadnum = '$squadnum' WHERE id = '$id' ";
$dbh->exec($sql);
header("Location: index.php");

?>