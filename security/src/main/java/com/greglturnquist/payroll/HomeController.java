/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.hateoas.*;


import java.util.List;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Controller
public class HomeController {
	//private final EmployeeRepository employeeRepository;

	//	public HomeController(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}
	@Autowired EmployeeRepository  employeeRepository;
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	//http://localhost:8080/advance?search=lastName:B*&size=2&page=1&sort=firstName,desc
	@GetMapping("/advance")
	public ResponseEntity<Page<Employee>> searchForEmployees(@SearchSpec Specification<Employee> specs, Pageable pageable) {
		//final Page<Employee> page = new PageImpl<>(List<Employee>);
		return new ResponseEntity<>(employeeRepository.findAll(Specification.where(specs), pageable), HttpStatus.OK);
	}




}
// end::code[]