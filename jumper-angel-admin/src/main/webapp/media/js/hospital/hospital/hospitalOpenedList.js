/**
 * 咨询列表js
 */
//请求总页数
var totalPage = 0;

var serviceType = 0;
var provinceId = 0;
var cityId = 0;
var keywords = "";
$(document).ready(function(){
	//初始化
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	getPage(serviceType,provinceId,cityId,keywords,1);
});
//获取医院数量
hospNumber();
function hospNumber(){
	$.ajax({
		url:path+'/hospital/hospitalList/hospitalNumber',
		type:'get',
		async:false,
		success:function(json){
			if(json.code==1){
				if(json.data!=null){
					$(".moj").html(json.data[0].openedNum);
					$(".mok").html(json.data[0].notOpenedNum);
				}
			}
		}
	})
}

//植入服务下拉列表
selectop();
function selectop(){
	$.ajax({
		url:path+'/hospital/hospitalList/getService',
		type:'post',
		async:false,
		success:function(json){
			////console.log(json.code);
			if(json.code==1){
				var data=json.data;
				////console.log(data);
				if(data!=null){
					var html='';
					for(var i=0;i<data.length;i++){
						html +='<option value='+data[i].id+'>'+data[i].name+'</option>'
					}
					$("#chanels").html(html);
				}
			}
		},error:function(){
			//console.log("失败");
		}
		
	})
}

//切换省份，省市级联
$(document).on("click","#province,#province2",function(){
	var province_id = $(this).val();//选择的城市
	var clickid=$(this).attr("id");//当前选择的id
	////console.log(province_id,"================"+clickid+"================");
	if(clickid=="province"){
		selecthtml(province_id,"#city",null)
	}else if(clickid=="province2"){
		selecthtml(province_id,"#city2",null)
	}
});

//省级联动方法
function  selecthtml(province_id,classhtml,city){
	$.ajax({
		type: 'get',
		dataType: 'json',
		data: 'id='+province_id,
		url: path + '/promotStatistics/getCityByProvince',
		success: function(ret){
			if(ret.code==1){
				if(ret.data != null){
					var content = '<option value="0">--- 请选择城市 ---</option>';
					var city_list = ret.data;
					for(var i = 0;i < city_list.length; i++){
						content+='<option value="'+city_list[i].id+'" >'+city_list[i].city_name+'</option>';
					}
					$(classhtml).html(content);
				}
				if(city!=null){//回调后设置值
					document.getElementById('city2').value=city;
				}
			}
		}
	});
}


