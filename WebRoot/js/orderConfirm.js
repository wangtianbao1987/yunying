$(document).ready(function(){
	var checkSub = function(that){
		var param = {};
		param.userName = $('#userName').val();
		param.phone = $.trim($('#phone').val());
		if(!param.phone){
			$(that).openWindow('提示','联系电话不能为空','["确定"]');
			return false;
		}
		param.address = $.trim($('#address').val());
		if(!param.address){
			$(that).openWindow('提示','配送地址不能为空','["确定"]');
			return false;
		}
		param.notLike = $('#notLike').val();
		param.winId = winId;
		return param;
	}
	$('.subBtn').unbind('click');
	$('.subBtn').bind('click',function(){
		var that = this;
		var reqParam = checkSub(that);
		if(!reqParam){
			return;
		}
		myAjax.ajax('saveOrder',reqParam,function(data){
			if(data.success){
				$(that).openWindow('提示','订单信息提交成功','["确定"]');
				$('.cancel-button,.window-masking,.ack-button').unbind('click');
				$('.cancel-button,.window-masking').bind('click',function(){});
				$('.ack-button').bind('click',function(){
					location.href = 'index.html';
				});
			}else{
				$(that).openWindow('提示',data.message || '信息保存失败','["确定"]');
			}
		},false,function(){},'json');
	});
});