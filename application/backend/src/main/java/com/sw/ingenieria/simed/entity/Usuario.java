package com.sw.ingenieria.simed.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Johan Céspedes at PUJ
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"USUARIO\"")


public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    @Size(max = 2147483647)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Size(max = 100)
    @Column(name = "APELLIDOS_USUARIO")
    private String apellidosUsuario;
    @Size(max = 20)
    @Column(name = "NUMERO_IDENTIFICACION_USUARIO")
    private String numeroIdentificacionUsuario;
    @Size(max = 250)
    @Column(name = "PASSWORD_USUARIO")
    private String passwordUsuario;
    @Size(max = 250)
    @Column(name = "CORREO_USUARIO")
    private String correoUsuario;
    @Column(name = "ESTADO_USUARIO")
    private Boolean estadoUsuario;
    @Column(name = "FECHA_CREACION_USUARIO")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaCreacionUsuario;
    @Column(name = "FECHA_MODIFICACION_USUARIO")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaModificacionUsuario;
    @JoinColumn(name = "ID_EPS", referencedColumnName = "ID_EPS")
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Eps idEps;
    @JoinColumn(name = "\"ID_TIPO_IDENTIFICACION\"", referencedColumnName = "ID_TIPO_IDENTIFICACION")
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private TipoIdentificacion tipoIdentificacion;
    @JoinColumn(name = "\"ID_TIPO_USUARIO\"", referencedColumnName = "ID_TIPO_USUARIO")
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private TipoUsuario idTipoUsuario;

    public Usuario(Long idUsuario) { this.idUsuario = idUsuario; }



}
