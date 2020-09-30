package com.sw.ingenieria.simed.Controller;

import com.sw.ingenieria.simed.dto.EpsInputDTO;
import com.sw.ingenieria.simed.dto.GeneralOutput;
import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.Usuario;
import com.sw.ingenieria.simed.service.EpsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/eps")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class EpsController {

    @Autowired
    private EpsService epsService;

    public EpsController(EpsService epsService) {this.epsService = epsService;}

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Eps> list = epsService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findAllByEps")
    public ResponseEntity <?> findAllByEps() throws Exception {
        List <Map<String, String>> list = epsService.findAllByEps();
        if(!(list == null)){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        Eps eps = epsService.findById(id);
        if(eps==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(epsService.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody Eps eps) throws Exception {
        return new ResponseEntity<>(epsService.create(eps), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") Short id, @RequestBody Eps eps) throws Exception {
        Eps eps1 = epsService.findById(id);
        if(eps1==null){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        eps.setIdEps(id);
        return new ResponseEntity<>(epsService.update(eps),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Short id) throws Exception {
        Eps eps1 = epsService.findById(id);
        if(eps1==null || !epsService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(epsService.deleteEPS(id), HttpStatus.OK);
    }
    @Deprecated
    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Short id) throws Exception {
        Eps eps = epsService.findId(id);
        if(eps==null || !epsService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(epsService.activar(id), HttpStatus.OK);
    }

   /* @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/asignarLugar")
    public GeneralOutput asignarLugar(@RequestBody EpsInputDTO entity) throws Exception{
        epsService.asignarLugar(entity);
        return new GeneralOutput(HttpStatus.ACCEPTED.value(),HttpStatus.ACCEPTED.getReasonPhrase(),"Asignación Realizada Exitosamente...");
    }*/


}
