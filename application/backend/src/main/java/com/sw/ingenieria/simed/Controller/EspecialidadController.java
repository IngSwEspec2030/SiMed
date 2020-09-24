package com.sw.ingenieria.simed.Controller;

import com.sw.ingenieria.simed.entity.Especialidad;
import com.sw.ingenieria.simed.service.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/especialidad")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")

public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {this.especialidadService = especialidadService;}

    @GetMapping("/listAll")
    public ResponseEntity<?> getList() throws Exception {
        List<Especialidad> list = especialidadService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @GetMapping("/findById/{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        Especialidad especialidad = especialidadService.findById(id);
        if(especialidad==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(especialidadService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity <?> create(@RequestBody Especialidad especialidad) throws Exception {
        return new ResponseEntity<>(especialidadService.create(especialidad), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <?> update(@PathVariable("id") Short id, @RequestBody Especialidad especialidad) throws Exception {
        Especialidad especialidad1 = especialidadService.findById(especialidad.getIdEspecialidad());
        if(especialidad1==null){
            return new ResponseEntity<>("No existe una especialidad correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(especialidadService.update(especialidad, id),HttpStatus.OK);
    }

    //@ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Short id) throws Exception {
        Especialidad especialidad1 = especialidadService.findId(id);
        if(especialidad1==null || !especialidadService.existeById(id)){
            return new ResponseEntity<>("No existe una especialidad correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(especialidadService.deleteEspecialidad(id), HttpStatus.OK);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Short id) throws Exception {
        Especialidad especialidad = especialidadService.findId(id);
        if(especialidad==null || !especialidadService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(especialidadService.activar(id), HttpStatus.OK);
    }

}
