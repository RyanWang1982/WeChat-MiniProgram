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

import wang.yongrui.wechat.entity.web.Plan;
import wang.yongrui.wechat.entity.web.criteria.PlanCriteria;
import wang.yongrui.wechat.service.PlanService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private PlanService planService;

	@PostMapping
	public ResponseEntity<Plan> create(@RequestBody Plan plan) {
		return new ResponseEntity<>(planService.create(plan), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Plan> retrieveOne(@PathVariable Long id) {
		return new ResponseEntity<>(planService.retrieveOne(id), HttpStatus.OK);
	}

	@PostMapping("/page")
	public ResponseEntity<Page<Plan>> retrievePage(@RequestBody PlanCriteria planCriteria, Pageable pageable) {
		return new ResponseEntity<>(planService.retrievePage(planCriteria, pageable), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Plan> putUpdate(@RequestBody Plan plan) {
		return new ResponseEntity<>(planService.putUpdate(plan), HttpStatus.OK);
	}

}
