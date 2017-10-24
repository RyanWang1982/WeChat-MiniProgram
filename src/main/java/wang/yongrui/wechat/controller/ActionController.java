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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.wechat.entity.web.Action;
import wang.yongrui.wechat.service.ActionService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/action")
public class ActionController {

	@Autowired
	private ActionService actionService;

	@PostMapping("/customedOne")
	public ResponseEntity<Action> createCustomedOne(@RequestBody Action action) {
		return new ResponseEntity<>(actionService.createCustomedOne(action), HttpStatus.CREATED);
	}

	@GetMapping("/allCustomedOnesByUser/{userId}")
	public ResponseEntity<Set<Action>> retrieveAllCustomedSetByUser(@PathVariable Long userId) {
		return new ResponseEntity<>(actionService.retrieveAllCustomedSetByUser(userId), HttpStatus.OK);
	}

	@PutMapping("/customedOne")
	public ResponseEntity<Action> putUpdate(@RequestBody Action action) {
		return new ResponseEntity<>(actionService.putUpdate(action), HttpStatus.OK);
	}

	@PatchMapping("/customedOne")
	public ResponseEntity<Action> patchUpdate(@RequestBody Action action) {
		return new ResponseEntity<>(actionService.patchUpdate(action), HttpStatus.OK);
	}

}
