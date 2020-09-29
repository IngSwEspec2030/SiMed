package com.sw.ingenieria.simed.Controller;

import com.sw.ingenieria.simed.entity.Usuario;
import com.sw.ingenieria.simed.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping (value="/usuario")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}

    @GetMapping("")
    public ResponseEntity <?> getList() throws Exception {
        List<Usuario> list = usuarioService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Long id ) throws Exception {
        Usuario usuario = usuarioService.findById(id);
        if(usuario==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuarioService.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody Usuario usuario) throws Exception {
        return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") Long id, @RequestBody Usuario usuario) throws Exception {
        Usuario usu = usuarioService.findById(id);
        if(usu==null){
            return new ResponseEntity<>("No existe un Usuario correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        usuario.setIdUsuario(id);
        return new ResponseEntity<>(usuarioService.update(usuario),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Long id) throws Exception {
        Usuario usuario = usuarioService.findById(id);
        if(usuario==null || !usuarioService.existeById(id)){
            return new ResponseEntity<>("No existe un usuario correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(usuarioService.deleteUsuario(id), HttpStatus.OK);
    }
    @Deprecated
    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Long id) throws Exception {
        Usuario usuario = usuarioService.findId(id);
        if(usuario==null || !usuarioService.existeById(id)){
            return new ResponseEntity<>("No existe un usuario correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuarioService.activar(id), HttpStatus.OK);
    }
}
