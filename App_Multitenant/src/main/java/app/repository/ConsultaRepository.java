package app.repository;

import app.models.Consulta;
import app.models.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends CrudRepository<Consulta, Integer>{
    //Buscar por Consultas por pacientes
    Consulta findByPaciente(Paciente paciente);
    
    
}
