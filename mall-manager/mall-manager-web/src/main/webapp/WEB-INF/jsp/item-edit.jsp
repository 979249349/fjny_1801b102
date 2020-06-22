<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<style type="text/css">
.form-item {
	margin-bottom: 15px;
	width: 100%;
	float: left;
}

.form-item>label {
	min-width: 72px;
	display: inline-block;
}

.form-item input, select {
	width: 270px;
}
</style>

<div class="super-theme-example">
	<form id="itemEditForm" method="post">
		<input type="hidden" name="id">
		<div class="form-item">
			<label for="" class="label-top">商品类目:</label> <a
				href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
			<input type="hidden" name="cid" style="width: 280px;"></input>
		</div>

		<div class="form-item">
			<label for="" class="label-top">商品标题：</label> <input id="title"
				name="title" class="easyui-textbox" prompt="请输入商品名称"
				data-options="required:true">
		</div>
		<div class="form-item">
			<label for="" class="label-top">商品卖点：</label> <input id="sellPoint"
				name="sellPoint" class="easyui-textbox" prompt="请输入商品详情"
				data-options="required:true,validType:'length[3,99]'" style="height: 70px">
		</div>

		<div class="form-item">
			<label for="" class="label-top">商品价格：</label> <input type="text"
				name="price" class="easyui-numberbox" data-options="min:0" />
		</div>
		<div class="form-item">
			<label for="" class="label-top">库存数量：</label> <input type="text"
				name="num" class="easyui-numberbox" data-options="min:0" />
		</div>
		<div class="form-item">
			<label for="" class="label-top">条形码：</label> <input type="text"
				name="barcode" class="easyui-textbox" data-options="required:true" />
		</div>
		<div class="form-item">
			<label for="" class="label-top">商品图片:</label> <a
				href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
			<input type="hidden" name="image" />
		</div>
		<div class="form-item">
			<label for="" class="label-top">商品描述:</label>
			 <textarea name="desc"
				 style="width: 700px; height:300px;">&lt;strong&gt;HTML内容&lt;/strong&gt;
			 </textarea>
		</div>
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton warning"
				onclick="clearForm()">取消</a> <a href="javascript:void(0)"
				class="easyui-linkbutton success" onclick="submitForm()">提交</a>
		</div>
	</form>

	<script  type="text/javascript">
		var itemEditEditor;
		//页面初始化完毕后执行此方法   
		$(function() {
			//创建富文本编辑器     
			itemEditEditor = TT.createEditor("#itemEditForm [name=desc]");
			//初始化类目选择和图片上传器     
			TT.init({
				fun : function(node) {
					//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。         
					TT.changeItemParam(node, "itemEditForm");
				}
			});
		});
		//提交表单   
		function submitForm() {
			//有效性验证     
			if (!$('#itemEditForm').form('validate')) {
				$.messager.alert('提示', '表单还未填写完成!');
				return;
			}
			itemEditEditor.sync();
			//ajax的post方式提交表单     
			//$("#itemEditForm").serialize()将表单序列号为key-value形式的字符串     
			alert($("#itemEditForm").serialize());
			$.post("/item/updata", $("#itemEditForm").serialize(), function(data) {
				if (data.status == 200) {
					$("#dgTbItem").datagrid("reload");
					$.messager.alert('操作成功', '恭喜您更新产品成功', 'warning',
							function() {
							$("#itemEditWindow").window("close");
								$('#item-list').click();
							});
				}
			});
		}
		function clearForm() {
			$('#itemEditForm').form('reset');
		}
	</script>
</div>
