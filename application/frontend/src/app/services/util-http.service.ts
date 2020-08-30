import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ConfigService } from './config.service';
import Swal from 'sweetalert2'
import { isJson } from '../utils/constants';

@Injectable({
  providedIn: 'root'
})
export class UtilHttpService {

  defaultMsgSpinner:string="Procesando su solicitud!";

  constructor(private http:HttpClient, private config:ConfigService) {}

  private createRequestHeader():HttpHeaders{

    return new HttpHeaders({
      'Content-Type':'application/json',
      'Accept':'application/json'
    })
  }

  /**
   * Consumo de servicio por parámetro get
   * @param url url de endpoint
   * @param param si el endpoint necesita un recurso se pasa aquí, de lo contrario debe ser null o ""
   */
  get(url:string, param:string){
    url = param===null || param===""? url:`${url}/${param}`;
    url = this.config.prop.urlTarget + url;
    return this.http.get(url, {headers:this.createRequestHeader()})
  }

  /**
   * consumo de servicio por parámentro post
   * @param url url de endpoint
   * @param body cuerpo, debe ser un objeto de una clase o interface
   */
  post(url:string, body:any){
    let b:string = isJson(body)? body: JSON.stringify(body);
    url = this.config.prop.urlTarget + url;
    return this.http.post(url, b, {headers:this.createRequestHeader()} )
  }

  /**
   * 
   * @param url url del endpoint
   * @param body 
   */
  put(url:string, body:any){
    let b:string = isJson(body)? body: JSON.stringify(body);
    url = this.config.prop.urlTarget + url;
    return this.http.put(url, b, {headers:this.createRequestHeader()} )
  }

  /**
   * Este método aún no esta definido
   * @param url 
   * @param id 
   */
  delete(url:string,id:any){
    url = this.config.prop.urlTarget + url;
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
