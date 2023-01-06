<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<label for="multiFile">请选择需要上传的多个文件:<label>
		<input type="file" name="file" id="multiFile" multiple />
		<button id="btn" onclick="uploads()">上传</button>
		
		<script type="text/javascript">
			function uploads(){
				if(window.XMLHttpRequest){
					var xhr = new XMLHttpRequest();
				} else {
					var xhr = new ActiveXObject('Microsoft XMLHTTP');
				}
				
				xhr.open('POST','/algz/common/file/uploads','true');
				xhr.setRequestHeader('X-Requested-With','XMLHttpRequest');
				var files = document.getElementById('multiFile').files;
				//console.log(files);
				
				var formData = new FormData();
				for(var i=0;i<files.length;i++){
					formData.append('file',files[i]);
				}
				formData.append('extra','aaaa')
				//console.log(formData.getAll('files'));
				
				xhr.send(formData);
				
			}
		</script>
name:${name}
</body>
</html>