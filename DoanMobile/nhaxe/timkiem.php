<?php
include  "connect.php";
$search = $_POST['search'];
if(empty($search)){
	$arr = ['success' => false ,
			'message' => "Khong Thanh Cong",
		];
}else{
	$query = "SELECT * FROM `xemoi`WHERE `DiaDiem` LIKE '%".$search."%'";
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
}


print_r(json_encode($arr));
?>