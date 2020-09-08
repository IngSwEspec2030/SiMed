import { TipoIdentificacion } from './tipo_identificacion'
import { Eps } from './eps'
import { TipoUsuario } from './tipo_usuario'

export class Usuario {
    NOMBRES_USUARIO: string
    APELLIDOS_USUARIO: string
    NUMERO_IDENTIFICACION_USUARIO: string
    PASSWORD_USUARIO: string
    CORREO_USUARIO: string
    ESTADO_USUARIO: string
    FECHA_CREACION_USUARIO: Date
    FECHA_MODIFICACION_USUARIO: Date
    TIPO_IDENTIFICACION: TipoIdentificacion
    ID_EPS: Eps
    TIPO_USUARIO: TipoUsuario


}