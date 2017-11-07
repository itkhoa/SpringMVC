package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.UserDao;
import com.spring.model.User;

@RestController
public class HomeController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/")
	public ModelAndView handleRequest() throws Exception {
		List<User> listUser = userDao.list();
		System.out.println(listUser.get(0).getEmail());
		ModelAndView model = new ModelAndView("UserList");
		model.addObject("userList", listUser);
		return model;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
		return userDao.list().isEmpty() ? new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT) : new ResponseEntity<List<User>>(userDao.list(), HttpStatus.OK);
	}
}
