package com.sw.ingenieria.simed.entity;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Johan Céspedes at PUJ
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")


public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Long id_usuario;
    @Column(name = "nombre_usuario")
    private String nombre_usuario;
    @Column(name = "apellido_usuario")
    private String apellido_usuario;
    @Column(name = "numdoc_usuario")
    private String numdoc_usuario;
    @Column(name = "fecha_creacion")
    private LocalDate fecha_creacion;
    /*@Column(name = "tipo_documento")
    private String tipo_documento;
    @Column(name = "estado_usuario")
    private Boolean estado_usuario;
    @Column(name = "eps_usuario")
    private Boolean eps_usuario;
    @Column(name = "usuario_crea")
    private Long usuario_crea;
    @Column(name = "usuario_mod")
    private Long usuario_mod;*/

    public Usuario(Long id) {
        this.id_usuario = id_usuario;
    }



}
