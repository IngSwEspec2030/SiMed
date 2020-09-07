package com.sw.ingenieria.simed.Controller;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.service.EpsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/eps")

public class EpsController {

    @Autowired
    private EpsService epsService;

    public EpsController(EpsService epsService) {this.epsService = epsService;}

    @GetMapping("/listAll")
    public ResponseEntity<?> getList() throws Exception {
        List<Eps> list = epsService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @GetMapping("/findById/{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        Eps eps = epsService.findById(id);
        if(eps==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(epsService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity <?> create(@RequestBody Eps eps) throws Exception {
        return new ResponseEntity<>(epsService.create(eps), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity <?> update(@RequestBody Eps eps) throws Exception {
        Eps eps1 = epsService.findById(eps.getIdEps());
        if(eps1==null){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(epsService.update(eps),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Short id) throws Exception {
        Eps eps1 = epsService.findById(id);
        if(eps1==null || !epsService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        epsService.delete(id);
        return new ResponseEntity<>("Eps eliminado", HttpStatus.OK);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Short id) throws Exception {
        Eps eps = epsService.findId(id);
        if(eps==null || !epsService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        epsService.activar(id);
        return new ResponseEntity<>("Eps activada", HttpStatus.OK);
    }

}
