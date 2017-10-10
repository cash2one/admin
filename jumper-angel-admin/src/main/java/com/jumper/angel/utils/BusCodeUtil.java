package com.jumper.angel.utils;
/**业务代码工具，通过服务类型serviceType*/
public class BusCodeUtil {
	private BusCodeUtil(){}
	/**服务的类型,-1:免费咨询,0:义诊10153,1:图文咨询10150,2:医院问诊10152,3:胎心监护,4:在线问诊,5:私人医生10151,6:网络诊室,7:设备租赁,8:胎心解读*/
	public static String getBusCode(Integer serviceType) {
		String busCode = "";
		if (serviceType==-1) {busCode = "10154";}
		if (serviceType==0) {busCode = "10153";	}
		if (serviceType==1) {busCode = "10150";	}
		if (serviceType==2) {busCode = "10152";	}
		if (serviceType==5) {busCode = "10151";	}
		return busCode;
	}
}
