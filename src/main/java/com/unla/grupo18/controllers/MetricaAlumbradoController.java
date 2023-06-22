package com.unla.grupo18.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.implementation.DispositivoAlumbradoService;
import com.unla.grupo18.services.implementation.EventoService;
import com.unla.grupo18.services.implementation.MetricaDispositivoAlumbradoService;

@Controller
@RequestMapping("/dispositivo/alumbrado")
public class MetricaAlumbradoController {
	private final MetricaDispositivoAlumbradoService metricaService;
	private final DispositivoAlumbradoService dispositivoService;
	private final EventoService eventoService;

	public MetricaAlumbradoController(MetricaDispositivoAlumbradoService metricaService,
			DispositivoAlumbradoService dispositivoService, EventoService eventoService) {
		this.metricaService = metricaService;
		this.dispositivoService = dispositivoService;
		this.eventoService = eventoService;
	}

	@GetMapping("/metricas/{id}")
	public String listaMetricasDispositivoAlumbrado(@PathVariable int id, Model model) {
		DispositivoAlumbrado dispositivo = dispositivoService.getDispositivoById(id);
		List<MetricaAlumbrado> metricas = metricaService.getMetricasByDispositivo(dispositivo);
		model.addAttribute("dispositivoAlumbrado", dispositivo);
		model.addAttribute("metricaDispositivoAlumbrado", metricas);

		return ViewRouteHelper.METRICAS_ALUMBRADO;
	}

	@GetMapping("/actualizar/{id}")
	public String actualizarEventosDelDispositivo(@PathVariable int id, Model model) {
		Dispositivo dispositivo = dispositivoService.getDispositivoById(id);
		DispositivoAlumbrado dispositivoAlumbrado = (DispositivoAlumbrado) dispositivo;

		List<MetricaAlumbrado> metricas = metricaService.getMetricasByDispositivo(dispositivoAlumbrado);

		for (MetricaAlumbrado metrica : metricas) {
			if (!dispositivoAlumbrado.isEstado()) {
				LocalTime horaEncendido = dispositivoAlumbrado.getHoradeEncendido();
				LocalTime horaApagado = dispositivoAlumbrado.getHoradeApagado();

				if (metrica.isSensorPresencia() && metrica.getHoraActual().isAfter(horaEncendido)
						&& metrica.getHoraActual().isBefore(horaApagado)) {
					dispositivoAlumbrado.setEstado(true);
					Evento eventoPrenderLaLuz = new Evento(dispositivoAlumbrado, "Se prendió la luz",
							metrica.getFechaHoraMetrica(), metrica);
					eventoService.saveEvento(eventoPrenderLaLuz);
				}
			} else {
				if (metrica.isSensorPresencia() == false) {
					dispositivoAlumbrado.setEstado(false);
					Evento eventoApagarLaLuz = new Evento(dispositivoAlumbrado, "Se apagó la luz",
							metrica.getFechaHoraMetrica(), metrica);
					eventoService.saveEvento(eventoApagarLaLuz);
				}
			}
		}

		return ViewRouteHelper.EVENTOS_ALUMBRADO;
	}
}