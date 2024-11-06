
package org.uv.tpcsw.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEmpleado extends JpaRepository<Empleado, Long> {
    
}
