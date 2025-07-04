package com.deliverytech.delivery.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController

public class HelloController{

	@GetMapping("/")

	public String hello(){

		return "Olá mundo do Spring Boot!!!";

	}
	@GetMapping("/api/status")
	public String status(){
		return "Aplicação funcionando!";
}

}
