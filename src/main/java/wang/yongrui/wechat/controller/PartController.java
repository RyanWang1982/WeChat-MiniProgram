/**
 * 
 */
package wang.yongrui.wechat.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.wechat.entity.web.Part;
import wang.yongrui.wechat.service.PartService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/part")
public class PartController {

	@Autowired
	private PartService partService;

	@PostMapping("/predefinedOnes")
	public ResponseEntity<String> createPredefinedSet(@RequestBody Set<Part> partSet) {
		partService.createPredefinedSet(partSet);
		return new ResponseEntity<>("Succeed", HttpStatus.CREATED);
	}

	@GetMapping("/allPredefinedOnes")
	public ResponseEntity<Set<Part>> retrieveAllPredefinedSet() {
		return new ResponseEntity<>(partService.retrieveAllPredefinedSet(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Part> putUpdat(@RequestBody Part part) {
		return new ResponseEntity<>(partService.putUpdate(part), HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<Part> patchUpdate(@RequestBody Part part) {
		return new ResponseEntity<>(partService.patchUpdate(part), HttpStatus.OK);
	}

}
