package org.fhk.service;

import org.springframework.web.multipart.MultipartFile;
import org.fhk.utils.PictureResult;

public interface PictureUploadService {
	public PictureResult pictureUpload(MultipartFile fileName);
}
