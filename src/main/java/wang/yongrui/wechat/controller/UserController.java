/**
 * 
 */
package wang.yongrui.wechat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import wang.yongrui.wechat.entity.web.User;
import wang.yongrui.wechat.property.WeChatMiniProgramProperties;
import wang.yongrui.wechat.service.UserService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private WeChatMiniProgramProperties weChatMiniProgramProperties;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@GetMapping("/wechatMPOpenId/{jscode}")
	public ResponseEntity<String> getUserOpenId(@PathVariable String jscode) {
		StringBuffer urlStrBuffer = new StringBuffer(
				"https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code");
		urlStrBuffer.append("&appid=").append(weChatMiniProgramProperties.getAppId());
		urlStrBuffer.append("&secret=").append(weChatMiniProgramProperties.getAppSecret());
		urlStrBuffer.append("&js_code=").append(jscode);

		String responseString = restTemplate.getForObject(urlStrBuffer.toString(), String.class);

		Map<String, String> openIdJsonMap = new HashMap<>();
		try {
			openIdJsonMap = new ObjectMapper().readValue(responseString, openIdJsonMap.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String result = null;
		if (null != openIdJsonMap.get("openid")) {
			result = openIdJsonMap.get("openid");
		} else {
			result = openIdJsonMap.get("errmsg");
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/wechatUnion/{jscode}")
	public ResponseEntity<String> getUserUnionId(@PathVariable String jscode) {
		StringBuffer urlStrBuffer = new StringBuffer(
				"https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code");
		urlStrBuffer.append("&appid=wx873bdbf26c4f9f6d");
		urlStrBuffer.append("&secret=9755898e8ae450ea05892701e5a70437");
		urlStrBuffer.append("&js_code=").append(jscode);

		String responseString = restTemplate.getForObject(urlStrBuffer.toString(), String.class);

		Map<String, String> openIdJsonMap = new HashMap<>();
		try {
			openIdJsonMap = new ObjectMapper().readValue(responseString, openIdJsonMap.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String result = null;
		if (null != openIdJsonMap.get("openid")) {
			result = openIdJsonMap.get("openid") + " --- " + openIdJsonMap.get("unionid") + " --- "
					+ openIdJsonMap.get("session_key");
		} else {
			result = openIdJsonMap.get("errmsg");
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "", notes = "gender, dateOfBirth should not be null; loginName, wechatUnionId, wechatMPOpenId, mobileNumber, email, avatarUrl should be unique")
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
	}

	@GetMapping("/byWechatMPOpenId/{wechatMPOpenId}")
	public ResponseEntity<User> retrieveOneByWechatMPOpenId(@PathVariable String wechatMPOpenId) {
		return new ResponseEntity<>(userService.retrieveOneByWechatMPOpenId(wechatMPOpenId), HttpStatus.OK);
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
