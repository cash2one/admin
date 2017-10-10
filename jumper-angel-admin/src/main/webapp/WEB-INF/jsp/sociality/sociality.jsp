<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 宝妈日记 - 日记管理</title>
    <link rel="stylesheet" href="css/css.css">
    <link rel="stylesheet" href="css/layer.css">
</head>
<body>
<div class="topUp">
    <ul class="topUp_ul">
        <li>天使医生后台管理</li>
        <li></li>
        <li>公告：您有一条来自汪星球的未读消息~~~!</li>
    </ul>
</div>
<div class="container">
	<div class="left_menu">
		<div class="left_menu_one">
			<ul class="left_menu_icon">
				<li class="iconbox">
					<a href="健康指导-1.html">
						<div class="iconimg home"></div>
						<p class="iconname">首页管理</p>
					</a>
				</li>
				<li class="iconbox active">
					<a href="宝妈日记-1.html">
						<div class="iconimg social2"></div>
						<p class="iconname">社交管理</p>
					</a>
				</li>
				<li class="iconbox">
					<a href="#">
						<div class="iconimg adver"></div>
						<p class="iconname">广告推送</p>
					</a>
				</li>
				<li class="iconbox">
					<a href="#">
						<div class="iconimg user"></div>
						<p class="iconname">用户反馈</p>
					</a>
				</li>
			</ul>
			<img src="images/left_menu_bgimg.png">
		</div>
	</div>
	<div class="list_menu_container">
		<div class="list_menu">
			<h3><i class="iconlist huati positionIicon"></i>话题组<i class="iconimg arrows positionIicon-r"></i></h3>
			<ul>
				<li><a href="话题组-话题管理.html">话题管理</a></li>
				<li><a href="话题组-成员管理.html">成员管理</a></li>
				<li><a href="话题组-帖子管理.html">帖子管理</a></li>
			</ul>
			<h3><i class="iconlist quanzi positionIicon"></i>交流圈<i class="iconimg arrows positionIicon-r"></i></h3>
			<ul>
				<li ><a href="交流圈-圈子管理.html">圈子管理</a></li>
				<li ><a href="交流圈-运营管理-账号管理.html">成员管理</a></li>
			</ul>
			<h3><i class="iconlist diary positionIicon"></i>宝妈日记 <i class="iconimg arrows positionIicon-r"></i></h3>
			<ul>
				<li><a href="宝妈日记-1.html" class="selected">日记管理</a></li>
			</ul>
			<h3><i class="iconlist guide positionIicon"></i>其他<i class="iconimg arrows positionIicon-r"></i></h3>
			<ul>
				<li ><a href="交流圈-举报中心-被举报的帖子.html">举报中心</a></li>
				<li ><a href="交流圈-运营管理-账号管理.html">运营管理</a></li>
			</ul>
		</div>
	</div>
	<div class="content">
		<p class="toptitle">首页管理 > 宝妈日记 > 日记管理</p>
		
		<div class="content-search-top" >
			<p class="top-text float-l" style="margin: 0; padding-left:15px ;">宝妈日记是一项用户私人属性的功能，可以根据用户名查找对应用户的日记。</p>
			<button class="search-icon float-r"></button>
			<input class="shortinput float-r" placeholder="输入用户名" style="width: 230px;" />
		</div>
		<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
			<div class="datalist margin-r float-l yellowback">
				<p class="datanum">1235</p>
				<p class="datatitle">当前日记总数</p>
			</div>
			<div class="datalist margin-r float-l greenback">
				<p class="datanum">100</p>
				<p class="datatitle">近30天新增日记数</p>
			</div>
			<div class="datalist float-l blueback">
				<p class="datanum">1000</p>
				<p class="datatitle">当前日记用户数</p>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<p class="diarytitle">日记数量最多的5名用户情况</p>
		<div class="panel margin-b">
			<table class="table"> 
				<thead>
					<tr>
						<th>用户名</th>
						<th>日记数量</th>
						<th>最近发表时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>50</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<a href="宝妈日记-2.html" class="red_btn_small">查看日记</a>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>60</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<a href="宝妈日记-2.html" class="red_btn_small">查看日记</a>
						</td>
					</tr>
					<tr>
						<td>3</td>
						<td>60</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<a href="宝妈日记-2.html" class="red_btn_small">查看日记</a>
						</td>
					</tr>
					<tr>
						<td>4</td>
						<td>60</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<a href="宝妈日记-2.html" class="red_btn_small">查看日记</button>
						</td>
					</tr>
					<tr>
						<td>5</td>
						<td>60</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<a href="宝妈日记-2.html" class="red_btn_small">查看日记</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<p class="diarytitle">最新发布的5条日记</p>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>用户名</th>
						<th>发表内容</th>
						<th>发表时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>最多显示20个字……</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<button class="red_btn_small" onClick="ShowDiv()">查看详情</button>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>最多显示20个字……</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<button class="red_btn_small" onClick="ShowDiv()">查看详情</button>
						</td>
					</tr>
					<tr>
						<td>3</td>
						<td>最多显示20个字……</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<button class="red_btn_small" onClick="ShowDiv()">查看详情</button>
						</td>
					</tr>
					<tr>
						<td>4</td>
						<td>最多显示20个字……</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<button class="red_btn_small" onClick="ShowDiv()">查看详情</button>
						</td>
					</tr>
					<tr>
						<td>5</td>
						<td>最多显示20个字……</td>
						<td>2016/10/28 9:20:48</td>
						<td>
							<button class="red_btn_small" onClick="ShowDiv()">查看详情</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
	</div>
</div>

<!--------------------  弹出层     ---------------------- --->
    <div class="normal_form">
    	<form>
    		<ul>
    			<li>
    				<label class="label">用户名 </label><input type="text" class="shortinput"/>
    			</li>
    			<li>
    				<label class="label">发布时间 </label><input type="text" class="shortinput"/>
    			</li>
    			<li>
    				<label class="label label-t">日记内容 </label><textarea class="pop_textarea"></textarea>
    			</li>
    			<li>
    				<label class="label label-t">配图 </label>
    				<img class="margin-r" src="images/imgdefault.png" />
    				<img class="margin-r" src="images/imgdefault.png" />
    				<img src="images/imgdefault.png" />
    			</li>
    			<li>
    				<label class="label label-t">日记状态 </label>正常
    			</li>
    		</ul>
		</form>
	</div>


<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/layer.js"></script>
<script src="js/style.js"></script>
<script>
function ShowDiv(){
	layer.open({
		title:'日记内容 - 查看详情',
  		type: 1,
  		area: ['50%'], //宽高
  		content: $('.normal_form')
	});
}
</script>
</body>
</html>