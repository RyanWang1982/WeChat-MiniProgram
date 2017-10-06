/**
 * 
 */
package wang.yongrui.wechat.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<String> createPredefinedPartSet(@RequestBody Set<Part> partSet) {
		partService.createPredefinedPartSet(partSet);
		return new ResponseEntity<>("Succeed", HttpStatus.CREATED);
	}

	@PostMapping("/customedOnes/{userId}")
	public ResponseEntity<String> createCustomedPartSet(@RequestBody Set<Part> partSet, @PathVariable Long userId) {
		partService.createCustomedPartSet(partSet, userId);
		return new ResponseEntity<>("Succeed", HttpStatus.CREATED);
	}

	@GetMapping("/allPredefinedOnes")
	public ResponseEntity<Set<Part>> getAllPredefinedPartSet() {
		return new ResponseEntity<>(partService.retrieveAllPredefinedPartSet(), HttpStatus.OK);
	}

	@GetMapping("/allCustomedOnes/{userId}")
	public ResponseEntity<Set<Part>> getAllCustomedPartSet(@PathVariable Long userId) {
		return new ResponseEntity<>(partService.retrieveAllCustomedPartSet(userId), HttpStatus.OK);
	}

}
