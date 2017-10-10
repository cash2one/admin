package com.jumper.angel.hospital.doctor.service;
/**评论相关业务*/
public interface IDoctorCommentsInfoService {

	/**通过CommentId关闭评论*/
	int deletComment(Integer commentId);

}
