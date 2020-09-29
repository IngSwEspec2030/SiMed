import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PermissionCheckerService {

  private grantedPermissions:string;

  //Listado de permisos para perfil administrador
  adminPermisos:string[]=[
    'dahsboard.visible',
    'adminEps.visible',
    'adminLugares.visible',
    'adminEspecialidad.visible',
    'crearUsuario.visible',
    'modificarUsuario.visible',
    'eliminarUsuario.visible',
    'lugaresCercanos.visible',
    'adminUsuario.visible'
  ];

  //Listado de permisos para perfil usuario estandar
  estandarPermisos:string[]=[
    'crearUsuario.visible',
    'modificarUsuario.visible',
    'lugaresCercanos.visible',
    'adminUsuario.visible'
  ]

  constructor(public auth:AuthService) {
    if(auth.getTipoUsuario()){
      this.grantedPermissions = auth.getTipoUsuario();
    }
   }


   isGranted(permiso:Permisos):boolean{
     if(this.grantedPermissions=='1'){
       return (this.adminPermisos.includes(permiso));
    }
    else{
      return (this.estandarPermisos.includes(permiso));
    }
   }

} 

//Si se incluyen permisos aquí, se deben habilitar en los listados de permisos del perfil
export enum Permisos{
  Dashboard = 'dahsboard.visible',
  AdminEps='adminEps.visible',
  AdminLugares='adminLugares.visible',
  AdminEspecialidad='adminEspecialidad.visible',
  AdminUsuario='adminUsuario.visible',
  CrearUsuario='crearUsuario.visible',
  ModificarUsuario='modificarUsuario.visible',
  EliminarUsuario='eliminarUsuario.visible',
  ConsultarLugaresCercanos='lugaresCercanos.visible'


}
