$(document).ready(function(){
	var added = {};
	var totMoney = 0;
	var initData = {
		showAddNode:function(){
			$('.tb').find('td').each(function(i,td){
				var addNode = $('<button>');
				addNode.addClass('addNode');
				addNode.html('+');
				$(td).append(addNode);
			});
		},
		moveFn:function(){
			var that = this;
			$('.tb').delegate('.moveNode','click',function(){
				var td = $(this).parent('td');
				var index1 = td.attr('index1');
				var index2 = td.attr('index2');
				var index3 = td.attr('index3');
				var gdata = caidanDatas[index1].values[index2];
				var name = gdata.name;
				var data = gdata.values[index3];
				var iid = 'K_'+index1+'_'+index2;
				var iidd = iid+'_'+index3;
				if(data.price){
					$('.'+iidd).remove();
					var node = added[iidd];
					node.count--;
					totMoney -= data.price;
					if(node.count == 0){
						$(this).remove();
						delete added[iidd];
						td.css({backgroundColor:'#CCFFFF'});
					}else {
						var addItem = $('<div>');
						addItem.addClass(iidd+' dingcanItem');
						addItem.attr('dbto',iid);
						addItem.attr('iidd',iidd);
						addItem.attr('price',node.price);
						addItem.attr('count',node.count);
						addItem.html(name+':'+node.price+'元*'+node.count+'='+node.price*node.count+'元');
						$('.dingcan').prepend(addItem);
					}
					that.setShowCount(td,node.count);
				}else if(data.minPrice && data.maxPrice){
					var nodes = added[iidd];
					$('.'+iidd+':first').remove();
					var delNode = nodes[nodes.length-1];
					nodes.splice(nodes.length-1,1);
					if(!nodes || nodes.length==0){
						$(this).remove();
						delete added[iidd];
						td.css({backgroundColor:'#CCFFFF'});
						that.setShowCount(td,0);
					}else{
						that.setShowCount(td,nodes.length);
					}
					totMoney -= delNode.price;
				}else if(data.perPrice){
					var nodes = added[iidd];
					$('.'+iidd+':first').remove();
					var delNode = nodes[nodes.length-1];
					nodes.splice(nodes.length-1,1);
					if(!nodes || nodes.length==0){
						$(this).remove();
						delete added[iidd];
						td.css({backgroundColor:'#CCFFFF'});
						that.setShowCount(td,0);
					}else{
						that.setShowCount(td,nodes.length);
					}
					totMoney -= delNode.itmoney;
				}
				$('.totMoney').html(totMoney);
			});
		},
		addRemoveNode:function(td){
			if(td.children('.moveNode').length==0){
				var moveNode = $('<button>');
				moveNode.addClass('moveNode');
				moveNode.html('-');
				td.append(moveNode);
			}
			td.css({backgroundColor:'#FFCCEE'});
		},
		setShowCount:function(td,count){
			if(!count || count == 0){
				td.children('.itmSl').remove();
				return;
			}
			if(td.children('.itmSl').length==0){
				var itmSlNode = $('<div>');
				itmSlNode.addClass('itmSl');
				itmSlNode.html(count);
				td.append(itmSlNode);
			}else{
				td.children('.itmSl').html(count);
			}
			
		},
		addFn:function(){
			var that = this;
			$('.tb').delegate('.addNode','click',function(){
				var td = $(this).parent('td');
				var index1 = td.attr('index1');
				var index2 = td.attr('index2');
				var index3 = td.attr('index3');
				var gdata = caidanDatas[index1].values[index2];
				var name = gdata.name;
				var data = gdata.values[index3];
				var iid = 'K_'+index1+'_'+index2;
				var iidd = iid+'_'+index3;
				if(data.price){
					var node = added[iidd];
					totMoney+=data.price;
					if(!node){
						node = {};
						node.price = data.price;
						node.count = 1;
						added[iidd] = node;
					}else{
						node.count++;
						$('.'+iidd).remove();
					}
					var addItem = $('<div>');
					addItem.addClass(iidd+' dingcanItem');
					addItem.attr('dbto',iid);
					addItem.attr('iidd',iidd);
					addItem.attr('price',node.price);
					addItem.attr('count',node.count);
					addItem.html(name+':'+node.price+'元*'+node.count+'='+node.price*node.count+'元');
					$('.dingcan').prepend(addItem);
					$('.totMoney').html(totMoney);
					that.addRemoveNode(td);
					that.setShowCount(td,node.count);
				}else if(data.minPrice && data.maxPrice){
					var setTitle = '提示';
					var setContents = '';
					for(var i=data.minPrice;i<=data.maxPrice;i++){
						setContents += ('<option value="'+i+'">'+i+'元</option>');
					}
					setContents = '选择金额：<select class="choseMoney">'+setContents+'</select>';
					var setButton = '["取消","确定"]';
					$(this).openWindow(setTitle,setContents,setButton);
					$('.confirm-button').on('click',function(){
						var choseMoney = parseInt($('.choseMoney').val());
						totMoney+=choseMoney;
						var node = {};
						node.price = choseMoney;
						node.count = 1;
						var nodes = added[iidd];
						if(!nodes){
							nodes = [node];
							added[iidd] = nodes;
						}else{
							nodes.push(node);
						}
						
						var addItem = $('<div>');
						addItem.addClass(iidd+' dingcanItem');
						addItem.attr('dbto',iid);
						addItem.attr('iidd',iidd);
						addItem.attr('price',node.price);
						addItem.attr('count',1);
						addItem.html(name+'='+node.price+'元');
						$('.dingcan').prepend(addItem);
						$('.totMoney').html(totMoney);
						that.addRemoveNode(td);
						
						closeWindow();
						that.setShowCount(td,nodes.length);
					});
				}else if(data.perPrice){
					var setTitle = '提示';
					var setContents1 = '';
					var setContents2 = '';
					if(data.unit == '斤'){
						for(var i=0;i<=10;i++){
							setContents1 += ('<option value="'+i+'">'+i+'斤</option>');
						}
						for(var i=0;i<=9;i++){
							setContents2 += ('<option value="'+i+'">'+i+'两</option>');
						}
						var setContents = '选择重量：<select class="choseJin">'+setContents1+'</select>&nbsp;&nbsp;&nbsp;&nbsp;<select class="choseLiang">'+setContents2+'</select>'
									+'<div class="choseZL_money">共0元</div>';
						var setButton = '["取消","确定"]';
						$(this).openWindow(setTitle,setContents,setButton);
						
						$('.choseJin,.choseLiang').on('change',function(){
							var res = $('.choseJin').val()*data.perPrice+$('.choseLiang').val()*data.perPrice*0.1;
							$('.choseZL_money').html('共'+res.toFixed(2)+'元');
						});
						
						$('.confirm-button').on('click',function(){
							var node = {};
							node.price = data.perPrice;
							node.count = parseFloat($('.choseJin').val()+'.'+$('.choseLiang').val());
							if(node.count == 0){
								return;
							}
							var nodes = added[iidd];
							if(!nodes){
								nodes = [node];
								added[iidd] = nodes;
							}else{
								nodes.push(node);
							}
							
							var addItem = $('<div>');
							addItem.addClass(iidd+' dingcanItem');
							node.itmoney = parseFloat((node.price*node.count).toFixed(2));
							addItem.attr('iidd',iidd);
							addItem.attr('price',node.price);
							addItem.attr('count',node.count);
							addItem.html(name+':'+node.price+'元*'+node.count+'='+node.itmoney+'元');
							totMoney+=node.itmoney;
							
							$('.dingcan').prepend(addItem);
							addItem.attr('dbto',iid);
							$('.totMoney').html(totMoney);
							that.addRemoveNode(td);
							
							closeWindow();
							that.setShowCount(td,nodes.length);
							
						});
					}
				}
				
			});
		},
		dingcanItemFn:function(){
			$('.dingcan').delegate('.dingcanItem','dblclick',function(){
				var iid = $(this).attr('dbto');
				location.href='#'+iid;
			});
		},
		ddokbtnFn:function(){
			var hasClick = false;
			$('.ddokbtn').unbind('click');
			$('.ddokbtn').bind('click',function(){
				if(hasClick){
					return;
				}
				hasClick = true;
				var param = {};
				param.winId = winId;
				var isEmpty = true;
				
				param.ids = [];
				param.price = [];
				param.count = [];
				$('.dingcan .dingcanItem').each(function(i,item){
					param.ids.push($(item).attr('iidd'));
					param.price.push($(item).attr('price'));
					param.count.push($(item).attr('count'));
					
				});
				
				if(param.ids.length == 0){
					hasClick = false;
					$(this).openWindow('提示','未点餐','["确定"]');
					return;
				}
				myAjax.ajax('dingdanConfirm',param,function(data){
					if(data.success){
						location.href = 'orderConfirm?winId='+winId;
					}else{
						$(this).openWindow('提示',data.message || 'ERROR','["确定"]');
					}
				},false,function(){
					hasClick = false;
				},'json');
				
			});
		}
	};
	
	var initShow = function(){
		for(var i=0;i<caidanDatas.length;i++){
			var caidanData = caidanDatas[i];
			if(caidanData.key){
				var tit01 = $('<div>');
				tit01.addClass('tit01');
				tit01.html(caidanData.key);
				$('.mainContent').append(tit01);
			}
			var table = $('<table>');
			table.addClass('tb');
			for(var ii=0;ii<caidanData.values.length;ii++){
				var tr = $('<tr>');
				tr.attr('id','K_'+i+'_'+ii);
				var val1 = caidanData.values[ii];
				var showName = val1.idx+'、'+val1.name;
				var th = $('<th>');
				th.html(showName);
				tr.append(th);
				for(var iii=0;iii<val1.values.length;iii++){
					var val2 = val1.values[iii];
					var td = $('<td>');
					td.attr('index1',i);
					td.attr('index2',ii);
					td.attr('index3',iii);
					if(val2.price){
						td.html(val2.price+'元'+val2.remark);
					}else if(val2.minPrice && val2.maxPrice){
						td.html(val2.minPrice+'-'+val2.maxPrice+'元'+val2.remark);
					}else if(val2.perPrice){
						td.html(val2.perPrice+'元/'+val2.unit+val2.remark);
					}
					tr.append(td);
				}
				table.append(tr);
			}
			$('.mainContent').append(table);
		}
		$('table tr').attr('valign','middle');
	};
	
	var initFn = function(){
		initShow();
		initData.showAddNode();
		initData.moveFn();
		initData.addFn();
		initData.dingcanItemFn();
		initData.ddokbtnFn();
	};
	
	initFn();
	
});