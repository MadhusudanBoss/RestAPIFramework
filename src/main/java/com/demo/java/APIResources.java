package com.demo.java;

public enum APIResources {
	//Enum in java is a special class which is a collection of  constants or methods in it. Enum treats below code as methods by default.
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	//the above methods expects a constructor to be defined with one parameter and resource as parameter.
	
	private String resource;
	
	APIResources(String resource) {
		this.resource=resource;
	}

	
	public String getResource() {
		return resource;
	}
}
