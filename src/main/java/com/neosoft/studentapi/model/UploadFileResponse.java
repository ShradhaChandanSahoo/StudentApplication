package com.neosoft.studentapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse {
	
	private String fileName;
	private String fileUrl;
	private String fileType;
	private Long fileSize;

}
