package modelo.servicios;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import modelo.dao.DiaFeriadoDAO;
import modelo.dao.MesDAO;
import modelo.dto.DiaFeriado;
import modelo.dto.Mes;

public class ServicioDiaFeriado {

	private MesDAO mesDAO;
	private DiaFeriadoDAO diaferiadoDAO;
	
	private static ServicioDiaFeriado instancia;	
	
	public static ServicioDiaFeriado getInstancia() {
		if (instancia == null) {
			instancia = new ServicioDiaFeriado();
		}
		return instancia;
	}
	
	private ServicioDiaFeriado() {
		super();
		this.mesDAO = MesDAO.getInstancia();
		this.diaferiadoDAO = DiaFeriadoDAO.getInstancia();
	}
	
	public List<DiaFeriado> getDiaFeriados() {
		List<DiaFeriado> diaferiado = this.diaferiadoDAO.queryAll(); 
		System.out.print(diaferiado); //probando el listado de 
		return diaferiado;
	}
	
	public List<Mes> getMeses() {
		List<Mes> meses = this.mesDAO.queryAll();
		//Collections.sort(mes);
		return meses;
	}
	
	
	public DiaFeriado getDiaFeriadoPorId(Serializable id) {
		if (id != null) {
			return this.diaferiadoDAO.get(id);
		}
		return null;
	}

	public Mes getMesPorId(Serializable id) {
		if (id != null) { 
			return this.mesDAO.get(id);
		}
		return null;
	}
	
	public DiaFeriado incluirDiaFeriado(DiaFeriado diaferiado) {
		this.diaferiadoDAO.saveOrUpdate(diaferiado);
		return diaferiado;
	}

	public DiaFeriado modificarDiaFeriado(Integer id, Integer dia, Integer idmes, String nombre ) {
		DiaFeriado diaferiado = this.getDiaFeriadoPorId(id);
		diaferiado.setDia(dia);
		Mes mes = this.getMesPorId(idmes);
		diaferiado.setMes(mes);
		diaferiado.setNombre(nombre);
		this.diaferiadoDAO.saveOrUpdate(diaferiado);
		return this.getDiaFeriadoPorId(id);
	}

	public String eliminarDiaFeriado(Integer id) {
		DiaFeriado diaferiado = this.getDiaFeriadoPorId(id);
		if (diaferiado != null) {
			this.diaferiadoDAO.delete(diaferiado);
			return "ok";
		}
		return "No se pudo eliminar el dia feriado";
	}

}
