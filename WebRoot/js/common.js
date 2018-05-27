var myAjax = {
	ajax:function(url,reqParam,success,error,golbalCallback,dataType){
		var datas = {
			url:url,
			data:reqParam,
			type:'POST',
			dataType:(dataType?dataType:'json'),
			cache:false
		};
		var ajaxParam = {
	    	url:datas.url,
	    	data:datas.data,
			type:datas.type,
			dataType:datas.dataType,
			cache:datas.cache,
			statusCode:{
				404:function(){
					$(this).openWindow('提示','找不到请求地址','["确定"]');
					if(golbalCallback){golbalCallback.call(datas);}
				},
				500:function(){
					$(this).openWindow('提示','服务器错误','["确定"]');
					if(golbalCallback){golbalCallback.call(datas);}
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				if(error){error.call(datas,XMLHttpRequest, textStatus, errorThrown);}else{
					$(this).openWindow('提示','请求失败','["确定"]');
				}
				if(golbalCallback){golbalCallback.call(datas);}
			},
			success:function(data, textStatus, jqXHR){
				if(success){success.call(datas,data, textStatus, jqXHR);}
				if(golbalCallback){golbalCallback.call(datas);}
			}
	    };
		$.ajax(ajaxParam);
	}
};