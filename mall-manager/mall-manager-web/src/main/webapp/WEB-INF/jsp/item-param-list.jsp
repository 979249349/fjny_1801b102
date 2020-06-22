<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="super-theme-example">
			<div style="height: 350px;">
				<table id="itemParamList"></table>
			</div>
			<br /><br />
		</div>
		<script>
		$('#itemParamList').datagrid({
			url: '/item/param/list',
			fit: true,
			pagination: true,
			fitColumns: true,
			toolbar: [{
				text: '添加',
				iconCls: 'fa fa-plus',
				handler: function() {
					TT.createWindow({url:'item-param-add'});
				}
			}, {
				text: '编辑',
				iconCls: 'fa fa-edit',
				handler: function() {}
			}, {
				text: '删除',
				iconCls: 'fa fa-remove',
				handler: function() {}
			}],

			height: 400,
			columns: [ [ {
					field: 'id',
					title: 'Id',
					width: 60
				},{
					field: 'itemCatId',
					title: '类目Id',
					width: 60
				},{
					field: 'itemCatName',
					title: '类目名称',
					width: 100
				},{
					field: 'paramData',
					title: '规格',
					width: 300,
					formatter : formatItemParamData 
				},{
					field: 'created',
					title: '创建时间',
					width: 130,
					formatter:TT.formatDateTime
				},{
					field: 'updated',
					title: '更新时间',
					width: 130,
					formatter:TT.formatDateTime
				},]
			]
		});
		function formatItemParamData(val,index){
			var json = JSON.parse(val);
			var array = [];
			$.each(json,function(i,e){
				array.push(e.group);
			})
			return array.join(",");
		}
		</script>