//显示分页
function getPage(serviceType,provinceId,cityId,keywords,currIndex){
	$.ajax({
		url:path+"/hospital/hospitalList/hospitalLists",
		type:"get",
		dataType:"json",
		data:{
			"pageIndex":1,
   	 		"everyPage":15,
   	 		"statusType": 1,
   	 		"serviceType": serviceType,
   	 		"provinceId": provinceId,
   	 		"cityId": cityId,
   	 		"first": true,
   	 		"keywords":keywords
		},
		//data:{"pageIndex":1, "everyPage":10, "first":true, "keywords":keywords},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	layui.use(["laypage"], function() {
		layui.laypage({
		    cont: "page",
		    pages: totalPage,
		    curr: currIndex,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    var curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":15,
		   	 		"statusType": 1,
		   	 		"serviceType": serviceType,
		   	 		"provinceId": provinceId,
		   	 		"cityId": cityId,
		   	 		"first": false,
		   	 		"keywords":keywords
		   	 	};
		   	 	$.ajax({
		   			url: path + "/hospital/hospitalList/hospitalLists",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				//var url ='';
		   				for(var i=0; i<data.length; i++) {
		   					var service = "";//开通的服务
		   					var text='';//区分文字
			   				var textclass='';//区分颜色
			   				var bod='';//是否可点击
		   					if(data[i].service==null){
		   						service=''
		   					}else{
		   						var dataj=data[i].service;
		   						for(var j=0;j<dataj.length;j++){
		   							if(dataj[j]!=null)
		   							service+=dataj[j]+'<br/>'
		   						}
		   					}
		   					if(data[i].isEnabled==1){
		   						if(data[i].isLocked==0){
			   						text="正常";
			   						textclass="huis";
			   						bod='disabledt';
			   					}else if(data[i].isLocked==1){
			   						text="被锁定";
			   						textclass="lus";
			   						bod='disableds';
			   					}
		   					}else if(data[i].isEnabled==0){
		   						text="禁用";
		   						textclass="huis";
		   						bod='disabledt';
		   					}
			   					
		   					var url='';
		   					var imgurl=data[i].imgUrl;
		   					var filePath=data[i].filePath;
		   					if(imgurl!=''){
		   						if(imgurl.indexOf("http")>=0){
				   					//alert(22);
			   						url=data[i].imgUrl;
			   					}else if(imgurl.indexOf("http")==-1){
			   						//alert(11);
			   						url=filePath+'/'+imgurl;//拼接url
			   					}
		   					}else{
		   						url=path+'/assets/images/base/default.jpg';
		   					}
		   					////console.log(url+'=========================');
		   					html += "<tr height='40px'>";
		   					html += "<td>"+(i+1)+"</td>";
		   					html += "<td>"+data[i].name+"</td>";
		   					html += "<td><img src='"+url+"'/></td>";
		   					html += "<td>"+data[i].province+"</td>";
		   					html += "<td>"+data[i].city+"</td>";
		   					html += "<td class='uname'>"+data[i].userName+"</td>";
		   					html += "<td>"+service+"</td>";
		   					html += "<td>"+data[i].addTime+"</td>";
		   					html += "<td>"+data[i].loginTime+"</td>";
		   					html += "<td><button class='red_btn_small  addHospital'  data-filePath='"+data[i].filePath+"'  data-imgUrl='"+data[i].imgUrl+"'  data-type='edit' data-id='"+data[i].id+"' data-filePath="+data[i].filePath+">编辑</button><br>";
		   					html += "<button class='green_btn_small onts  "+textclass+" "+bod+"'  data-isLocked="+data[i].isLocked+">"+text+"</button><br>";
		   					html += "<button class='green_btn_small dbist' data-username='"+data[i].userName+"' data-monitorId="+data[i].monitorId+"  data-id="+data[i].id+" data-isLocked="+data[i].isLocked+" data-isEnabled='"+data[i].isEnabled+"'>账号管理</button><br>";
		   					html += "<button class='blue_btn_small quxian' data-isPayment="+data[i].isPayment+">高级权限管理</button></td>";
		   				}
		   				$("#tbody").html(html);
		   				//$('#areaSelect').removeAttr("disabled");
		   				$(".disabledt").attr("disabled",true);
		   				$(".disableds").attr("disabled",false);
		   			}
		   		});
	   		}
	  	});
	});
}

function aays(major){
	$.ajax({
		url:path+'/hospital/majorList/majorLists',
		type:'get',
		data:{
			"pageIndex":1,
			"everyPage":8888,
			"keywords":null,
			"first":false
		},
		success:function(json){
			if(json.code==1){
				var html='';
				for(var i=0;i<json.data.length;i++){
					html+='<li>';
					html+='<input type="checkbox" class="checkeds"  data-major="'+json.data[i].major+'" name="chkStudent" value="'+json.data[i].id+'"><label style="margin:0">'+json.data[i].major+'</label></li>';	
					html+='</li>';
				}
				$("#majorId").html(html);
				if(major!=null){
					var ont=$("input[name=chkStudent]");
					var ontlength=ont.length;
					for(var k=0;k<ontlength;k++){
						for(var i=0;i<major.length;i++){
							////console.log(ont,"================="+data.majorId[i]);
							if(ont.eq(k).val() == major[i]){
								ont.eq(k).attr("checked",true);
							}
							
						}
					}
				}
				
			}
		}
	})
}

//获取当前分页的页码数
function active_a(){
	var currIndex='';
	var name=$(".layui-laypage-curr em").eq(1).html();
	if(name!=undefined){
		currIndex=name;
	}else{
		aurrIndex=1;
	}
	return currIndex;
}

