/**
 * 
 */
package wang.yongrui.wechat.controller;

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

import wang.yongrui.wechat.entity.web.User;
import wang.yongrui.wechat.service.UserService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
	}

	@GetMapping("/byWechatUnionId/{wechatUnionId}")
	public ResponseEntity<User> retrieveOneByWechatUnionId(@PathVariable String wechatUnionId) {
		return new ResponseEntity<>(userService.retrieveOneByWechatUnionId(wechatUnionId), HttpStatus.OK);
	}

	@GetMapping("/{id}/withPlan")
	public ResponseEntity<User> retrieveOneWithPlan(@PathVariable Long id) {
		return new ResponseEntity<>(userService.retrieveOneWithPlan(id), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> putUpdate(@RequestBody User user) {
		return new ResponseEntity<>(userService.putUpdate(user), HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<User> patchUpdate(@RequestBody User user) {
		return new ResponseEntity<>(userService.patchUpdate(user), HttpStatus.OK);
	}

}
