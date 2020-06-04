package io.github.kprasad99.web.proto.endpoint;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kprasad.person.proto.PersonProto.Person;
import io.github.kprasad.person.proto.PersonProto.Persons;
import io.github.kprasad99.web.proto.orm.dao.PersonDao;

@RestController
@RequestMapping("/api/proto/person")
public class PersonProtoService {

	@Autowired
	private PersonDao personDao;

	@Autowired
	private ModelMapper mapper;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Persons list() {
		List<Person> persons = personDao.findAll().stream().map(toProto).map(Person.Builder::build)
				.collect(Collectors.toList());
		return Persons.newBuilder().addAllPerson(persons).build();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable("id") int id) {
		return personDao.findById(id).map(toProto).map(Person.Builder::build).orElse(null);
	}

	public Function<io.github.kprasad99.web.proto.orm.model.Person, Person.Builder> toProto = p -> mapper.map(p,
			Person.Builder.class);

}
