package com.david.springmvc.restful.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.david.springmvc.restful.entities.Department;
import com.david.springmvc.restful.entities.Employee;
@Component
public class EmployeeConverter implements Converter<String, Employee> {

	public Employee convert(String source) {
		
		if(source!=null &&!"".equals(source)){
			//GG-gg@gg.com-0-105
			String[] values=source.split("-");
			if(values!=null && values.length==4){
				Employee emp=new Employee();
				emp.setLastName(values[0]);
				emp.setEmail(values[1]);
				emp.setGender(Integer.valueOf(values[2]));
				Department dept=new Department();
				dept.setId(Integer.valueOf(values[3]));
				emp.setDepartment(dept);
				System.out.println(source+"converter --"+emp);
				return emp;
			}
		}
		return null;
	}

}
