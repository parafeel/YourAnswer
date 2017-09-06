<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/frontResource/css/amazeui.min.css" rel="stylesheet" type="text/css">


<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/other/wangEditor.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/frontResource/JS/other/amazeui.min.js" type="text/javascript" ></script>

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
       	<div>
			<div class="am-form-group">
				<label for="qTitle">问题：</label>
				<input type="text" class="am-form-field am-radius" name="qTitle" id="qTitle" placeholder="标题" required/>
	  		</div>
	  		<div class="am-form-group">
				<label for="editor">问题详情：</label>
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
			<div id="app">
				<div class="form-group" name="qTopic" id="qTopic">
					<template v-for="t in endData">
						<span class="Tag-content">
							<button type="button" class="am-btn am-btn-secondary am-round am-btn-xs">
								{{t}}
							</button>
							&nbsp;
						</span>
					</template>
				</div>
				<div class="form-group">
					<a id="addBtn" class="btn" data-toggle="modal" data-target="#myModal1">
						<strong>选择话题分类</strong>
					</a>
					<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title" id="myModalLabel">添加话题分类</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-md-5">
												<input v-on:keyup="showSearchData()" id="searchTopic" type="text"
													   class="form-control"
														placeholder="查询相关话题">
											</div>
											<div class="col-md-6">
												<span class="text-left text-danger" id="searchTopicMsg"></span>
											</div>
										</div>
										<hr>
										<div class="row form-group">
										<div class="col-md-6 ">
											<div class="box_l form-control" style="height: 250px">
												<li  v-for="topic in topics" @click="LeftToRight(topic)">
													{{topic}}
												</li>
											</div>
										</div>
										<div class="col-md-6 ">
											<div class="box_r form-control" style="height: 250px">
												<li v-for="sData in selectedData" @click="RightToLeft(sData)">
													{{sData}}
												</li>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" data-dismiss="modal" @click="listTopic()">
										完成
									</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	  		<div>
				<span class="text-left text-danger" id="addQuestionMessage"></span>
			</div>
	  		<button id="addQuestionBtn" class="am-btn am-btn-secondary">提交</button>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>


<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/Question/addQuestionAjax.js"></script>

</body>
</html>