//添加医院
$(document).on("click",".addHospital",function(){
	$(".tanks p").empty();//清空提示
	var _this=$(this);
	var addtype=_this.attr("data-type");
	var hospitalid=_this.attr("data-id");
	var sl='';
	//var titile='';
	if(addtype=="edit"){
		reviseDot(_this,hospitalid);
		//title="添加医院";
	}else if(addtype=="add"){
		//清空
		//title="医院编辑";
		$("#names,#introduction,#address").val("");
		$("#province2,#city2").val("0");
		$("#uploadfile").val("");	
		$(".img-thumbnail").attr("src","/admin/assets/images/empty.png");
	}
	aays(null);//科室方法
	layer.open({
		title:"医院信息管理",
  		type: 1,
  		area: ['880px','750px'], //宽高
  		content: $('.addOrEdit'),
  		btn: ['保存','取消'],
  		yes: function(indexw){
  			sl=indexw;
  			//revise(hospitalid,addtype);
  			if(addtype=="add"){
  				revise(0,addtype,indexw);
  			}else{
  				revise(hospitalid,addtype,indexw);
  			}
  		},
    	end:function(){
    		//ua.destroy();
    	}
	});
	if(addtype=="edit"){
		reviseDot(_this,hospitalid);
	}else if(addtype=="add"){
		//清空
		$("#names,#introduction,#address").val("");
		$("#province2,#city2").val("0");
		$("#uploadfile").val("");	
		$(".img-thumbnail").attr("src","/admin/assets/images/empty.png");
	}
	aays(null);//科室方法
	
	
});

//修改。添加方法
function revise(hospitalid,addtype,indexw){
	var valid = true;
	//alert(hospitalid+"医院id");
	if(addtype=="add"){
		var datavo= dataAdd(hospitalid,addtype,indexw);	
	}else{
		//reviseDot(hospitalid);
		var datavo= dataAdd(hospitalid,addtype,indexw)
	}
	//字符限制方法调用
	valid=valid && thistext("#names",datavo.name,'.thisyy',2,30);//
	valid=valid && thistext("#introduction",datavo.introduction,'.thisjj',24,300);//
	valid=valid && thistext("#address",datavo.address,'.thisdz',6,50);//
	
	if(datavo.name==''){
		setHint('.thisyy', false, '请输入医院名称');
		return;
	}
	if(datavo.introduction==''){
		setHint('.thisjj', false, '请输入简介信息');
		return;
	}
	if(datavo.province==0){
		setHint('.thissf', false, '请选择省份');
		return;
	}
	if(datavo.city==0){
		setHint('.thiscs', false, '请选择城市');
		return;
	}
	if(datavo.address==''){
		setHint('.thisdz', false, '请完善医院地址');
		return;
	}
	if(datavo.majorId[0]==undefined){
		setHint('.thisks', false, '请至少选择一个科室');
		return;
	}
	
	var data=JSON.stringify(datavo);
	////console.log("=------------:"+data);
	if(valid){
		$.ajax({
			url:path+ '/hospital/hospitalList/hospitalInfoCommit',
			type:'post',
			dataType:"json",
			contentType : 'application/json;charset=utf-8',
			data : data,
			success:function(datas){
				////console.log(datas);
				if(datas.code==1){
					//console.log("成功");
					layer.close(indexw);
					getPage(serviceType,provinceId,cityId,keywords,active_a());
				}
			}, error: function(){
				//console.log("失败");
		       }
		})
	}
}

//表单错误提示方法
function setHint(clas,valid, msg){
	if(valid){
		$(clas).html("");
	}else{
		$(clas).html("");
		$(clas).html('*'+msg);
		return;
	};
};


//添加字符的失焦事件
$("#names").blur(function(){
	var name=$("#names").val();//医院名称
	thistext("#names",name,'.thisyy',2,30);//
})
$("#introduction").blur(function(){
	var introduction=$("#introduction").val();//医院简介
	thistext("#introduction",introduction,'.thisjj',24,300);//
})
$("#address").blur(function(){
	var address=$("#address").val();//详细地址
	thistext("#address",address,'.thisdz',6,50);//
})
$("#province2").click(function(){
	setHint('.thissf', true, '')
})
$("#city2").click(function(){
	setHint('.thiscs', true, '')
})
$(document).on("change",'.checkeds',function(){
	setHint('.thisks', true, '')
});


//添加字符限制
function thistext(nameid,nameval,cname,mins,maxs){
	var valid=true;
	var thlength=nameval.length;
	if(nameval!=''){
		if(thlength<mins){
			valid=false;
			setHint(cname, false, '请输入'+mins+'-'+maxs+'个字符');
			return;
		}else if(thlength>maxs){
			valid=false;
			setHint(cname, false, '请输入'+mins+'-'+maxs+'个字符');
			return;
		}else{
			valid=true;
			setHint(cname, true, '');
		}
	}else if(nameval==''){
		setHint(cname, true, '');
	}
	return valid;
}

