<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/head.jsp"></jsp:include>
<!-- left 大模块 -->
<!-- <style>
	.list_menu_container{display:none;}
</style>  -->
<div class="left_menu">
    <div class="left_menu_one">
       <ul class="left_menu_icon">
       		<li class="iconbox active" id="dataManage">
           	<a href="${pageContext.request.contextPath}/userStatistics/forwardUserStatistics" target="list">
               <div class="iconimg data2"></div>
               <p class="iconname">数据中心</p>
            </a>
           </li>
           <li class="iconbox" id="homeManage">
           	<a href="${pageContext.request.contextPath}/news/forwardNewsInformationList" target="list">
               <div class="iconimg home2"></div>
               <p class="iconname">内容管理</p>
            </a>
           </li>
           <li class="iconbox" id="socialityManage">
           	<a href="${pageContext.request.contextPath}/sociality/topicManager/topicIndex" target="list">
               <div class="iconimg social"></div>
               <p class="iconname">社交管理</p>
            </a>
           </li>
           <li class="iconbox" id="consultantManage">
				<a href="${pageContext.request.contextPath}/hospital/doctor/consultantAll"  target="list">
					<div class="iconimg business2"></div>
					<p class="iconname">业务管理</p>
				</a>
			</li>
           <li class="iconbox" id="hospitalManage">
				<a href="${pageContext.request.contextPath}/hospital/hospitalList/hospitalList"  target="list">
					<div class="iconimg hospital"></div>
					<p class="iconname">医院管理</p>
				</a>
			</li>
           <!-- <li class="iconbox" id="advertisement">
           	<a href="#">
               <div class="iconimg adver"></div>
               <p class="iconname">广告推送</p>
            </a>
           </li>
           <li class="iconbox" id="userFeedback">
           	<a href="#">
               <div class="iconimg user"></div>
               <p class="iconname">用户反馈</p>
            </a>
           </li> -->
       </ul>
       <img src="${pageContext.request.contextPath}/assets/images/left_menu_bgimg.jpg">
    </div>
</div>
<!-- left 小模块-->
<div class="list_menu_container" id="dataDiv">
	<div class="list_menu dataManage">
		<h3><i class="iconlist guide positionIicon"></i>用户统计<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/userStatistics/forwardUserStatistics" class="selected" target="list">数据概览</a></li>
		</ul>
		<h3><i class="iconlist preDate positionIicon"></i>推广统计<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/promotStatistics/forwardPromotStatistics" target="list">医院总表</a></li>
			<li ><a href="${pageContext.request.contextPath}/promotStatistics/forwardHopsitalBindPage" target="list">院区查询</a></li>
		</ul>
	</div>
</div> 
 <div class="list_menu_container" style="display: none;" id="homeDiv">
	<div class="list_menu homeManage">
		<h3><i class="iconlist information positionIicon"></i>资讯订阅<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/news/forwardNewsInformationList" class="selected" target="list">文章管理</a></li>
			<li ><a href="${pageContext.request.contextPath}/news/forwardNewsChanelsList" target="list">频道管理</a></li>
		</ul>
		<h3><i class="iconlist guide positionIicon"></i>健康指导<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/baby/forwordBaby" target="list">宝宝发育变化</a></li>
			<li><a href="javascript:" target="list">孕周健康提示</a></li>
			<li><a href="${pageContext.request.contextPath}/baby/forwordBabyStatusImage" target="list">孕周宝宝状态图</a></li>
		</ul>
		<h3><i class="iconlist preDate positionIicon"></i>孕期管理<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/pregnancy/findPregAnteExaminationInfoList" target="list">产检管理</a></li>
			<li ><a href="${pageContext.request.contextPath}/pregnancy/forwardPregAnteExamItemInfo" target="list">产检项目管理</a></li>
		</ul>
		<h3><i class="iconlist play positionIicon"></i>播放器<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/music/forwordMusic" target="list">电台内容管理</a></li>
		</ul>
		<h3><i class="iconlist encyclopedia positionIicon"></i>孕期百科<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/encyclopedia/forwardQuestionClassOrTypeList?type=questionClass" target="list">模块与标签</a></li>
			<li><a href="${pageContext.request.contextPath}/encyclopedia/forwardQuestionList" target="list">内容库管理</a></li>
		</ul>
		<h3><i class="iconlist information positionIicon"></i>广告管理<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/advertise/forwardAdvertiseList" target="list">广告列表</a></li>
		</ul>
	</div>
