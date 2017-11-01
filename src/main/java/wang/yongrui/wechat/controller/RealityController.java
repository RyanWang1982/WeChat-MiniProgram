/**
 * 
 */
package wang.yongrui.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.wechat.entity.web.Reality;
import wang.yongrui.wechat.entity.web.criteria.RealityCriteria;
import wang.yongrui.wechat.service.RealityService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/reality")
public class RealityController {

	@Autowired
	private RealityService realityService;

	@PostMapping
	public ResponseEntity<Reality> create(@RequestBody Reality reality) {
		return new ResponseEntity<>(realityService.create(reality), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reality> retrieveOne(@PathVariable Long id) {
		return new ResponseEntity<>(realityService.retrieveOne(id), HttpStatus.OK);
	}

	@PostMapping("/page")
	public ResponseEntity<Page<Reality>> retrievePage(@RequestBody RealityCriteria realityCriteria, Pageable pageable) {
		return new ResponseEntity<>(realityService.retrievePage(realityCriteria, pageable), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Reality> put(@RequestBody Reality reality) {
		return new ResponseEntity<>(realityService.putUpdate(reality), HttpStatus.OK);
	}

}
