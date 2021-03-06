import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { userReturned } from 'src/app/dto/userReturn';
import { Usuario } from 'src/app/dto/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private keyToken:string;
  private keyUsuer:string;
  private keyEps:string;
  private user:Usuario;
  private keyTipoUsuario:string;
  private keyEmail: string;
  private keyIdUser:string;
  private keyUsuerAll: string;

  constructor(private router:Router) {
    this.keyToken='#ARDPTJB*Kt#';
    this.keyUsuer='#334567Ku$';
    this.keyEps='#456467Ke$';
    this.keyTipoUsuario="%46456456";
    this.keyIdUser="%#sfgfkuidd*";
    this.keyEmail="%&456545km";
    this.keyUsuerAll="#*rawxXkjni$nXS84#dwerturse&&627";
   }

  isAuthenticated():boolean{
    return (localStorage.getItem(this.keyUsuer))==='';
  }

  setUser(usuarioAutenticado:userReturned){
    this.user = usuarioAutenticado.usuario;
    let userJson = JSON.stringify(this.user);
    localStorage.setItem(this.keyUsuerAll,userJson);
    localStorage.setItem(this.keyTipoUsuario, usuarioAutenticado.usuario.tipoUsuario.idTipoUsuario.toString());
    localStorage.setItem(this.keyUsuer, usuarioAutenticado.usuario.username);
    localStorage.setItem(this.keyIdUser, usuarioAutenticado.usuario.idUsuario.toString());
    localStorage.setItem(this.keyEps, usuarioAutenticado.usuario.eps.idEps.toString());
    localStorage.setItem(this.keyToken, usuarioAutenticado.token);
    localStorage.setItem(this.keyEmail, usuarioAutenticado.usuario.correoUsuario);
  }
  
  getUserRegistered():Usuario{
    let user = localStorage.getItem(this.keyUsuerAll);
    let userTrs = JSON.parse(user);
    return userTrs;
  }  
  
  getUserName():string{    
    return localStorage.getItem(this.keyUsuer);
  }

  getUserId():string{    
    return localStorage.getItem(this.keyIdUser);
  }

  getUserEmail():string{    
    return localStorage.getItem(this.keyEmail);
  }

  getTokenUser():string{
    let token = (localStorage.getItem(this.keyToken));
    return token;
  }

  getidEps() : number {
    let eps = (localStorage.getItem(this.keyEps));
    let er = /^\d*$/;
    if(er.test(eps))       
       return +eps;
    return null;
  }

  getTipoUsuario(){
    return localStorage.getItem(this.keyTipoUsuario);
  }

  logout(){
    sessionStorage.clear();
    localStorage.clear();
    this.router.navigate(['/login']);
  }


  updateUsuario(usuario:Usuario){
    this.user=this.getUserRegistered();    
    localStorage.removeItem(this.keyUsuerAll);
    for(const prop in usuario){

      if(prop==='nombreUsuario'){
        if(this.user.nombreUsuario!=usuario.nombreUsuario)
        this.user.nombreUsuario=usuario.nombreUsuario;
      }

      if(prop==='apellidosUsuario'){
        if(this.user.apellidosUsuario!=usuario.apellidosUsuario)
        this.user.apellidosUsuario=usuario.apellidosUsuario;
      }

      if(prop==='numeroIdentificacionUsuario'){
        if(this.user.numeroIdentificacionUsuario!=usuario.numeroIdentificacionUsuario)
        this.user.numeroIdentificacionUsuario=usuario.numeroIdentificacionUsuario;
      }

      if(prop==='correoUsuario'){
        if(this.user.correoUsuario!=usuario.correoUsuario)
        this.user.correoUsuario=usuario.correoUsuario;
      }

      if(prop==='eps'){
        if(usuario.eps.idEps!=null && this.user.eps.idEps!=usuario.eps.idEps)
          this.user.eps.idEps=usuario.eps.idEps;
      }

      if(prop==='tipoIdentificacion'){
        if(this.user.tipoIdentificacion.tipoIdentificacion!=usuario.tipoIdentificacion.tipoIdentificacion)
        this.user.tipoIdentificacion.tipoIdentificacion=usuario.tipoIdentificacion.tipoIdentificacion;
      }
    }



      
    let userJson = JSON.stringify(this.user);
    localStorage.setItem(this.keyUsuerAll,userJson);
  }
}
