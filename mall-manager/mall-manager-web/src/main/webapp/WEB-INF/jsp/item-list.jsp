<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

		<div class="super-theme-example">
			<div style="height: 550px;">
				<table id="dgTbItem"></table>
			</div>
			<br />
			<br />
			<!-- <table id="pg" style="width:300px"></table> -->
			<div id="itemEditWindow" class="easyui-window" title="商品编辑" style="width:80%;height:80%"
			data-options="iconCls:'icon-save',modal:true,closed:true,href:'item-edit'">
			</div>
		</div>
		<script type="text/javascript">

		$('#dgTbItem').datagrid({
				url: 'item/getItem',
				fit: true,
				pagination: true,
				fitColumns: true,
				toolbar: [{
					text: '添加',
					iconCls: 'fa fa-plus',
					handler: function() {
						$("#item-add").click();
					}
				}, {
					text: '编辑',
					iconCls: 'fa fa-edit',
					handler: function() {
						var ids =getSelections();
						//判断如果未选定，不执行，提示
						if(ids.length ==0){
							$.messager.alert('提示',"必须选择一个商品");
							return;
						}
						//如果选定多行数据 提示：只能选择一个商品
						if(ids.indexOf(',') > 0){
							$.messager.alert('提示',"只能选择一个商品");
							return;
						}
						$("#itemEditWindow").window({
							onLoad:function(){
								var data = $("#dgTbItem").datagrid("getSelections")[0];
								console.log("onLoad:"+data);
								$("#itemEditForm").form('load',data);
								//将商品描述进行显示
								$.getJSON("item/query/item-desc/" +data.id,function(result){
									if(result.status == 200){
										itemEditEditor.html(result.data.itemDesc);
									}
								});
								TT.init({
									"pics":data.image,
									"cid":data.cid,
									fun:function(node){}
								});
							}
						}).window('open');
					}
				}, {
					text: '删除',
					iconCls: 'fa fa-remove',
					handler: function() {
						var ids = getSelectionsIds();
						if(ids.length == 0){
							$.messager.alert('提示','未选中商品!');
							return ;
							}
						
						//提醒是否刪除數據
						$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品吗？',function(r){
							if (r){
								//進行post跟服務端交互
								var params = {"ids":ids};
								$.post("/item/delete",params,function(data){
									if(data.status == 200){ 
										$.messager.alert
										('提示','删除商品成功!',undefined,function(){
											$("#dgTbItem").datagrid("reload");
											}); 
										} }); 
								} });

					}
				},{
					text: '上架',
					iconCls: 'fa fa-save',
					handler: function() {
						var ids = getSelectionsIds();
						if(ids.length == 0){
							$.messager.alert('提示','未选中商品!');
							return ;
							}
						
						//提醒是否上传數據
						$.messager.confirm('确认','确定上架ID为 '+ids+' 的商品吗？',function(r){
							if (r){
								//進行post跟服務端交互
								var params = {"ids":ids};
								$.post("/item/putaway",params,function(data){
									if(data.status == 200){ 
										$.messager.alert
										('提示','上架商品成功!',undefined,function(){
											$("#dgTbItem").datagrid("reload");
											}); 
										} }); 
								} });
						
					}
				},{
					text: '下架',
					iconCls: 'fa fa-save',
					handler: function() {
						var ids = getSelectionsIds();
						if(ids.length == 0){
							$.messager.alert('提示','未选中商品!');
							return ;
							}
						
						//提醒是否下架數據
						$.messager.confirm('确认','确定下架ID为 '+ids+' 的商品吗？',function(r){
							if (r){
								//進行post跟服務端交互
								var params = {"ids":ids};
								$.post("/item/soldout",params,function(data){
									if(data.status == 200){ 
										$.messager.alert
										('提示','下架商品成功!',undefined,function(){
											$("#dgTbItem").datagrid("reload");
											}); 
										} }); 
								} });
					}
				}],

				height: 400,
				columns: [
					[{
						field: 'id',
						title: '商品ID',
						width: 100,
						align: 'center',
						sortable: true
					}, {
						field: 'title',
						title: '商品标题',
						width: 200,
						align: 'center',
						sortable: true
					}, {
						field: 'sell_point',
						title: '商品卖点',
						width: 100,
						align: 'center',
						sortable: true
					}, {
						field: 'num',
						title: '库存',
						width: 100,
						align: 'center',
						sortable: true
					}, {
						field: 'barcode',
						title: '条形码',
						width: 100,
						align: 'center',
						sortable: true
					}, {
						field: 'price',
						title: '商品价格',
						width: 100,
						align: 'center',
						sortable: true
					}, {
						field: 'image',
						title: '图片',
						width: 100,
						align: 'center',
						formatter:function(value,row){
							return "<img src="+value+" width='200px',height='200px'>"
						}
					},{
						field: 'cid',
						title: '类目',
						width: 100,
						align: 'center'
					},{
						field: 'status',
						title: '状态',
						width: 100,
						align: 'center',
						formatter:TT.formatItemStatus
					},{
						field: 'created',
						title: '创建时间',
						width: 100,
						align: 'center',
						formatter:TT.formatDateTime
					},{
						field: 'updated',
						title: '更新时间',
						width: 100,
						align: 'center',
						formatter:TT.formatDateTime
					}]
				]
			});
			
		function getSelections(){
			var itemList = $("#dgTbItem");
			var sels = itemList.datagrid("getSelections");
			var ids =[];
			for(var i in sels){
				ids.push(sels[i].id);
			}
			ids = ids.join(",");
			return ids;
		}
		function getSelectionsIds(){
			var itemList = $("#dgTbItem");
			var sels = itemList.datagrid("getSelections");
			var ids =[];
			for(var i in sels){
				ids.push(sels[i].id);
			}
			ids = ids.join(",");
			return ids;
		}
		
		</script>
</body>
</html>