package com.babyshop.babyshop.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import com.babyshop.babyshop.repositories.ImageRepository;

@Service
public class ImageService {
	@Autowired
	ImageRepository imageRepository;
	// Thư mục lưu trữ các ảnh product
	private final Path storageFolderProduct = Paths.get("src/main/resources/static/image/product-image");
	// Thư mục lưu trữ các ảnh brand
	private final Path storageFolderBrand = Paths.get("src/main/resources/static/image/brand-image");
	// Thư mục lưu trữ avatar người dùng
	private final Path storageFolderAvatar = Paths.get("avatar");

	public ImageService() {
		try {
			Files.createDirectories(storageFolderProduct);
			Files.createDirectories(storageFolderBrand);
			Files.createDirectories(storageFolderAvatar);
		} catch (IOException ex) {
			throw new RuntimeException("Cannot initalize storage", ex);
		}
	}

//	//Trả về toàn bộ ảnh trong thư mục
	public Stream<Path> loadAll() {
		try {
			Stream<Path> paths = Files.walk(this.storageFolderProduct, 1)
					.filter(path -> !path.equals(this.storageFolderProduct))
					.map(path -> this.storageFolderProduct.relativize(path));
			return paths;
			// this.storageFolder::relativize = path -> this.storageFolder.relativize(path)
		} catch (IOException e) {
			throw new RuntimeException("Failed to load stored files", e);
		}
	}

	// Đọc nội dung file và trả về byte[]
	public byte[] readFileContentProduct(String fileName) {
		try {
			Path file = storageFolderProduct.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			} else {
				throw new RuntimeException("Could not read file: " + fileName);
			}
		} catch (IOException exception) {
			throw new RuntimeException("Could not read file: " + fileName, exception);
		}
	}

	// Đọc nội dung file và trả về byte[]
	public byte[] readFileContentBrand(String fileName) {
		try {
			Path file = storageFolderBrand.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			} else {
				throw new RuntimeException("Could not read file: " + fileName);
			}
		} catch (IOException exception) {
			throw new RuntimeException("Could not read file: " + fileName, exception);
		}
	}
	//Sinh ra một tên file ngẫu nhiên có độ dài 32 kí tự
	public String randFileName() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public String storeAvatar(UrlResource urlResource) {
		String generatedFileName = randFileName();
		generatedFileName = generatedFileName + ".png";
		try {
			//Lấy ra Path đường dẫn tới vị trí lưu file
			Path destinationFilePath = this.storageFolderAvatar.resolve(generatedFileName)
					.normalize().toAbsolutePath();
			//kiển tra chắc chắn là file sẽ được lưu vào đúng vị trí
			if(!destinationFilePath.getParent().equals(this.storageFolderAvatar.toAbsolutePath())) {
				throw new RuntimeException("Cannot store file outside current directory");
			}
			//lưu file
			try(InputStream inputStream = urlResource.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedFileName;
	} 
	
	public byte[] readFileContentAvatar(String fileName) {
		try {
			Path file = storageFolderAvatar.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			} else {
				throw new RuntimeException("Could not read file: " + fileName);
			}
		} catch (IOException exception) {
			throw new RuntimeException("Could not read file: " + fileName, exception);
		}
	}

}
