
/**
 * 初始化bootstrap-select控件
 * @param id 元素id
 * @param url 数据源
 * @param defaultValue 默认值
 */
function initSelect(id,url,defaultValue){
	$.post(url,function(res){
        var txt='';
        for(var i = 0;i <res.length;i++){  
            txt += '<option value='+ res[i].id +'>' + res[i].text+'</option>'; 
        }  
        document.getElementById(id).innerHTML=txt;
        //默认值赋值
        if(defaultValue!=null && defaultValue!=""){
        	$('#'+id).val(defaultValue);
        }
    },"json");  
}

function initSelect2(id,url,defaultValue){
	$.post(url,function(res){
		$('#'+id).empty();
		$('#'+id).select2({
      		minimumResultsForSearch: -1,
		    data:res
		});
		if(defaultValue!=null && defaultValue!=""){
			$('#'+id).val(defaultValue);
			//$('#'+id).select2().val(defaultValue).trigger("change");
			$('#'+id).trigger('change'); 
        }
		
    },"json");  
}
/**
 * 初始化可搜索的select
 * @param id
 * @param url
 * @param defaultValue
 */
function initSelect2Search(id,url,defaultValue){
	$.post(url,function(res){
		$('#'+id).empty();
		$('#'+id).select2({
      		//minimumResultsForSearch: -1,
		    data:res
		});
		if(defaultValue!=null && defaultValue!=""){
			$('#'+id).val(defaultValue);
			$('#'+id).trigger('change'); 
        }
		
    },"json");  
}

/**
 * 初始化laydate控件
 */
$(function(){
	layui.use('laydate', function(){
      var laydate = layui.laydate;
      $(".lay-date").each(function(){
    	  var id = $(this).attr("id");
    	  var dataFormat = $(this).attr("data-format");
    	  if(dataFormat==undefined||dataFormat==null||dataFormat==""){
    		  dataFormat = "date";
    	  }
    	  laydate.render({
	          elem: "#"+id
	          ,type: dataFormat
	          ,showBottom: false
	          ,done: function(){
	        	// $("#"+id).blur();//触发blur事件赋值校验
	        	 $("#"+id).triggerHandler('blur')
	          }
	       });
      })
      
    })
})

/**
 * 多个图片上传调用方法
 * @param elem 上传按钮id
 * @param preview 预览id
 * @param url 上传路径
 * @param id 外键ID
 */
function getUpload(elem,preview,url,id){
	layui.use('upload', function(){
		var $ = layui.jquery
		,upload = layui.upload;
		
		//多图片上传
		  upload.render({
		    elem: '#'+elem
		    ,url: url+id
		    ,size: 0
		    ,multiple: true
		    ,before: function(obj){
		      //预读本地文件示例，不支持ie8
		      obj.preview(function(index, file, result){
		    	  //console.log(index,file);
		    	  //console.log(index,1)
		    	  //$('#'+preview).append('<div id="'+index+'" style="width:138px"><dl><dt style="position: relative;"><img src="'+ result +'" class="layui-upload-img" style="width: 90px;height: 80px"><div class="img-mask"></div><span class="img-del" id="'+index+'" onclick="delt(this)"><i class="glyphicon glyphicon-trash"></i></span></dt><dd id="dd'+index+'" style="padding-right:10px;line-height:16px"></dd></dl></div>')
		      });
		    }
		    ,done: function(res, index, upload){
		      //上传完毕
		    	//console.log('#dd'+index,2);
		    	//var target=document.getElementById('dd'+index);
		    	//console.log(target);
		    	$('#'+preview).append('<dl id="'+res.id+'"><dt style="position: relative;"><img src="'+ res.sqlPath + res.id + res.extName +'" class="layui-upload-img" style="width: 90px;height: 80px"><div class="img-mask"></div><span class="img-del" id="'+res.id+'" onclick="delt(this)"><i class="glyphicon glyphicon-trash"></i></span></dt><dd style="padding-right:10px;line-height:16px"><input type="hidden" id="imageId" name="imageId" value="'+res.id+'" /><div class="img-tip"><span class="img-tip">'+res.name+'</span></div></dd></dl>')
		    }
		  });
		
	});
}
//删除图片
function delt(s){
	$("dl#"+s.id).remove();
}

function delId(s){
	$("#"+s.id).remove();
}

/**
 * 上传文件调用方法
 * @param elem 上传按钮id
 * @param preview 预览id
 * @param url 上传路径
 * @param id 外键ID
 */
function getFileUpload(elem,preview,url,id){
	layui.use('upload', function(){
		var $ = layui.jquery
		,upload = layui.upload;
		
		//多图片上传
		  upload.render({
		    elem: '#'+elem
		    ,url: url+id
		    ,size: 0
		    ,accept: 'file' //普通文件
		    ,done: function(res, index, upload){
		      //上传完毕
		    	var cont=$('#'+preview).children().length+1;
		    	$('#'+preview).append('<div id="'+ res.id +'"><a href="'+ res.sqlPath + res.id + res.extName +'" >'+cont+'.'+res.name+'</a>&nbsp;<span class="img-del" id="'+ res.id +'" onclick="delId(this)"><i class="glyphicon glyphicon-trash"></i></span><input type="hidden" id="fileId" name="fileId" value="'+res.id+'" /></div>')
		    }
		  });
		
	});
}


//关闭层iframe
function closeLayer(){
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
}

//计算天数差的函数，通用  
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式  
    var  aDate,  oDate1,  oDate2,  iDays  
    aDate  =  sDate1.split("-")  
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式  
    aDate  =  sDate2.split("-")  
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
    return  iDays  
}

/**
 * @desc 通过url选择用户
 * url为/orguser/tosel/+字段/+类型
 * 类型说明：
 * 0 默认为字段赋值Userid 字段+"_CN" 赋值为UserName
 * 1 需自己写方法 initFdName(Userid, UserName)
 * @author cy
 * @Date 2018-3-14
 */
function seluser(url){
	layer.open({
	  title: ['选择用户','font-size:18px;text-align:center;padding:0'],
	  type:2,
	  area: ['650px','80%'],
	  shade: [0.3, '#000'],
	  content: url
	}); 
}

/**
 * 筛选客户
 * @param url
 */
function selCustomerOne(url){
	layer.open({
	  title: ['选择客户','font-size:18px;text-align:center;padding:0'],
	  type:2,
	  area: ['650px','80%'],
	  shade: [0.3, '#000'],
	  content: url
	}); 
}
/**
 * 筛选房间
 * @param url
 */
function selRoomOne(url){ 
	layer.open({
	  title: ['选择房间','font-size:18px;text-align:center;padding:0'],
	  type:2,
	  area: ['650px','80%'],
	  shade: [0.3, '#000'],
	  content: url
	}); 
}

function GetUrlArg(name){var url=location.href;if(url.substring(url.length-1)=="#"){url=url.substring(0,url.length-1);};var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");       if (reg.test(url)) return unescape(RegExp.$2.replace(/\+/g, " ")); return ""; };
