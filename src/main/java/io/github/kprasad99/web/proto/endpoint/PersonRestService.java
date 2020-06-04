package io.github.kprasad99.web.proto.endpoint;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kprasad99.web.proto.orm.dao.PersonDao;
import io.github.kprasad99.web.proto.orm.model.Person;

@RestController
@RequestMapping("/api/person")
public class PersonRestService {

	@Autowired
	private PersonDao personDao;
	
	@GetMapping
	public List<Person> list(){
		return personDao.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Person> findById(@PathVariable("id")int id){
		return personDao.findById(id);
	}
	
}