</div> 
<div class="list_menu_container" style="display: none;" id="socialityDiv">
	<div class="list_menu socialityManage">
		<h3><i class="iconlist huati positionIicon"></i>话题组<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/sociality/topicManager/topicIndex" class="selected" target="list">小组管理</a></li>
			<li><a href="${pageContext.request.contextPath}/sociality/userTopicManager/userTopicIndex" target="list">成员管理</a></li>
			<li><a href="${pageContext.request.contextPath}/sociality/debatepostManager/debatepostIndex" target="list">帖子管理</a></li>
			<li><a href="${pageContext.request.contextPath}/sociality/commentManager/commentIndex" target="list">评论管理</a></li>
		</ul>
		<h3><i class="iconlist quanzi positionIicon"></i>交流圈<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/sociality/group/init" target="list">圈子管理</a></li>
			<li ><a href="${pageContext.request.contextPath}/group/user/groupinit" target="list">成员管理</a></li>
		</ul>
		
		<h3><i class="iconlist diary positionIicon"></i>宝妈日记 <i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/sociality/diary/init"  target="list">日记管理</a></li>
		</ul>
		<h3><i class="iconlist guide positionIicon"></i>其他<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/sociality/reprot/debatepost/index" target="list">举报中心</a></li>
			<li ><a href="${pageContext.request.contextPath}/sociality/recycleManager/recycleDebatepost" target="list">回收站</a></li>
			<!-- <li ><a href="交流圈-运营管理-账号管理.html">运营管理</a></li> -->
		</ul>
	</div>
</div>
<div class="list_menu_container" style="display: none;" id="consultantDiv">
	<div class="list_menu consultantManage">
		<h3><i class="iconlist consult positionIicon"></i>咨询管理<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/hospital/doctor/consultantAll" class="selected" target="list">全部咨询</a></li>
			<li ><a href="${pageContext.request.contextPath}/hospital/doctor/problemBase" target="list">问题库</a></li>
			<li ><a href="${pageContext.request.contextPath}/hospital/doctor/myAnswering" target="list">我的解答</a></li>
		</ul>
		<h3><i class="iconlist consult positionIicon"></i>短信管理<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/verified/forwordUserVerifiedCode" target="list">验证码管理</a></li>
		</ul>
		<h3><i class="iconlist consult positionIicon"></i>开放用户管理<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/business/businessFromJsp" target="list">用户列表</a></li>
		</ul>
		<h3><i class="iconlist preDate positionIicon"></i>短信统计<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/sms/forwardSmsStatistics" target="list">医院列表</a></li>
		</ul>
	</div>
</div>
<%-- <div class="list_menu_container" style="display: none;" id="hospitalDiv">
	<div class="list_menu hospitalManage">
		<h3><i class="iconlist consult positionIicon"></i>医院维护<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/hospital/doctor/consultantAll" class="selected" target="list">医院列表</a></li>
			<li ><a href="${pageContext.request.contextPath}/hospital/doctor/problemBase" target="list">主页设置</a></li>
		</ul>
		<h3><i class="iconlist consult positionIicon"></i>科室维护<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/verified/forwordUserVerifiedCode" target="list">科室列表</a></li>
		</ul>
	</div>
</div> --%>
<div class="list_menu_container" style="display: none;" id="hospitalDiv">
	<div class="list_menu hospitalManage">
		<h3><i class="iconlist hospital positionIicon"></i>医院维护<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/hospital/hospitalList/hospitalList" class="selected" target="list">医院列表</a></li>
			<li ><a href="${pageContext.request.contextPath}/hospital/homepage/basicFunction" target="list">主页设置</a></li>
		</ul>
		<h3><i class="iconlist hospital positionIicon"></i>科室维护<i class="iconimg arrows positionIicon-r"></i></h3>
		<ul>
			<li ><a href="${pageContext.request.contextPath}/hospital/majorList/majorList" target="list">科室列表</a></li>
		</ul>
	</div>
</div>

<!-- 自动收缩 -->
<!-- <button id="list_menu_btn"><</button> -->
<script type="text/javascript">
//处理左边菜单
$(document).ready(function(){
	
});
</script>
