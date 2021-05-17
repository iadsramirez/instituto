package com.formacionbdi.microservicios.app.respuestas.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.respuestas.clients.ExamenFeignClient;
import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.models.repository.RespuestaRepository;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Pregunta;

@Service
public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	@Qualifier("respuestaRepository")
	private RespuestaRepository repository;
	@Autowired
	private ExamenFeignClient examenFeignClient;
	
	@Override
//	@Transactional
	public List<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	@Override
	//@Transactional(readOnly = true)
	public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		//return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		Examen examen=examenFeignClient.obtenerExamenPorId(examenId);
		List<Pregunta> preguntas=examen.getPreguntas();
		
		List<Long> preguntasIds=preguntas.stream().map(p->p.getId()).collect(Collectors.toList());
		List<Respuesta> respuestas=(List<Respuesta>) repository.
				findRespuestaByAlumnoByPreguntaIds(alumnoId, preguntasIds);
		
		respuestas=respuestas.stream().map(r->{
			preguntas.forEach(p->{
				if(p.getId()==r.getPreguntaId()) {
					r.setPregunta(p);
				}
			});
			
			return r;
		}).collect(Collectors.toList());
		
		return respuestas;
	}

	@Override
//	@Transactional(readOnly = true)
	public List<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		List<Respuesta> respuestasAlumno=(List<Respuesta>) repository.findByAlumnoId(alumnoId);
		List<Long> examenIds=Collections.emptyList();
		
		if(respuestasAlumno.size()> 0) {
			List<Long> preguntaIds=respuestasAlumno.stream().map(r->r.getPreguntaId()).collect(Collectors.toList());
			examenIds=examenFeignClient.obtenerExamenesIdsPorPreguntasIdRespondidas(preguntaIds);
		}
		
		
		return examenIds;
	}

	@Override
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
		return repository.findByAlumnoId(alumnoId);
	}

}
