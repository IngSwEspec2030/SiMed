import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor() { }


  /**
   * Obtener ubicación del usario
   */
  getPosition(): Promise<any>
  {
    return new Promise((resolve, reject) => {

      navigator.geolocation.getCurrentPosition(resp => {

          resolve({lng: resp.coords.longitude, lat: resp.coords.latitude});
        },
        err => {
          reject(err);
        });
    });
  }


  onErrorPosition(err){
    console.warn('ERROR(' + err.code + '): ' + err.message);
    console.log("User not allow")
  }

  options = {
    enableHighAccuracy: true,
    timeout: 3000,
    maximumAge: 0
  }
}
