package com.sw.ingenieria.simed.Controller;

import com.sw.ingenieria.simed.entity.LugarAtencion;
import com.sw.ingenieria.simed.entity.Usuario;
import com.sw.ingenieria.simed.service.LugarAtencionService;
import com.sw.ingenieria.simed.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/lugarAtencion")
public class LugarAtencionControler {
    private LugarAtencionService lugarAtencionService;

    public LugarAtencionControler(LugarAtencionService lugarAtencionService) {this.lugarAtencionService = lugarAtencionService;}

    @GetMapping("/listAll")
    public ResponseEntity<?> getList() throws Exception {
        List<LugarAtencion> list = lugarAtencionService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @GetMapping("/findById/{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Long id ) throws Exception {
        System.out.println("este es el id que llega al controller = " + id);
        LugarAtencion usuario = lugarAtencionService.findById(id);
        if(usuario==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lugarAtencionService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity <?> create(@RequestBody LugarAtencion lugarAtencion) throws Exception {
        return new ResponseEntity<>(lugarAtencionService.create(lugarAtencion), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity <?> update(@RequestBody LugarAtencion lugarAtencion) throws Exception {
        LugarAtencion lugAtencion = lugarAtencionService.findById(lugarAtencion.getIdLugaresAtencion());
        if(lugAtencion==null){
            return new ResponseEntity<>("No existe un lugar de atención correspondiente al ID ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lugarAtencionService.update(lugarAtencion),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Long id) throws Exception {
        LugarAtencion lugarAtencion = lugarAtencionService.findById(id);
        if (lugarAtencion == null || !lugarAtencionService.existeById(id)) {
            return new ResponseEntity<>("No existe un lugar de atención correspondiente al ID ingresado", HttpStatus.BAD_REQUEST);
        }
        lugarAtencionService.delete(id);
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }
}
