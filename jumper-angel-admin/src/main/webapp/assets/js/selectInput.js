/**
 * Created by Apple on 2016/11/28 0028.
 */
//$(document).ready(function(){
//    $(".selButton").on("click",function(){
//        if($(".selInput").val()==""){
//            alert("请输入话题名称！");
//            //???
//            $(".select_txt").text()=="未选择";
//            //???
//        }if($(".selInput").val().length>13){
//            alert("请重新输入最多12个字符！");
//        }else{
//            var oSelInVal=$(".selInput").val();
//            $(".select_txt").text(oSelInVal);
//            $("#select_value").val(oSelInVal);
//        }
//    });
//});
$(document).ready(function(){
    $(".select_box").click(function(event){
        event.stopPropagation();
        $(this).find(".option").toggle();
        //$(this).parent().siblings().find(".option").hide();
    });
    $(document).click(function(event){
        var eo=$(event.target);
        if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length)
            $('.option').hide();
    });
    /*阻止.optionl里的input冒泡*/
    $(document).ready(function(){$('.selInput').click(function(event){event.stopPropagation();})});
    /*赋值给文本框*/
    $(".option a").click(function(){
        var value=$(this).text();
        $(this).parent().siblings(".select_txt").text(value);
        $("#select_value").val(value)
    });
});