function reviseDot(_this,hospitalid){
	var url='';
	var filePath=_this.attr("data-filePath");
	var imgUrl=_this.attr("data-imgUrl");
	$.ajax({
		url:path+'/hospital/hospitalList/hospitalinfo',
		get:'get',
		dataType:"json",
		contentType : 'application/json;charset=utf-8',
		data:{"hospitalId":hospitalid},
		success:function(json){
			var data=json.data
			$("#names").val(data.name);//医院名称
			$("#introduction").val(data.introduction);//医院简介
			if(imgUrl!=''){
				if(imgUrl.indexOf("http")>=0){
					url=imgUrl;
				}else if(imgUrl.indexOf("http")==-1){
					url=filePath+'/'+imgUrl;
				}
			}
			
			$("#uploadfile").val(imgUrl);//图片地址
			$(".img-thumbnail").attr("src",url);//展示图片	
			//$("#imgPicker_"+imgUploadId.split(",")[0]).attr("src", "");
			var city=data.city;
			$("#province2").val(data.province);//省份id
			selecthtml(data.province,"#city2",city);
			$("#address").val(data.address);//详细地址
			aays(data.majorId);
		}
	})
}

//获取用户添加添加或修改输入的值 放在一个集合 vo 来使用
function dataAdd(hospitalid,addtype,indexw){
	var name=$("#names").val();//医院名称
	var introduction=$("#introduction").val();//医院简介
	var uploadfile=$("#uploadfile").val();//图片地址
	var province2=$("#province2").val();//省份id
	var city2=$("#city2").val();//城市id
	var address=$("#address").val();//详细地址
	var majorId=[];//科室
//	//console.log($("input[name=chkStudent]").eq(0).prop("checked"));
	var checkBox = $("input[name=chkStudent]");
	var xLength = checkBox.length;
	for(var i=0;i<xLength;i++){
		if(checkBox.eq(i).prop("checked")){
			/*//console.log("=================================");
			//console.log(checkBox.eq(i).attr("value"));*/
			majorId.push(checkBox.eq(i).attr("value"));
		}
	}
	var vo={};
	vo["id"]=hospitalid;
	vo["type"]=addtype;
	vo["name"]=name;
	vo["introduction"]=introduction;
	vo["uploadfile"]=uploadfile; 
	vo["province"]=province2;
	vo["city"]=city2;
	vo["address"]=address;
	vo["majorId"]=majorId;
	////console.log(vo);
	return vo;
}
//点击触发账号管理
$(document).on("click",'.dbist',function(){
	var userName=$(this).attr("data-username");
	var hospitalId=$(this).attr('data-id');
	var isLocked=$(this).attr('data-isLocked');
	var monitorId=$(this).attr('data-monitorId');
	var isEnabled=$(this).attr('data-isEnabled');
	openTheHospital(hospitalId,userName,monitorId,isEnabled)
});

//账号管理
function openTheHospital(hospitalId,userName,monitorId,isEnabled){
	//alert(isLocked);
	//alert(hospitalId)
	//var uName=
	//清空
	$("#passwordNamea,#passwordNameb").val("");
	$(".pthis").empty();
	layer.open({
		title:'账号管理',
  		type: 1,
  		area: ['480px','490px'], //宽高
  		content: $('.normal_form2'),
  		btn: ['保存','取消'],
  		yes: function(sit){
  			nextStep(sit,hospitalId,userName,monitorId);
  		},
    	end:function(){
    		
    	}
	});
	$("#name").val(userName);
	if(isEnabled==0){
		$('input[name=status]').attr("checked",true);
		$("#status").val("0");
	}else if(isEnabled==1){
		$('input[name=status]').attr("checked",false);
		$("#status").val("1");
	}
}
$("#name").blur(function(){
	cName("#name");
})
//用户名
function cName(id){
	var valid=true;
	var name=$(id).val();
	var pardi=/^[\w-]*$/i;
	valid=pardi.test(name);
	//var ps=/^[a-zA-Z\/ ]{0,20}$/;
	if(name!=''){
		if(!valid){
			valid=false;
			setHint(".pthis",valid,'用户名不允许出现中文或空格');
			return;
		}else if(1>name.length){
			valid=false;
			setHint(".pthis",valid,'姓名长度必须大于1位');
		}
		if(name.length>20){
			valid=false;
			setHint(".pthis",valid,'姓名长度必须小于10位');
		}else{
			valid=true;
			setHint(".pthis",valid,'');
		}
	}
	return valid; 
}

