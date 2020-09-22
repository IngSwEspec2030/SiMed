import { TipoIdentificacion } from './tipo_identificacion'
import { Eps } from './eps'
import { TipoUsuario } from './tipo_usuario'

export class Usuario {
    idUsuario?:number;
    nombreUsuario: string;
    apellidosUsuario: string;
    numeroIdentificacionUsuario: string;
    passwordUsuario: string;
    correoUsuario: string;
    estadoUsuario: string;
    fechaCreacionUsuario: Date;
    fechaModificacionUsuario: Date;
    tipoIdentificacion: TipoIdentificacion;
    idEps: string;
    eps: Eps;
    idTipoUsuario: string;
    tipoUsuario: TipoUsuario;
    username: string;
}