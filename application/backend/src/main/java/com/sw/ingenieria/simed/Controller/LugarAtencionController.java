package com.sw.ingenieria.simed.Controller;

import com.sw.ingenieria.simed.dto.GeneralOutput;
import com.sw.ingenieria.simed.dto.LugarAtencionOutputDTO;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import com.sw.ingenieria.simed.service.LugarAtencionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/lugarAtencion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class LugarAtencionController {
    @Autowired
    private LugarAtencionService lugarAtencionService;
    public LugarAtencionController(LugarAtencionService lugarAtencionService) {this.lugarAtencionService = lugarAtencionService;}

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
        LugarAtencion lugarAtencion = lugarAtencionService.findById(id);
        if(lugarAtencion==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lugarAtencionService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity <?> create(@RequestBody LugarAtencion lugarAtencion) throws Exception {
        return new ResponseEntity<>(lugarAtencionService.create(lugarAtencion), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <?> update(@PathVariable("id") Long id, @RequestBody LugarAtencion lugarAtencion) throws Exception {
        LugarAtencion lugAtencion = lugarAtencionService.findById(id);
        if(lugAtencion==null){
            return new ResponseEntity<>("No existe un lugar de atención correspondiente al ID ingresado",HttpStatus.BAD_REQUEST);
        }
        lugarAtencion.setIdLugaresAtencion(id);
        return new ResponseEntity<>(lugarAtencionService.update(lugarAtencion),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Long id) throws Exception {
        LugarAtencion lugarAtencion = lugarAtencionService.findById(id);
        if(lugarAtencion==null || !lugarAtencionService.existeById(id)){
            return new ResponseEntity<>("No existe un lugar de atención correspondiente al ID ingresado",HttpStatus.BAD_REQUEST);
        }
        lugarAtencionService.delete(id);
        return new ResponseEntity<>("Lugar de atención eliminado", HttpStatus.OK);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Long id) throws Exception {
        LugarAtencion lugarAtencion = lugarAtencionService.findId(id);
        if(lugarAtencion==null || !lugarAtencionService.existeById(id)){
            return new ResponseEntity<>("No existe un lugar de atención correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        lugarAtencionService.activar(id);
        return new ResponseEntity<>("Lugar de atención activado", HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lugaresAtencionEps/{id}")
    public GeneralOutput getAllByEpsAndActivo(@PathVariable("id") Short id ) throws Exception {
        List<LugarAtencionOutputDTO> lugarAtencionDTOList =new ArrayList<>();
        for (LugarAtencion lugarAtencion:lugarAtencionService.obtenerLugarAtencionPorEps(id)) {
            LugarAtencionOutputDTO laDTO = LugarAtencionOutputDTO.getDTO(lugarAtencion);
            lugarAtencionDTOList.add(laDTO);
        }
        return new GeneralOutput(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),lugarAtencionDTOList);
    }

    @GetMapping("/lugaresCercanos/{idEps}/{lat}/{lon}")
    public ResponseEntity<?> getClosestPlaces(@PathVariable("idEps") Short idEps, @PathVariable("lat") double lat, @PathVariable("lon") double lon ) throws Exception {
        List<LugarAtencion> list = lugarAtencionService.obtenerLugaresAtencionCercanos(idEps, lat, lon);
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }


}