//密码===================================
//字符串长度范围在min与max中
function isRang(str,min) {if(null==str||""==str||str.length<min)return false;return true;}
//密码的键盘事件		
$('#passwordNamea,#passwordNameb').keyup(function(){
  	passwordName("#passwordNamea","#passwordNameb",".pthis");
});
function passwordName(clname,blname,tname){
	var passwordNamea=$(clname).val();
	var passwordNameb=$(blname).val();
	var b = /^[0-9a-zA-Z]*$/g;
	var valid = b.test(passwordNamea);
	
	var validlength = isRang(passwordNamea,1)  && passwordNamea.length<17;
	var validtrue=(passwordNamea==passwordNameb);
	if(passwordNamea!=''){
		if(validlength){
			if(valid!=true){
				valid=false;
				setHint(tname,valid, "密码不符合规则，请重新输入");
			}
		}else{
			validlength=false;
			setHint(tname,validlength, "密码不符合规则，请重新输入");
		}
	}
	if(passwordNamea==''&& passwordNameb==''){
		validlength=true;
		setHint(tname,validlength, "");
	}
	
	if(passwordNamea !='' && passwordNameb !='' &&  validlength){
		if(!validtrue){
			validtrue=false;
			setHint(tname,validtrue, "两次输入密码不一致");
		}else if(validtrue){
			if(valid){
				setHint(tname,validtrue, "");
			}else{
				setHint(tname,valid, "密码不符合规则，请重新输入");
			}
		}
	}
	if(passwordNameb=='' && passwordNamea==''){
		setHint(".pthis",true, "");
	}
	
	var p=[];
	p.push(valid,validlength,validtrue);
	return p;
	
}

//点击禁用方法
$(document).on("change",'#status',function(){
	var _this=$(this).val();
})

//提交数据
function nextStep(sit,hospitalId,userName,monitorId){
	var valid = true;
	var validlength = true;
	var validtrue = true;
	var index=0;
	
	var cname=$("#name").val();
	var passwordNamea=$("#passwordNamea").val();
	var passwordNameb=$("#passwordNameb").val();
	var status=0;
	
	if($("#status[type='checkbox']").is(':checked')){
		status=0;
	}else {
		status=1;
	}
	////console.log(status,typeof(status));
	
	validlength = validlength && passwordName("#passwordNamea","#passwordNameb",".pthis")[1];//密码长度验证
	validtrue = validtrue && passwordName("#passwordNamea","#passwordNameb",".pthis")[2];//二次密码是否相同验证
	valid = valid && cName("#name");//用户名
	valid = valid && passwordName("#passwordNamea","#passwordNameb",".pthis")[0];//密码验证

	if(cname==''){
		setHint(".pthis",false,'请输入用户名');
		return;
	}
	if(passwordNamea!='' && passwordNameb==''){
			setHint(".pthis",false,'请输入密码');
			return;
	}
	if(passwordNamea=='' && passwordNameb!=''){
			setHint(".pthis",false,'请输入密码');
			return;
	}
	////console.log(valid,validlength,validtrue);
	if(valid && validlength && validtrue){
		//console.log(cname,"----------"+userName);
		//参数
		/*var param = {};
		//转json字符串
		param = JSON.stringify(param);*/
		var index;
		
		$.ajax({
			url: path + "/hospital/hospitalList/accountManagement",
			type: "post",
			//contentType: "application/json;charset=utf-8",
			dataType: "json",//从服务器端返回的数据类型
			data: {
				"hospitalId":hospitalId,
				"userName":cname,
				"monitorId":monitorId,
				"password":passwordNamea,
				"status":-1,
				"isEnabled":status
			},
			success: function(json) {
				//console.log(json.msgbox,json.code);
				index=json.code;
				/*if(json.code == 0){
					setHint(".pthis",false,"用户名已存在！");
				}else*/ 
				if(json.code == 1){
					////console.log("成功");
					layer.close(sit);
					getPage(serviceType,provinceId,cityId,keywords,active_a());
				}
			},error:function(){
			}
			
		});
	}/* else {
		//console.log("商户信息填写错误，请重新填写");
	}*/
}

