package jp.co.cybermissions.itspj.java.empmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

  @GetMapping("")
  public String getList() {
    return "emp/list";
  }
}
