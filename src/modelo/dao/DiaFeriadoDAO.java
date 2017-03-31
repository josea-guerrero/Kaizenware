package modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.dao.utils.GenericDAO;
import modelo.dto.DiaFeriado;

public class DiaFeriadoDAO extends GenericDAO {

	private static DiaFeriadoDAO instancia;	
	public static DiaFeriadoDAO getInstancia() {
		if (instancia == null) {
			instancia = new DiaFeriadoDAO();
		}
		return instancia;
	}

	private DiaFeriadoDAO() {
		super();
	}

	public List<DiaFeriado> queryAll() {
		return super.queryAll(DiaFeriado.class);
	}

	public DiaFeriado get(Serializable id) {
		return (DiaFeriado)super.get(DiaFeriado.class, id);
	}

	public void save(DiaFeriado DiaFeriado) {
		super.save(DiaFeriado);
	}

	public void update(DiaFeriado DiaFeriado) {
		super.update(DiaFeriado);
	}

	public void saveOrUpdate(DiaFeriado DiaFeriado) {
		super.saveOrUpdate(DiaFeriado);
	}

	public void delete(DiaFeriado DiaFeriado) {
		super.delete(DiaFeriado);
	}

}
