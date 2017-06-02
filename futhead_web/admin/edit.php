<html>
<head>
    <title>Edit Player Information</title>
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
<h2>Edit Player Information</h2>

<?php
    require('connect_db.php');

    if (isset($_GET['id']))
        $id = $_GET['id'];
    else
        die('Missing Player ID');
	
	$sql = "SELECT * FROM player, playfor where player.id = $id AND player.id = playfor.id ";

	$result = $dbh->prepare($sql);
	$result->execute();
	$row = $result->fetch(PDO::FETCH_ASSOC);

if (!$row)
    die('Invalid Player ID.');
?>

<form method="POST" action="save.php">
    <p><b>
        <label for="id">ID:</label>
		<?php echo $row['id'] ;?>
        <input id="id" name="id" type="hidden" value="<?php echo $row['id'] ;?>">
    </p>
    <p>
        <label for="name">Name:</label>
        <input id="name" name="name" type="text" value="<?php echo $row['name'] ;?>">
    </p>
	    <p>
        <label for="position">Position:</label>
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
        <label for="nation">Nation:</label>
        <input id="nation" name="nation" type="text" value="<?php echo $row['nation'] ;?>">
    </p>
    <p>
        <label for="teamid">Team ID:</label>
	    <select id="teamid" name="teamid">
		<?php
		$sql2 = 'SELECT teamid FROM team';
		foreach ($dbh->query($sql2) as $row2) { 

echo "<option value='" . $row2['teamid'] . "'>" . $row2['teamid'] . "</option>";
}
?>
</select>
	</p>
    <p>
        <label for="squadnum">Squad Number:</label>
        <input id="squadnum" name="squadnum" type="text" value="<?php echo $row['squadnum'] ;?>">
    </p>
	<p>
        <label for="ovr">Overall:</label>
        <input id="ovr" name="ovr" type="text" value="<?php echo $row['ovr'] ;?>">
    </b></p>
    <p>
        <input type="submit" value="Submit" onclick="return confirm('Confirm?');">
    </p>
</form>

</body>
</html>
