import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  latDefault = 4.627832;
  lngDefault = -74.064395;

  constructor() { }


  /**
   * Obtener ubicación del usario
   */
  getPosition(): Promise<any>
  {
    if(!navigator.geolocation){//si el navegador no soporta le damos un valor default
     console.warn("Geolocation is not supported");
     let def1= new Promise((resolve) => {
        resolve({lng: this.latDefault, lat: this.lngDefault});
      });   
      return def1;
    }

       let prom = new Promise((resolve, reject) => {
       navigator.geolocation.getCurrentPosition(resp => {    
           resolve({lng: resp.coords.longitude, lat: resp.coords.latitude});
         },(err) => {
           this.onErrorPosition(err);
             // reject(err);
         });
     });
        
     return prom;
  }

  onErrorPosition(err){
    console.warn('user error in geolocalization:(' + err.code + '): ' + err.message);
    console.log("User not allow")
  }

  options = {
    enableHighAccuracy: true,
    timeout: 3000,
    maximumAge: 0
  }
}
