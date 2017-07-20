<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/wangEditor.min.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/addQuestionAjax.js"></script>
<style type="text/css">
	#editor {
		background-color: #fff;
	}
</style>

<title>Ask Question --Answer</title>
</head>
<body>

	<%@ include file="/WEB-INF/staticSource/header.jsp"%>

	<div class="container">
		<br>
		<br>
		<br>
       	<div class="highlight">
		<form name="addQuestionForm" id="addQuestionForm" method="post" action="${pageContext.request.contextPath}/addQuestion">
	  		<div class="form-group">
	    		<%--@declare id="inputtile"--%><label for="InputTile">写下你的问题：</label>
	    		<input type="text" class="form-control" name="qTitle" id="qTitle" placeholder="标题" required>
	  		</div>
	  		<div class="form-group">
				<textarea style="display: none" class="form-control" rows="8"  name="qDetail" id="qDetail"  required>
				</textarea>
				<div style="padding: 5px 0; color:#999999">问题详情：</div>
				<div id="editor">
				</div>

				<script type="text/javascript">
                    var E = window.wangEditor;
                    var editor = new E('#editor');
                    editor.customConfig.zIndex = 100;
                    editor.customConfig.menus = [
                        'bold',  // 粗体
                        'italic',  // 斜体
                        'underline',  // 下划线
                        'strikeThrough',  // 删除线
                        'foreColor',  // 文字颜色
                        'backColor',  // 背景颜色
                        'link',  // 插入链接
                        'list',  // 列表
                        'justify',  // 对齐方式
                        'quote',  // 引用
                        'emoticon',  // 表情
                        'image',  // 插入图片
                        'table',  // 表格
                        'video',  // 插入视频
                        'code',  // 插入代码
                        'undo',  // 撤销
                        'redo'  // 重复
                    ]
                    editor.create();

				</script>
	  		</div>
	  		<div class="form-group">
	    		<input type="text" class="form-control" name="qTopic" id="qTopic" placeholder="标签">
	  		</div>
	  		<div>
				<span class="text-left text-danger">${addQuestionMessage}</span>
			</div>
	  		<button id="submitBtn" type="submit" class="btn btn-default">提交</button>
		</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>


</body>
</html>