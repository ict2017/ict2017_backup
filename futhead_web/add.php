<html>
<head>
    <title>Add New Player</title>
    <meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="index.css" />
</head>
<body>
	<div id="big_wrapper">
		<header id="top_header">
			<h1>FIFA Player Database</h1>
		</header>
 
<nav id="top_menu" >
  <ul>
	<li><a href=index.php>Home</a></li>
	<li><a href=add.php>Add New Player</a></li>
	<li><a href=addteam.php>Add New Team</a></li>
	<li><a href=position.php>Position</a></li>
	<li><a href=team.php>Team</a></li>
			        <div id= "search_bar" align= "right">
            <form action="search.php" method="get">
                Search by Name: <input type="text" name="search" />
                <input type="submit" name="ok" value="search" />
            </form>
        </div>
  </ul>
</nav>
<h2>Add New Player</h2>
<?php
    require('connect_db.php');
	$nRows = $dbh->query('select count(*) from player')->fetchColumn();
	$id = $nRows+1;
?>

<form method="POST" action="store.php">
    <p>
        <label for="id"><b>ID: </label>
		<?php echo $id ;?> </b>
        <input id="id" name="id" type="hidden" value="<?php echo $id ;?>">
    </p>
    <p>
        <label for="name"><b>Name: </b></label>
        <input id="name" name="name" type="text" value="" required="true">
    </p>
    <p>
        <label for="position"><b>Position: </b></label>
        <select id="position" name="position">
		<option value="GK">GK</option>
		<option value="CB">CB</option>
		<option value="LB">LB</option>
		<option value="RB">RB</option>
		<option value="LWB">LWB</option>
		<option value="RWB">RWB</option>
		<option value="CM">CM</option>
		<option value="CDM">CDM</option>
		<option value="LM">LM</option>
		<option value="RM">RM</option>
		<option value="CAM">CAM</option>
		<option value="CF">CF</option>
		<option value="ST">ST</option>
		<option value="LW">LW</option>
		<option value="RW">RW</option>
		<option value="LF">LF</option>
		<option value="RF">RF</option>
</select>
    </p>    
	<p>
        <label for="nation"><b>Nation: </b></label>
        <input id="nation" name="nation" type="text" value="" required="true">
    </p>
	<p>
        <label for="teamid"><b>Team ID: </b></label>
        <select id="teamid" name="teamid">
		<?php
		$sql = 'SELECT teamid FROM team';
		foreach ($dbh->query($sql) as $row) { 

echo "<option value='" . $row['teamid'] . "'>" . $row['teamid'] . "</option>";
}
?>
</select>
    </p>
	<p>
        <label for="squadnum"><b>Squad Number: </b></label>
        <input id="squadnum" name="squadnum" type="text" value="" required="true">
    </p>
    <p>
        <label for="ovr"><b>Overall: </b></label>
        <input id="ovr" name="ovr" type="text" value="" required="true">
    </p>
    <p>
        <input type="submit" value="Submit" onclick="return confirm('Confirm?');">
    </p>
</form>

</body>
</html>