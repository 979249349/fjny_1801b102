package org.fhk.service.impl;

import java.awt.PageAttributes.OriginType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.fhk.service.PictureUploadService;
import org.fhk.utils.FtpUtil;
import org.fhk.utils.IDUtils;
import org.fhk.utils.PictureResult;

@Service
public class PictureUploadServiceImpl implements PictureUploadService {

	String host="127.0.0.1";
	int port=21;
	String username = "fhk";
	String password = "123456";
	
	@Override
	public PictureResult pictureUpload(MultipartFile uploadfile) {

		PictureResult result = new PictureResult();
		try {
			//判断是否为空
			if(null == uploadfile || uploadfile.isEmpty()) {
				result.setError(1);
				result.setMessage("上传图片为空");
				return result;	
			}
			//获取文件名
			String originalFilename = uploadfile.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			DateTime dateTime = new DateTime();
			String filePath = dateTime.toString("/yyyy/MM/dd");
			String filename = IDUtils.genImageName() + ext;
			InputStream input = uploadfile.getInputStream();
			FtpUtil.uploadFile(host, port, username, password, "/", filePath, filename, input);
			String url = "http://localhost:8888" + filePath +"/"+ filename ;
			result.setError(0);
			result.setUrl(url);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result.setError(1);
			result.setMessage(ExceptionUtil.unwrapThrowable(e).toString());
		}
		return result;
	}

}
