package com.henry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.henry.entity.PopData;
import com.henry.repository.PopDataRepository;
import com.henry.service.IPopDataService;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "https://cpcho.github.io/")
public class PopDataController {
	@Autowired
	private IPopDataService popDataService;
	@Autowired
	private PopDataRepository popDataRepository;

	@GetMapping("pop-data")
	public ResponseEntity<PopData> getPopDataById(@RequestParam("id") String id) {
		PopData pd = popDataService.getPopDataById(Integer.parseInt(id));
		return new ResponseEntity<PopData>(pd, HttpStatus.OK);
	}
	
	/*
	@GetMapping("all-pop-data")
	public ResponseEntity<List<PopData>> getAllPopData() {
		List<PopData> list = popDataService.getAllPopData();
		return new ResponseEntity<List<PopData>>(list, HttpStatus.OK);
	}
 	*/
	
	@PostMapping("pop-data")
	public ResponseEntity<Void> createPopData(@RequestBody PopData pd, UriComponentsBuilder builder) {
		boolean flag = popDataService.createPopData(pd);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/pop-data?id={id}").buildAndExpand(pd.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("pop-data")
	public ResponseEntity<PopData> updatePopData(@RequestBody PopData article) {
		popDataService.updatePopData(article);
		return new ResponseEntity<PopData>(article, HttpStatus.OK);
	}

	@DeleteMapping("pop-data")
	public ResponseEntity<Void> deletePopData(@RequestParam("id") String id) {
		popDataService.deletePopData(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<PopData> retrieveAllPopData() {
		return popDataRepository.findAll();
	}
}