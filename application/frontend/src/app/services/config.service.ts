import { Injectable } from '@angular/core';

import configInit from '../config/config.json';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  prop:any = configInit;

  constructor(private http:HttpClient) {
    console.log('aqui', this.prop);
    if(this.prop===null || this.prop === undefined || this.prop.length){
      this.http.get('app/config/config.json')
      .subscribe(data=>{
        this.prop = data;
        console.log('configuration loaded...', data);
        
      })
    }
  }
}
