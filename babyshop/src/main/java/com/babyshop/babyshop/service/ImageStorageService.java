package com.babyshop.babyshop.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.babyshop.babyshop.controller.ImageController;

@Service
public class ImageStorageService {
	private final Path storageFolder = Paths.get("src/main/resources/static/image/product-image");

	public ImageStorageService() {
		try {
			Files.createDirectories(storageFolder);
		} catch (IOException ex) {
			throw new RuntimeException("Cannot initalize storage", ex);
		}
	}

	//Trả về toàn bộ ảnh trong thư mục
	public Stream<Path> loadAll() {
		try {	
			Stream<Path> paths =  Files.walk(this.storageFolder, 1)
					.filter(path -> !path.equals(this.storageFolder))
					.map(path -> this.storageFolder.relativize(path));
			return paths;
			//this.storageFolder::relativize = path -> this.storageFolder.relativize(path)
		} catch (IOException e) {
			throw new RuntimeException("Failed to load stored files", e);
		}
	}

	//Đọc nội dung file và trả về byte[]
	public byte[] readFileContent(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
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
	
	
	//load tất cả các đường dẫn file có tên trong list	
		public List<String> getURIImage(List<String> imgName){
			try {
	            List<String> paths = loadAll()
	                    .map(path -> {
	                        String uriPath = MvcUriComponentsBuilder.fromMethodName(ImageController.class,
	                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
	                        return uriPath;
	                    })
	                    .collect(Collectors.toList());
	            List<String> uris = new ArrayList<>();
	            String uri = "";
	            for (int i = 0; i < imgName.size(); i++) {
					for (int j = 0; j < paths.size(); j++) {
						if(paths.get(j).contains(imgName.get(0))) {
							uri = paths.get(j);
						}
					}
					uris.add(uri);
				}
	            return uris;
	            
	        }catch (Exception exception) {
	            exception.printStackTrace();
	        }
			return null;
		}
}
