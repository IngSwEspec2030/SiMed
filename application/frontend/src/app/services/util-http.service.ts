import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ConfigService } from './config.service';
import Swal from 'sweetalert2'
import { isJson } from '../utils/constants';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UtilHttpService {

  defaultMsgSpinner:string="<h3>Procesando su solicitud!</h3>";

  constructor(private http:HttpClient, private config:ConfigService, private auth:AuthService) {}

  private createRequestHeader():HttpHeaders{

    return new HttpHeaders({
      'Content-Type':'application/json',
      'Accept':'application/json',
      'Authorization': this.auth.getTokenUser()
    })
  }

  /**
   * Consumo de servicio por parámetro get
   * @param url url de endpoint
   * @param param si el endpoint necesita un recurso se pasa aquí, de lo contrario debe ser null o ""
   */
  get(url:string, param?:string){
    url = param===null || param===""? url:`${url}/${param}`;
    url = environment.apiEndPoint + url;
    return this.http.get(url, {headers:this.createRequestHeader()})
  }

  /**
   * consumo de servicio por parámentro post
   * @param url url de endpoint
   * @param body cuerpo, debe ser un objeto de una clase o interface
   */
  post(url:string, body:any){
    let b:string = isJson(body)? body: JSON.stringify(body);
    url = environment.apiEndPoint + url;
    return this.http.post(url, b, {headers:this.createRequestHeader()} )
  }

  /**
   * 
   * @param url url del endpoint
   * @param body 
   */
  put(url:string, body:any, id:any){
    let b:string = isJson(body)? body: JSON.stringify(body);
    url = environment.apiEndPoint + `${url}/${id}`;
    return this.http.put(url, b, {headers:this.createRequestHeader()} )
  }

  /**
   * Este método aún no esta definido
   * @param url 
   * @param id 
   */
  delete(url:string,id:any){
    console.log(`${url}/${id}`);
    url = `${url}/${id}`;
    url = environment.apiEndPoint + url;
    return this.http.delete(url,{headers:this.createRequestHeader()} )
  }

  showBusy(title:string=this.defaultMsgSpinner){
    Swal.fire( {allowOutsideClick:false, title: title});
    Swal.showLoading();
  }

  closeBusy(){
    Swal.close();
  }
}