//密码的键盘事件		
$('#passwordName1,#passwordName2').keyup(function(){
  	passwordName("#passwordName1","#passwordName2",".pthis2");
});

//解锁方法
$(document).on("click",'.onts',function(){
	var _this=$(this);
	var userName=_this.siblings('.dbist').attr("data-username");
	var hospitalId=_this.siblings('.dbist').attr('data-id');
	var isLocked=_this.siblings('.dbist').attr('data-isLocked');
	var monitorId=_this.siblings('.dbist').attr('data-monitorId');
	//清空
	$("#passwordName1,#passwordName2").val('');
	$("#name2").val(userName);
	layer.open({
		title:'账号解锁',
  		type: 1,
  		area: ['480px','390px'], //宽高
  		content: $('.normal_form3'),
  		btn: ['保存','取消'],
  		yes: function(ind){
  			aoccmt(ind,_this,userName,hospitalId,monitorId)
  		},
    	end:function(){
    		
    	}
	});
});

function aoccmt(ind,objs,userName,hospitalId,monitorId){
	var valid = true;
	var validlength = true;
	var validtrue = true;
	var index=0;
	
	var cname=$("#name2").val();
	var passwordNamea=$("#passwordName1").val();
	var passwordNameb=$("#passwordName2").val();
	validlength = validlength && passwordName("#passwordName1","#passwordName2",".pthis2")[1];//密码长度验证
	validtrue = validtrue && passwordName("#passwordName1","#passwordName2",".pthis2")[2];//二次密码是否相同验证
	valid = valid && cName("#name");//用户名
	valid = valid && passwordName("#passwordName1","#passwordName2",".pthis2")[0];//密码验证
	if(cname==''){
		setHint(".pthis2",false,'请输入用户名');
		return;
	}
	if(passwordNamea=='') {
		setHint(".pthis2", false, "密码不能为空");
		return;
	}
	if(passwordNameb==''){
		setHint(".pthis2", false, "请再次输入密码");
		return;
	}
	
	////console.log(valid,validlength,validtrue);
	if(valid && validlength && validtrue){
		$.ajax({
			url: path + "/hospital/hospitalList/accountManagement",
			type: "post",
			//contentType: "application/json;charset=utf-8",
			dataType: "json",//从服务器端返回的数据类型
			data: {
				"hospitalId":hospitalId,
				"userName":cname,
				"userName":userName,
				"monitorId":monitorId,
				"password":passwordNamea,
				"isEnabled":-1,
				"status":0
			},
			success: function(json) {
				////console.log(json.msgbox,json.code);
				//注册成功
				index=json.code;
				if(json.code == 1){
					objs.addClass("huis").html("正常").removeClass("onts").attr("disabled",true);
					layer.close(ind);
					getPage(serviceType,provinceId,cityId,keywords,active_a());
				}
			},error:function(){
				////console.log("失败");
			}
		});
	}
	
	
	
}



//高级权限点击触发
$(document).on("click",'.quxian',function(){
	var isPayment=$(this).attr("data-isPayment");
	var hospitalId=$(this).siblings(".dbist").attr('data-id');
	$("input[name=status_k]").eq(isPayment).attr("checked",true);
	layer.open({
		title:'医院高级权限管理',
  		type: 1,
  		area: ['400px','200px'], //宽高
  		content: $('.normal_form4'),
  		btn: ['保存','取消'],
  		yes: function(indc){
  			isPayment=$("input[name=status_k]:checked").val();
  			////console.log(isPayment,hospitalId);
  			if(isPayment!=undefined){
  				$.ajax({
  	  				url:path+'/hospital/hospitalList/isPayment',
  	  				type:'get',
  	  				data:{
  	  					"hospitalId":hospitalId,
  	  					"status":isPayment
  	  				},
  	  				success:function(json){
  	  					if(json.code==1){
  	  						layer.close(indc);
  	  						getPage(serviceType,provinceId,cityId,keywords,active_a());
  	  					}
  	  				},error:function(){
  	  					//console.log("失败");
  	  				}
  	  			})
  			}
  		},
    	end:function(){
    	}
	});
	
})

//关键字查询
$(document).on("click","#searchItem",function(){
	var keywords = $("#keywords").val();
	var provinceId = $("#province").val();
	var cityId = $("#city").val();
	var serviceType =  $("#chanels").val();
	getPage(serviceType,provinceId,cityId,keywords);
});
