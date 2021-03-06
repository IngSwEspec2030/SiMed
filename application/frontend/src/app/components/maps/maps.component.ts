import { Component, Input, OnInit } from '@angular/core';
import { Marcador } from 'src/app/dto/marcador';
import { LocationService } from 'src/app/services/location.service';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ConfigService } from 'src/app/services/config.service';
import { LugaresAtencion } from 'src/app/dto/lugarAtencion'; 
import { DisplayMessage, IconType, ButtonType } from 'src/app/utils/messageSweet';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})
export class MapsComponent implements OnInit {

  idEpsSelected:number;
  
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
    private config:ConfigService ,
    private auth:AuthService
    ) { 
      this.userMark = new Marcador(4.627832,-74.064395);
      this.userMark.valido=false;
    }

  ngOnInit(): void {
    this.idEpsSelected = this.auth.getidEps();
    this.getUserLocation();
  }

  /**
   * Obtiene la ubicación del usuario
   */
 private  getUserLocation() {
    this.locationService.getPosition().then(pos=>
      {
        if(!pos){
          console.log("Not Pos", pos);
        }
         this.lat=pos===undefined?this.lat: pos.lat;
         this.lng=pos===undefined?this.lng:pos.lng;
         const userMark = new Marcador(this.lat, this.lng);
         userMark.valido=true;
         this.userMark=userMark;
         if(pos!==undefined)
          this.getLugaresCercanos();
         
      });    
  }
  /**
   * Obtiene los lugares de atención mas cernanos
   */
  private getLugaresCercanos(){
    let parametros= `${this.idEpsSelected}/${this.lat}/${this.lng}`
    this.http.get(this.config.prop.urllistLugaresAtencionCercanos, parametros)
    .subscribe((data:LugaresAtencion[])=>{
      if(data.length>0){
        this.message.displayInfoMessage("Estimado Usuario", "Se han encontrado " +data.length + " sitios cerca de usted"  , IconType.info, ButtonType.Ok);      
        this.lugaresCercanosList = data;
      }
      else{
        this.message.displayInfoMessage("Error", "No se encontraron lugares de atención cerca de usted", IconType.error, ButtonType.Ok);      
      }
    }, (err)=>{     
      if(err!=null && err!==undefined && err.error.status===500){
        this.message.displayInfoMessage("Atención", "No se encontraron lugares de atención cerca de usted para la Eps seleccionada", IconType.warning, ButtonType.Ok);      

      }else{
        this.message.displayInfoMessage("Error", "No se pudo conectar al servicio, por favor intentelo más tarde!", IconType.error, ButtonType.Ok);      
      }
    })
  }

 onAddMark(evento){
    const coord: {lat:number, lng:number} = evento.coords;
    const nuevoMarcador = new Marcador(coord.lat, coord.lng);
    // this.marcadores.push(nuevoMarcador);
  }


 onDeleteMark(index:number){
  //  this.marcadores.splice(index,1);
  }

  public onGetLugaresxEps(idEps:number){
    this.idEpsSelected=idEps;
    this.getLugaresCercanos();
  }

  onAccept(infoWindow){    
    if(infoWindow!=null){
      infoWindow.close();
    }
      
  }

}
