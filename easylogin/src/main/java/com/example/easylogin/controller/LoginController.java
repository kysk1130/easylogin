package com.example.easylogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.easylogin.model.dao.UserRepository;
import com.example.easylogin.model.entity.User;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepos;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			
			/* Stringのデータを戻り値として返すloginというメソッドを作成している。
			   引数は3つ、最初の2つはアノテーション(@RequestParam)付与。
			   クライアントからのリクエストであることを意味し、HTML側で定義された
			   name属性を指定することによって判断する */
			
			Model m) {
		
			/* Modelはレスポンスとしてクライアント側に返すためのオブジェクト。
			   addAttributeメソッドを使い、"message"というキー文字列に対して
			   ログイン結果によって分岐するメッセージを値に設定している。 */
		
		String message = "welcome! ";
		
		List<User> users = userRepos.findByUserNameAndPassword(userName, password);
		if (users.size() > 0) {
			User user = users.get(0);
			message += user.getFullName();
		} else {
			message += "guest";
		}
		
		m.addAttribute("message", message);
		
		return "login";
	}

}
