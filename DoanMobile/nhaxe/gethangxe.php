<?php
include  "connect.php";
$query = "SELECT * FROM `nhaxe`";
$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
	// code...
	$result[]=($row);
}
if (!empty($result)) {
	// code...
	$arr = [
			'success' => true,
			'message' => "Thanh Cong",
			'result' => $result
	];
}else{
	$arr = ['success' => false ,
			'message' => "Khong Thanh Cong",
			'result' => $result];
}
print_r(json_encode($arr));
?>