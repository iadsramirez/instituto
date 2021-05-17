package com.formacionbdi.microservicios.app.respuestas.services;

import java.util.List;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;



public interface RespuestaService {

	public List<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	public List<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
	
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId);
}
