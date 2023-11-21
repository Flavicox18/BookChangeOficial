package pe.edu.upao.bookchange.service;

import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.model.Intercambio;
import pe.edu.upao.bookchange.repository.Intercambiorepository;

@Service
public class Intercambioservice {
    public final Intercambiorepository intercambiorepository;

    public Intercambioservice(Intercambiorepository intercambiorepository){ this.intercambiorepository = intercambiorepository;}

    public Intercambio addIntercambio(Intercambio intercambio){
        return intercambiorepository.save(intercambio);
    }
}
