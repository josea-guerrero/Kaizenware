package modelo.dto;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "pago")
public class Pago implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="generador_id_pago", sequenceName="secuencia_pago")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="generador_id_pago")
	private Integer id;
	
	private Calendar fecha_emision;
	
	private String descripcion;
	
	private Double monto;
	
	private String comprobante;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "mes")
	private Mes mes;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_candidato")
	private Candidato candidato;

	public Pago() {
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

	public Calendar getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(Calendar fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	
	public String getFechaEmisionString() {
		Integer dia = fecha_emision.get(Calendar.DATE);
		Integer mes = fecha_emision.get(Calendar.MONTH) + 1;
		Integer ano = fecha_emision.get(Calendar.YEAR);
		return  dia.toString() + "/" + mes.toString() + "/" + ano.toString();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMonto() {
		return monto;
	}
	
	public String getMontoString() {
		return monto.toString();
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
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
		Pago other = (Pago) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
