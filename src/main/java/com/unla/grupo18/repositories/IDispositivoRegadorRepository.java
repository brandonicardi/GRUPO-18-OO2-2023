package com.unla.grupo18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoRegador;
import java.util.List;


@Repository("dispositivoRegadorRepository")
public interface IDispositivoRegadorRepository extends JpaRepository<DispositivoRegador, Integer> {
	
	public List<DispositivoRegador> findByIsBaja(boolean baja);
	public List<DispositivoRegador> findByEstaPrendido(boolean estaPrendido);
}
