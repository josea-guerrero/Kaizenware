package modelo.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@NamedNativeQueries({ 
	@NamedNativeQuery(name = "findEmpleadoSinUsuario",
	query = "SELECT * FROM candidato c LEFT OUTER JOIN usuario u ON u.id_candidato = c.id WHERE u.id IS NULL AND c.estado = 2",
	resultClass = Candidato.class),
	
	@NamedNativeQuery(name = "queryMorris",
	query = "SELECT *  FROM candidato c, pais p WHERE p.id= c.id_Pais  ",
	resultClass = Candidato.class),
	
	@NamedNativeQuery(name = "empleadosSinAsistenciaActual",
	query = "SELECT * FROM candidato c LEFT OUTER JOIN "
	+"( SELECT * FROM asistencia a WHERE a.mes = DATE_PART('MONTH', NOW()) AND a.anio = DATE_PART('YEAR', NOW()) ) asisactual"
	+ " ON asisactual.id_candidato = c.id"
	+ " WHERE asisactual.id IS NULL AND c.estado = 2",
	resultClass = Candidato.class) })
@Entity
@Table(name = "candidato")
public class Candidato implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "generador_id_candidato", sequenceName = "secuencia_candidato")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_id_candidato")
	private Integer id;
	
	private String nombres;
	
	private String apellidos;
	
	private String telefono;
	
	private String correo;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pais")
	private Pais pais;
	
	private String ciudad;
	
	private Character sexo;
	
	private String curriculum;
	
	private Integer estado;
	
	@OneToMany(mappedBy = "candidato", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Asistencia> asistenciasMensuales;

	@OneToMany(mappedBy = "candidato", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Falta> faltas;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "candidato_palabra_clave", 
			   joinColumns = {@JoinColumn(name="id_candidato")}, 
			   inverseJoinColumns = {@JoinColumn(name = "id_palabra_clave")})
	@Fetch(value = FetchMode.SUBSELECT)
	private List<PalabraClave> palabrasClaves;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_candidato")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Habilidad> habilidades;
	
	@OneToMany(mappedBy = "candidato", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Cargo> cargos;
	
	public Candidato() {
		super();
	}

	public Integer getId() {
		return id;
	}
	
	public String getIdString() {
		if (this.id == null) {
			return null;
		}
		return this.id.toString().replaceAll("\\.", "");
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Character getSexo() {
		return sexo;
	}
	
	public String getSexoString() {
		switch(sexo) {
			case 'F': return "Femenino";
			case 'M': return "Masculino";
			default: return "";
		}
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	public Integer getEstado() {
		return estado;
	}
	
	public String getEstadoString() {
		switch(estado) {
			case 1: return "Candidato";
			case 2: return "Empleado";
			default: return "";
		}
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Asistencia> getAsistenciasMensuales() {
		return asistenciasMensuales;
	}

	public void setAsistenciasMensuales(List<Asistencia> asistenciasMensuales) {
		this.asistenciasMensuales = asistenciasMensuales;
	}

	public List<Falta> getFaltas() {
		return faltas;
	}

	public void setFaltas(List<Falta> faltas) {
		this.faltas = faltas;
	}

	public List<PalabraClave> getPalabrasClaves() {
		return palabrasClaves;
	}

	public void setPalabrasClaves(List<PalabraClave> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	public Double getRemuneracionTotal(Mes mes) {
		Double total = 0.0;
		for(Cargo cargo : this.cargos) {
			if(cargo.getProyecto().getEstado().equals(1)) {
				switch(cargo.getTipo_remuneracion()) {
				case 1: total += cargo.getRemuneracion();
						break;
				case 2: total += cargo.getRemuneracion() / mes.horasLaborables();
				}
			}
		}
		
		return total;
	}
	
	public Integer getHorasJustificadas(Integer mes, Integer anio) {
		Integer horasJusti = 0;
		for(int i = 0; i < faltas.size(); i++) {
			if(faltas.get(i).getFecha().get(Calendar.MONTH) == mes-1 && 
			   faltas.get(i).getFecha().get(Calendar.YEAR) == anio) {
				
				if(faltas.get(i).getTipo_falta().equals(2)) {
					horasJusti += faltas.get(i).getHoras_faltadas();
				}
			}
			
		}
		return horasJusti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Candidato [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", correo=" + correo + ", pais=" + pais + ", ciudad=" + ciudad + ", sexo=" + sexo + ", curriculum="
				+ curriculum + ", estado=" + estado + "]";
	}
	
	
		
}
