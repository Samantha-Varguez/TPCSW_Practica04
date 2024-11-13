
package org.uv.tpcsw.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/empleados")
public class ControllerEmpleados {
    
    // permite hacer implementacion automatica 
    @Autowired
   
    private RepositoryEmpleado repositoryEmpleados;
    
    @GetMapping()
    public List<Empleado> list() {
         //jpa implementa el findall
        return repositoryEmpleados.findAll();
    }
    
    @GetMapping("/{id}")
    public Empleado get(@PathVariable String id) {
        Optional<Empleado> res= repositoryEmpleados.findById(Long.valueOf(id));
        return res.get();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Empleado input) {
        return repositoryEmpleados.findById(Long.valueOf(id))
                .map(existingEmpleado -> {
                    existingEmpleado.setNombre(input.getNombre()); // Example: setting new fields
                    existingEmpleado.setDepto(input.getDepto());
                    repositoryEmpleados.save(existingEmpleado);
                    return new ResponseEntity<>(existingEmpleado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Empleado empleado) {
        Empleado empRes = repositoryEmpleados.save(empleado);
        return new ResponseEntity<Empleado>(empRes, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
     if (repositoryEmpleados.existsById(Long.valueOf(id))) {
            repositoryEmpleados.deleteById(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
