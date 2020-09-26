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
    estadoUsuario: boolean;
    fechaCreacionUsuario?: Date;
    fechaModificacionUsuario?: Date;
    tipoIdentificacion: TipoIdentificacion;
    eps: Eps;
    tipoUsuario: TipoUsuario;
    username: string;
}