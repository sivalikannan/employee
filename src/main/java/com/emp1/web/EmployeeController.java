package com.emp1.web;

import com.emp1.employee.Employee;
import com.emp1.employee.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("employees", employeeService.list());
		return "employees";
	}

	@GetMapping("/new")
	public String newForm(Model model) {
		model.addAttribute("mode", "create");
		model.addAttribute("employee", new Employee());
		return "employee-form";
	}

	@PostMapping
	public String create(@Valid Employee employee, BindingResult bindingResult, Model model, RedirectAttributes ra) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("mode", "create");
			return "employee-form";
		}
		employeeService.create(employee);
		ra.addFlashAttribute("toast", "Employee created successfully");
		return "redirect:/employees";
	}

	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable long id, Model model, RedirectAttributes ra) {
		return employeeService.get(id)
				.map(emp -> {
					model.addAttribute("mode", "edit");
					model.addAttribute("employee", emp);
					return "employee-form";
				})
				.orElseGet(() -> {
					ra.addFlashAttribute("toast", "Employee not found");
					return "redirect:/employees";
				});
	}

	@PostMapping("/{id}")
	public String update(@PathVariable long id, @Valid Employee employee, BindingResult bindingResult, Model model, RedirectAttributes ra) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("mode", "edit");
			employee.setId(id);
			return "employee-form";
		}
		boolean ok = employeeService.update(id, employee).isPresent();
		ra.addFlashAttribute("toast", ok ? "Employee updated successfully" : "Employee not found");
		return "redirect:/employees";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable long id, RedirectAttributes ra) {
		boolean ok = employeeService.delete(id);
		ra.addFlashAttribute("toast", ok ? "Employee deleted" : "Employee not found");
		return "redirect:/employees";
	}
}

