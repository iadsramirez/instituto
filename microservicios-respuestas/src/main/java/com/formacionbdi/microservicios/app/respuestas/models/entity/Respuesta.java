package com.formacionbdi.microservicios.app.respuestas.models.entity;
/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/
//import javax.persistence.ManyToOne;
/*import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;*/

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Pregunta;

/*@Entity
@Table(name="respuestas")*/

@Document(collection = "respuestas")
public class Respuesta {

	/*@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)*/
	@Id
	private String id;
	
	private String texto;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@Transient
	
	@Transient
	private Alumno alumno;
	
	//@Column(name ="alumno_id" )
	private Long alumnoId;
	
	//@OneToOne(fetch = FetchType.LAZY)
	@Transient
	private Pregunta pregunta;
	
	private Long preguntaId;
	
	

	public Long getPreguntaId() {
		return preguntaId;
	}

	public void setPreguntaId(Long preguntaId) {
		this.preguntaId = preguntaId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Long getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}
	
	
	
	
}
