/**
 * Created by Administrator on 2017/3/13 0013.
 */
//日期显示
window.onload=function () {
    setInterval(dateTitle,1000);
};
function dateTitle() {
    var myTime = new Date();
    var iYear = myTime.getFullYear();
    var iMonth = myTime.getMonth()+1;
    var iDate = myTime.getDate();
    var iWeek = myTime.getDay();
    var iHours = myTime.getHours();
    var iMin = myTime.getMinutes();
    var iSec = myTime.getSeconds();
    var str='';
    var dateT = document.getElementById('dateT');
    if(iWeek === 0)iWeek = '星期日';
    if(iWeek === 1)iWeek = '星期一';
    if(iWeek === 2)iWeek = '星期二';
    if(iWeek === 3)iWeek = '星期三';
    if(iWeek === 4)iWeek = '星期四';
    if(iWeek === 5)iWeek = '星期五';
    if(iWeek === 6)iWeek = '星期六';
    if (iMonth >= 1 && iMonth <= 9) {
        iMonth = "0" + iMonth;
    }
    if (iDate >= 0 && iDate <= 9) {
        iDate = "0" + iDate;
    }
    if (iHours >= 0 && iHours <= 9) {
        iHours = "0" + iHours;
    }
    if (iMin >= 0 && iMin <= 9) {
        iMin = "0" + iMin;
    }
    if (iSec >= 0 && iSec <= 9) {
        iSec = "0" + iSec;
    }
    dateT.innerHTML = iYear+'年'+iMonth+'月'+iDate+'日 '+iHours+':'+iMin+':'+iSec+' '+iWeek;
}