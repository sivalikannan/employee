package com.emp1.employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	private final Map<Long, Employee> store = new ConcurrentHashMap<>();
	private final AtomicLong seq = new AtomicLong(1000);

	public EmployeeService() {
		// seed data
		create(new Employee(null, "Sivali Kannan", "Software Engineer", "B.E CSE", "Chennai, TN", "sivali@example.com", "https://example.com/resume", "https://example.com/portfolio"));
		create(new Employee(null, "Asha Kumar", "HR Manager", "MBA", "Bengaluru, KA", "asha@example.com", "", ""));
	}

	public List<Employee> list() {
		return store.values().stream()
				.sorted(Comparator.comparing(Employee::getId))
				.toList();
	}

	public Optional<Employee> get(long id) {
		return Optional.ofNullable(store.get(id));
	}

	public Employee create(Employee input) {
		long id = seq.incrementAndGet();
		Employee emp = new Employee(
				id,
				input.getName(),
				input.getRole(),
				input.getQualification(),
				input.getAddress(),
				input.getEmail(),
				normUrl(input.getResume()),
				normUrl(input.getPortfolio())
		);
		store.put(id, emp);
		return emp;
	}

	public Optional<Employee> update(long id, Employee input) {
		if (!store.containsKey(id)) return Optional.empty();
		Employee updated = new Employee(
				id,
				input.getName(),
				input.getRole(),
				input.getQualification(),
				input.getAddress(),
				input.getEmail(),
				normUrl(input.getResume()),
				normUrl(input.getPortfolio())
		);
		store.put(id, updated);
		return Optional.of(updated);
	}

	public boolean delete(long id) {
		return store.remove(id) != null;
	}

	private static String normUrl(String v) {
		if (v == null) return "";
		return v.trim();
	}
}

