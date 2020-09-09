import { TipoIdentificacion } from './tipo_identificacion'
import { Eps } from './eps'
import { TipoUsuario } from './tipo_usuario'

export class Usuario {
    nombreUsuario: string
    apellidosUsuario: string
    numeroIdentificacionUsuario: string
    passwordUsuario: string
    correoUsuario: string
    estadoUsuario: string
    fechaCreacionUsuario: Date
    fechaModificacionUsuario: Date
    tipoIdentificacion: TipoIdentificacion
    idEps: Eps
    idTipoUsuario: TipoUsuario
    username: string


}