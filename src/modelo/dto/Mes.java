package modelo.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "mes")
public class Mes {
	
	@Id
	@Column(name = "numero")
	private Integer id; //numero del mes;
	
	private String mes;
	
	@OneToMany(mappedBy = "mes", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Asistencia> asistencias;
	
	@OneToMany(mappedBy = "mes", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<DiaFeriado> diasFeriados;
	
	@OneToMany(mappedBy = "mes", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Pago> pagos;
	
	@OneToMany(mappedBy = "mes", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Cobro> cobros;

	public Mes() {
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

	public String getMes() {
		return mes;
	}
	
	public Month getMonth() {
		Month month = null;
		switch(id) {
		case 1: month = Month.JANUARY;
				break;
		case 2: month = Month.FEBRUARY;
				break;
		case 3: month = Month.MARCH;
				break; 
		case 4: month = Month.APRIL;
				break;		
		case 5: month = Month.MAY;
				break;
		case 6: month = Month.JUNE;
				break;
		case 7: month = Month.JULY;
				break;
		case 8: month = Month.AUGUST;
				break;
		case 9: month = Month.SEPTEMBER;
				break;
		case 10: month = Month.OCTOBER;
				break;
		case 11: month = Month.NOVEMBER;
				break;
		case 12: month = Month.DECEMBER;
				break;
		default:
				break;
		}
		return month;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<Asistencia> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public List<DiaFeriado> getDiasFeriados() {
		return diasFeriados;
	}

	public void setDiasFeriados(List<DiaFeriado> diasFeriados) {
		this.diasFeriados = diasFeriados;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public List<Cobro> getCobros() {
		return cobros;
	}

	public void setCobros(List<Cobro> cobros) {
		this.cobros = cobros;
	}
	
	public Integer horasLaborables() {
		Integer diasLaborables = 0;
		switch(id) {
			case 1 : case 3 : case 5 : case 7 : case 8 : case 10 : case 12 :
				
				for(int i = 1; i <= 31; i++) {
					LocalDate fecha = LocalDate.of(2017, getMonth(), i);
					if(fecha.getDayOfWeek().equals(DayOfWeek.MONDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.TUESDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.THURSDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
						
							if(!buscarDiaFeriado(fecha.getDayOfMonth()))
								diasLaborables++;
					}
				}
			case 2 :
				for(int i = 1; i <= 28; i++) {
					LocalDate fecha = LocalDate.of(2017, getMonth(), i);
					if(fecha.getDayOfWeek().equals(DayOfWeek.MONDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.TUESDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.THURSDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
						
							if(!buscarDiaFeriado(fecha.getDayOfMonth()))
								diasLaborables++;
					}
				}
			case 4 : case 6 : case 9 : case 11 :
				for(int i = 1; i <= 30; i++) {
					LocalDate fecha = LocalDate.of(2017, getMonth(), i);
					if(fecha.getDayOfWeek().equals(DayOfWeek.MONDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.TUESDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.THURSDAY)||
						fecha.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
							if(!buscarDiaFeriado(fecha.getDayOfMonth()))
								diasLaborables++;
					}
				}
			
		}
		return diasLaborables * 8;
	}
	
	public Boolean buscarDiaFeriado(Integer dia) {
		for(int i = 0; i < diasFeriados.size(); i++) {
			if(diasFeriados.get(i).getDia().equals(dia))
				return true;
		}
		return false;
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
		Mes other = (Mes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mes [id=" + id + ", mes=" + mes + ", diasFeriados=" + diasFeriados + "]";
	}
	
}
