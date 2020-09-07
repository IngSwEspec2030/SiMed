import { Component, OnInit } from '@angular/core';
import { Marcador } from 'src/app/dto/marcador';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})
export class MapsComponent implements OnInit {

  marcadores:Marcador[]=[];
  title = 'Proyecto SiMed';

  lat = 4.627832;
  lng = -74.064395;

  constructor() { }

  ngOnInit(): void {
    if(localStorage.getItem('marcadores')){
      this.marcadores = JSON.parse(localStorage.getItem('marcadores'));
    }
  }

  onAddMark(evento){
    const coord: {lat:number, lng:number} = evento.coords;
    const nuevoMarcador = new Marcador(coord.lat, coord.lng);
    this.marcadores.push(nuevoMarcador);
    this.onSaveStorage();
  }

  onSaveStorage(){
    localStorage.setItem('marcadores', JSON.stringify(this.marcadores));
  }


  onDeleteMark(index:number){
  console.log("index: ", index);
  
   this.marcadores.splice(index,1);
   this.onSaveStorage();
  }

}
