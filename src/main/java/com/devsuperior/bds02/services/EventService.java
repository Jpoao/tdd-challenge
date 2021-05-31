package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.expetion.EntityNotFoundExceptions;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO updated) {
		try {
			Event event = repository.getOne(id);
			event.setCity(cityRepository.getOne(updated.getCityId()));
			event.setDate(updated.getDate());
			event.setName(updated.getName());
			event.setUrl(updated.getUrl());
			event = repository.save(event);
			return new EventDTO(event);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundExceptions("id " + id + " not found");
		}
	}
}
