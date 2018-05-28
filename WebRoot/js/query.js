$(document).ready(function(){
	var querying = false;
	var checkQuery = function(that){
		if(querying){
			return;
		}
		querying = true;
		var param = {};
		param.phone = $.trim($('#phone').val());
		param.startDate = $.trim($('#startDate').val());
		if(param.phone == ''){
			$(that).openWindow('提示','手机号条件不能为空','["确定"]');
			return false;
		}else{
			return param;
		}
	};
	var queryFn = function(that){
		var reqParam = checkQuery(that);
		if(reqParam){
			$('.queryListDiv').html('');
			myAjax.ajax('queryOrder',reqParam,function(data){
				if(data.success){
					$('.queryListDiv').html(JSON.stringify(data.datas));
				}else{
					$(that).openWindow('提示',data.message || '点餐信息查询失败','["确定"]');
				}
			},false,function(){
				querying = false;
			},'json');
		}else{
			querying = false;
		}
	};
	
	$('.queryBtn').unbind('click');
	$('.queryBtn').bind('click',function(){
		queryFn(this);
	});
	
});