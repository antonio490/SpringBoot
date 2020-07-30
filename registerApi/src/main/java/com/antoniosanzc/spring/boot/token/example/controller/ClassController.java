package com.antoniosanzc.spring.boot.token.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.antoniosanzc.spring.boot.token.example.model.Clase;
import com.antoniosanzc.spring.boot.token.example.model.Dao;

public class ClassController {

	@Autowired
	private Dao dao;

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getInventoryClass")
	@ResponseBody
	public List<Clase> getInventoryClass(){
		
		List<Clase> claseList = dao.claseList();
		return claseList;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value= "/getInventoryClass", params = "id")
	@ResponseBody
	public List<Clase> getInventoryClass(@RequestParam("id") int id){
		
		List<Clase> claseList = dao.claseListById(id);
		return claseList;
	}
}
