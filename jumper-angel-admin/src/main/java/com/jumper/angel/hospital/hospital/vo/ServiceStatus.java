package com.jumper.angel.hospital.hospital.vo;

public enum ServiceStatus {
	//1常规监护，2实时监护，3院内监护，4医院问诊，5营养管理，6孕妇学校，7高危管理，8母子手册
	/**0：全部服务*/
	ALLSERVICE(0, "全部服务"),
	/**1：常规监护*/
	CONVENTION(1, "常规监护"),
	/**2：实时监护*/
	REAL_TIME(2, "实时监护"),
	/**3：院内监护*/
	HOSPITAL(3, "院内监护"),
	/**4：医院问诊*/
	CONSULTANT(4, "医院问诊"),
	/**5：营养管理*/
	NUTRITION(5, "营养管理"),
	/**6：孕妇学校*/
	SCHOOL(6, "孕妇学校"),
	/**7：高危管理*/
	HIGH_RISK(7,"高危管理"),
	/**8：母子手册*/
	MOTHER_AND_SON(8,"母子手册");
	private int type; 
	private String name;
	private ServiceStatus(int type, String name) {
		this.type = type;
		this.name = name;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getName(int index){
		ServiceStatus[] docs = ServiceStatus.values();
		for(ServiceStatus type : docs){
			if(type.type == index){
				return type.name;
			}
		}
		return null;
		
	}

}
