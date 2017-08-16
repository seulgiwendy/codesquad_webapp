package com.wheejuni.spring;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.wheejuni.spring.asdf.StorageService;
import com.wheejuni.spring.domain.User;
import com.wheejuni.spring.domain.UserRepository;

import handler.ImageRequestHandler;
import handler.RandomStringGenerationHandler;
import handler.UserLoginCheckHandler;
import handler.UserSessioninfoHandler;

@Controller
public class PhotoFormExperimental {

	public Image image;
	@Autowired
	UserRepository userRepo;

	@GetMapping("/photo/{index}")
	public String returnForm(@PathVariable long index, Model model, HttpSession session) {
		User user = userRepo.findOne(index);
		String dest = user.getProfilepath();
		User sessionedUser = (User)session.getAttribute("loginuser");
		if (UserLoginCheckHandler.LoginChecker(user.getUsername(), session) == false) {
			return "redirect:https://s3.ap-northeast-2.amazonaws.com/codesquad-webapp-original/" + dest;
		}
		model.addAttribute("userinfo", user);
		return "photo/upload";
	}

	@PostMapping("/photo/{uniqueId}")
	public String getUpload(@PathVariable long uniqueId, HttpServletRequest request,
			@RequestParam("pic") MultipartFile fileUpload, HttpSession session) throws Exception {
		
		User user = userRepo.findByUniqueId(uniqueId);
		User sessionedUser = (User)session.getAttribute("loginuser");
		
		if (fileUpload.isEmpty()) {
			return "photo/error";
		}

		if (sessionedUser == null) {
			System.out.println("세션된 유저 없음");
			return "users/editUserFailMessage";
		}
		
		if (sessionedUser.getUsername().equals(user.getUsername()) == false) {
			System.out.println("로그인정보와 타겟 유저 일치하지 않음");
			return "users/editUserFailMessage";
		}

		//UserSessioninfoHandler usif = new UserSessioninfoHandler(session);

		String username = user.getUsername();
		String filename = RandomStringGenerationHandler.randomStringFactory(15);

		System.out.println("picture POST upload initiated");
		byte[] uploadedBytes = fileUpload.getBytes();
		System.out.println(uploadedBytes.toString());
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(uploadedBytes));

		File outputFile = new File(filename + ".jpg");
		ImageIO.write(img, "jpg", outputFile);
		user.setProfilepath(filename);
		if (ImageRequestHandler.ImageInputRequest(img, filename)) {
			System.out.println("이미지 업로드에 성공함");
		}
		userRepo.save(user);
		return "redirect:/users";
	}

}
