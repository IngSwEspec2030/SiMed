import { Component, OnInit } from '@angular/core';
import { Marcador } from 'src/app/dto/marcador';
import { LocationService } from 'src/app/services/location.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ConfigService } from 'src/app/services/config.service';
import { LugaresAtencion } from 'src/app/dto/lugares_atencion';
import { DisplayMessage, IconType, ButtonType } from 'src/app/utils/messageSweet';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})
export class MapsComponent implements OnInit {


  iconRed:any = 'https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|FF0000';
  iconBlue:any = 'https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|4286f4';
  
  message:DisplayMessage=new DisplayMessage();

  lugaresCercanosList:LugaresAtencion[]=[];

  title = 'Proyecto SiMed';
  lat = 4.627832;
  lng = -74.064395;

  userMark:Marcador;
  constructor(
    private locationService:LocationService, 
    private http:UtilHttpService, 
    private config:ConfigService ) { }

  ngOnInit(): void {
    this.getUserLocation();
    this.getLugaresCercanos();
  }

  /**
   * Obtiene la ubicación del usuario
   */
  getUserLocation() {
    this.locationService.getPosition().then(pos=>
      {
         this.lat=pos.lat;
         this.lng=pos.lng;
         const userMark = new Marcador(this.lat, this.lng);
         userMark.valido=true;
         this.userMark=userMark;
         
      });
  }


  /**
   * Obtiene los lugares de atención mas cernanos
   */
  getLugaresCercanos(){

    this.http.get(this.config.prop.urllistAllLugaresAtencion, null)
    .subscribe((data:LugaresAtencion[])=>{

      if(data.length>0){
        this.message.displayInfoMessage("Estimado Usuario", "Se han encontrado " +data.length + " sitios cerca de usted!"  , IconType.info, ButtonType.Ok);      
        this.lugaresCercanosList = data;
      }
      else{
        this.message.displayInfoMessage("Error", "No se encontraron lugares de atención cerca de usted!", IconType.error, ButtonType.Ok);      
      }
    }, (err)=>{
      console.error(err.error);      
      this.message.displayInfoMessage("Error", "No se pudo conectar al servicio, por favor intentelo más tarde!", IconType.error, ButtonType.Ok);      
    })
  }

  onAddMark(evento){
    const coord: {lat:number, lng:number} = evento.coords;
    const nuevoMarcador = new Marcador(coord.lat, coord.lng);
    // this.marcadores.push(nuevoMarcador);
  }


  onDeleteMark(index:number){
  console.log("index: ", index);  
  //  this.marcadores.splice(index,1);
  }

}
