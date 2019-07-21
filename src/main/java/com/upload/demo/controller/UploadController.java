package com.upload.demo.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/uploader")
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@GetMapping("/upload")
	public String upload() {
		return "uploadfile"; // 模板(视图)页面名称
	}

	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return "上传失败";
		}

		String fileName = file.getOriginalFilename(); // 上传后在服务端上的保存名
		String filePath = "D:\\Download\\"; // 在服务端上的保存路径(到文件名的上一层目录)
		File _file = new File(filePath + fileName); // 基于服务端上的完整保存路径 

		try {
			file.transferTo(_file);
			logger.info("上传成功!");

			return "<h1>上传成功!</h1>";
		} catch (IOException e) {
			logger.error("上传失败");
			e.printStackTrace();
		}
		return "上传失败";
	}

}
