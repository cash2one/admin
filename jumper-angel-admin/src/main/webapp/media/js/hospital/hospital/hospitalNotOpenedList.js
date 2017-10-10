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
					//console.log(json.data[0].openedNum);
					$(".moj").html(json.data[0].openedNum);
					$(".mok").html(json.data[0].notOpenedNum);
				}
			}
		}
	})
}

//显示分页
function getPage(serviceType,provinceId,cityId,keywords, currIndex){
	$("#tbody").html("");
	$.ajax({
		url:path+"/hospital/hospitalList/hospitalLists",
		type:"get",
		dataType:"json",
		data:{
			"pageIndex":1,
   	 		"everyPage":15,
   	 		"statusType": 0,
   	 		"serviceType": serviceType,
   	 		"provinceId": provinceId,
   	 		"cityId": cityId,
   	 		"first": true,
   	 		"keywords":keywords
			},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	layui.use(["laypage"], function() {
		layui.laypage({
		    cont: "pages",
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
		   	 		"statusType": 0,
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
		   				////console.log(json.data);
		   				var data = json.data;
		   				var html = "";
		   				var status = "";
		   				for(var i=0; i<data.length; i++) {
		   					var url='';
		   					var imgurl=data[i].imgUrl;
		   					var filePath=data[i].filePath
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
			   				
		   					/*//console.log("indexof--------:"+imgurl.indexOf("http://"));
			   				//console.log("imgurl-----------:"+imgurl);
			   				//console.log("filePath------------:"+filePath);
			   				//console.log("url----------------:"+url);*/
		   					status = data[i].isVisible==0?"公开":"隐藏";
		   					html += "<tr height='40px'>";
		   					html += "<td>"+(i+1)+"</td>";
		   					html += "<td>"+data[i].name+"</td>";
		   					html += "<td><img src='"+url+"'/></td>";
		   					html += "<td>"+data[i].province+"</td>";
		   					html += "<td>"+data[i].city+"</td>";
		   					html += "<td>"+data[i].addTime+"</td>";
		   					html += "<td><button class='red_btn_small'  onClick='generalize("+data[i].id+")'>删除</button><br>";
		   					html += "<button class='green_btn_small addHospital' data-filePath='"+data[i].filePath+"'  data-imgUrl='"+data[i].imgUrl+"' type='edit' hospitalId='"+data[i].id+"'>编辑</button><br>";
		   					html += "<button class='blue_btn_small gokiss' data-hospitalId='"+data[i].id+"'>开通网络医院</button></td>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}

//切换省份，省市级联
$(document).on("change","#province,#province2",function(){
	var province_id = $(this).val();//选择的城市
	var clickid=$(this).attr("id");//当前选择的id
	//console.log(province_id,"================"+clickid+"================");
	if(clickid=="province"){
		selecthtml(province_id,"#city",null)
	}else if(clickid=="province2"){
		selecthtml(province_id,"#city2",null)
	}
});

//关键字查询
$(document).on("click","#searchItem",function(){
	var keywords = $("#keywords").val();
	var provinceId = $("#province").val();
	var cityId = $("#city").val();
	var serviceType = 0;
	getPage(serviceType,provinceId,cityId,keywords,1);
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
//添加医院
$(document).on("click",".addHospital",function(){
	$(".tanks p").empty();//清空提示
	var _this=$(this);
	var addtype=_this.attr("type");
	var hospitalid=_this.attr("hospitalid");
	var sl='';
	layer.open({
		title:'医院信息管理',
  		type: 1,
  		area: ['880px','750px'], //宽高
  		content: $('.addOrEdit'),
  		btn: ['保存','取消'],
  		yes: function(indexw){
  			sl=indexw;
  			//revise(hospitalid,addtype);
  			if(addtype=="add"){
  				revise(hospitalid,"add",indexw);
  			}else{
  				revise(hospitalid,"edit",indexw);
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
				//console.log(json.data);
				var html='';
				for(var i=0;i<json.data.length;i++){
					html+='<li>';
					html+='<input type="checkbox" class="checkeds"  data-major="'+json.data[i].major+'" name="chkStudent" value="'+json.data[i].id+'"><label style="margin:0;">'+json.data[i].major+'</label></li>';	
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


//修改。添加方法
function revise(hospitalid,addtype,indexw){
	var valid=true;
	if(addtype=="add"){
		var datavo= dataAdd(hospitalid,addtype,indexw);	
	}else{
		//reviseDot(hospitalid);
		var datavo= dataAdd(hospitalid,addtype,indexw)
	}
	//字符限制方法调用
	valid=valid &&thistext("#names",datavo.name,'.thisyy',2,30);//
	valid=valid &&thistext("#address",datavo.address,'.thisdz',6,50);//
	valid=valid &&thistext("#introduction",datavo.introduction,'.thisjj',24,300);//
	
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
	//console.log("-----------------:"+data);
	if(valid){
		$.ajax({
			url:path+ '/hospital/hospitalList/hospitalInfoCommit',
			type:'post',
			dataType:"json",
			contentType : 'application/json;charset=utf-8',
			data : data,
			success:function(datas){
				//console.log(datas.code+'==================');
				//console.log(datas.data);
				if(datas.code==0){
					setHint('.thisyy', false, "该医院名称已存在");
					return;
				}else if(datas.code==1){
					////console.log("成功");
					layer.close(indexw);
					getPage(serviceType,provinceId,cityId,keywords,active_a());
				}
			}, error: function(){
				//console.log("失败");
		       }
		})
	}
}
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
})

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
		}else if(mins<thlength<maxs){
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
	//console.log(filePath,imgUrl+"----------------------");
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
	//console.log(majorId+'----------',typeof(majorId));
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

//删除
function generalize(id){
    layer.alert($("#generalize").val(), {
      title:'提示',
      btn:['确认', '取消'],
      content: '确认删除医院？',
      btnAlign: 'c',
      yes: function(s){
    	  deletHospital(id);
    	  layer.close(s);
      }
  });
} 

//删除医院
function deletHospital(id){
	var hospitalId=parseInt(id);
	$.ajax({
		type:"GET",//请求方式
		url:path+'/hospital/hospitalList/deleteHospitalInfo',//发送请求地址
		data:{//发送给数据库的数据
			"id": id
		},
		//请求成功后的回调函数有两个参数
		success:function(data){
			////console.log(data.code+"======================",data);
			if(data.code==1){
				layer.msg('操作成功', {time: 600});
				getPage(serviceType,provinceId,cityId,keywords,active_a());
			}else if(data.code==0){
				layer.msg(data.msgbox, {time: 2000});
			}
		},error:function(){
    		return;
    	}
	});
}

//开通网络医院
$(document).on("click",'.gokiss',function(){
	//var uName=
	var hospitalId=$(this).attr("data-hospitalId");
	//清空
	$("#name,#nname,#phone,#passwordNamea,#passwordNameb").val("");
	$(".pthis").empty();
	layer.open({
		title:'开通网络医院',
  		type: 1,
  		area: ['480px','300px'], //宽高
  		content: $('.normal_form2'),
  		btn: ['开通','取消'],
  		yes: function(index){
			  	nextStep(hospitalId,index);
  		},
    	end:function(){
    		
    	}
	});
})

//表单错误提示方法
function setHint(clas,valid, msg){
	if(valid){
		$(clas).html("");
	}else{
		$(clas).html("");
		$(clas).html('*'+msg);
	};
};


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
			setHint(".pthis",valid,'姓名长度必须小于20位');
		}else{
			valid=true;
			setHint(".pthis",valid,'');
		}
	}
	return valid; 
}
/*$("#nname").blur(function(){
	nName("#nname");
})*/
/*
$("#phone").blur(function(){
	isMobile();
});*/
//手机号码验证
/*function isMobile(){
	var patrn=/^1\d{10}$/;
	var phone=$("#phone").val();
	var valid=patrn.test(phone)
	if(!valid && phone!=''){
		valid=false;
		setHint(".pthis",valid,'请输入正确的手机号码');
	}else{
		valid=true;
		setHint(".pthis",valid,"");
	}
	return valid;
};*/

//密码===================================
//字符串长度范围在min与max中
function isRang(str,min) {if(null==str||""==str||str.length<min)return false;return true;}

function passwordName(){
	var passwordNamea=$("#passwordNamea").val();
	var passwordNameb=$("#passwordNameb").val();
	var b = /^[0-9a-zA-Z]*$/g;
	var valid = b.test(passwordNamea);
	var validlength = isRang(passwordNamea,1)  && passwordNamea.length<17;
	var validtrue=(passwordNamea==passwordNameb);
	if(passwordNamea!=''){
		if(validlength){
			if(valid!=true){
				valid=false;
				setHint(".pthis",valid, "密码不符合规则，请重新输入");
			}
		}else{
			validlength=false;
			setHint(".pthis",validlength, "密码不符合规则，请重新输入");
		}
	}
	
	if(passwordNamea !='' && passwordNameb !='' &&  validlength){
		if(!validtrue){
			setHint(".pthis",validtrue, "两次密码不一致");
		}else if(validtrue){
			if(valid){
				setHint(".pthis",validtrue, "");
			}else{
				setHint(".pthis",valid, "密码不符合规则，请重新输入");
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
		
//密码的键盘事件		
$('#passwordNamea,#passwordNameb').keyup(function(){
  	passwordName();
});


//提交数据
function nextStep(hospitalId,index){
	var valid = true;
	var validlength = true;
	var validtrue = true;
	
	var cname=$("#name").val();
	/*var nname=$("#nname").val();
	var phone=$("#phone").val();*/
	var passwordNamea=$("#passwordNamea").val();
	var passwordNameb=$("#passwordNameb").val();
	validlength = validlength && passwordName()[1];//密码长度验证
	validtrue = validtrue && passwordName()[2];//二次密码是否相同验证
	valid = valid && cName("#name");//用户名
	//valid = valid && nName("#nname");//昵称
	//valid = valid && isMobile();//手机
	valid = valid && passwordName()[0];//密码验证
	if(cname==''){
		setHint(".pthis",false,'请设置医院的登录账号');
		return;
	}
	/*if(nname==''){
			setHint(".pthis",false, "请输入昵称");
			return;
	}*/
	/*if(phone==''){
		setHint(".pthis",false,"手机不能为空");
		return;
	}*/
	if(passwordNamea=='') {
		setHint(".pthis", false, "请设置密码");
		return;
	}
	if(passwordNameb==''){
		setHint(".pthis", false, "请再次输入密码");
		return;
	}
	

	
	//console.log(valid,validlength,validtrue);
	//console.log(typeof(hospitalId),hospitalId,cname,nname,phone,passwordNamea);
	if(valid && validlength && validtrue){
		//参数
		var param = {
			
		};
		//转json字符串
		param = JSON.stringify(param);
		$.ajax({
			url: path + "/login/userAdd",
			type: "post",
			//contentType: "application/json;charset=utf-8",
			dataType: "json",//从服务器端返回的数据类型
			data: {
				"hospitalId":hospitalId,
				"userName":cname,
				//"mobile":phone,
				"passWord":passwordNamea
			},
			success: function(json) {
				if(json.code == 0){
					setHint(".pthis",false,"用户名重复，请更换！");
				}else if(json.code == 1){
					//console.log("成功");
					layer.close(index);
					getPage(serviceType,provinceId,cityId,keywords,active_a());
				}
			},error:function(){
				////console.log("失败");
			}
			
		});
	}/* else {
		//console.log("商户信息填写错误，请重新填写");
	}*/
}
