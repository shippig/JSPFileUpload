<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload2.do" method="post" enctype="multipart/form-data">
		1. 파일 저장하기 : <input type="file" name="uploadFile01"/> <br>
		2. 파일 저장히기 : <input type="file" name="uploadFile02"/> <br>
		3. 파일 저장히기 : <input type="file" name="uploadFile03"/> <br>
		
		<input type="submit" value="업로드">
	</form>

</body>
</html>