<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>글쓴이:</td>
				<td><input type="text" name="name"/></td>	
			</tr>
			<tr>
				<td>제목: </td>
				<td><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td>파일 지정하기:</td>
				<td><input type="file" name="uploadFile"/></td>
			</tr>
		</table>
		
		<input type="submit" value="업로드"/ >
	</form>
</body>
</html>