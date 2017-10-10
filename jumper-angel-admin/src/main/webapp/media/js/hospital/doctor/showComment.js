function showComment(id,isAdmin,userId,doctorId,serviceType){
		$.ajax({
			type:"get",//请求方式
			url:"showComments",//发送请求地址
		    data:{//发送给数据库的数据
		    	chatUrl:"/accounts/add",
		    	messageUrl:"/im/sel_message",
		    	serviceType:serviceType,
		    	userId: userId,
		    	doctor_id: doctorId,
		    	isAdmin: isAdmin,
		    	consultantId: id
		  	},
	    	//请求成功后的回调函数有两个参数
	    	success:function(result,textStatus){
	    		var data = result.data;
	    		var html = '';
	    		var a1=/\.tempAu/;
	    		var a2=/\.audioI/;
	    		var b=/http/;
	    		
	    		for(var index = data.length-1;index>=0;index--){
	    			html += "<tr><td ><a href='#'>"+data[index].sendChatName+"<a/>&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].sendTime
	    			+"\n\t</td></tr>";
	    			if (data[index].msgType==2) {
	   					html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<img src='"+data[index].msgContent
	   						+"' class='img' height='"+data[index].height+"' width='"+data[index].width+"'></img></td></tr>";
	    			}else if (data[index].msgType==14) {
	    				/*html += "<tr><td >"+data[index].sendChatName+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].sendTime
	    					+"\n\t</td></tr>";*/
	    				var a1T = a1.test(data[index].msgContent);
	    				var a2T = a2.test(data[index].msgContent);
	    				if(a1T || a2T){
	    					if(b.test(data[index].msgContent)){
	    						html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;"+
		    						"<div><i id='xx_"+data[index].msgId
		    							+"' class='audioI' style='width:32px; height:32px; float:left;overflow:hidden;'>"+
		    						"<img  src='"+path
		    							+"/assets/images/audio.png' style='width: 100%;  height: 100%;'></i><audio style='display:none;' class='aAudio' id='aaa_"+
		    							data[index].msgId+"' src='"+
		    						data[index].msgContent+"'controls='controls'></audio><span style='float:left;margin:4px 0 0 5px;'>"+
		    						data[index].size+"</span><i class='dataYl' style='display:none;'>"+data[index].size+"</i></div></td></tr>";
	    					}else{
		   						html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;"+
		   							"<div><i id='xx_"+data[index].msgId+"' class='audioI' style='width:32px; height:32px; float:left;overflow:hidden;'>"+
		   							"<img  src='"+path+"/assets/images/audio.png' style='width: 100%;  height: 100%;'></i><audio style='display:none;' class='aAudio' id='aaa_"+data[index].msgId+"' src='"+
		   							data[index].filePath+data[index].msgContent+"'controls='controls'></audio><span style='float:left;margin:4px 0 0 5px;'>"+
		   							data[index].size+"</span><i class='dataYl' style='display:none;'>"+data[index].size+"</i></div></td></tr>";
	    					}
	    				}else{
	    					html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;发送了一条语音</img></td></tr>";
	    				}
	    			}else{
	   					/*html += "<tr><td >"+data[index].sendChatName+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].sendTime+"\n\t</td></tr>";*/
	   					html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].msgContent+"</td></tr>";
					}
	    		}
	    		$("#msg_content").html(html);
				ShowDiv(); 
	    	}
	    });
	}