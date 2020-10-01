import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { DisplayMessage, IconType, ButtonType } from 'src/app/utils/messageSweet';
import { UtilHttpService } from 'src/app/services/util-http.service';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  public labels1: string[] = ['Sanitas', 'Colsubsidio', 'Coomeva'];
  public data1 = [
    [10, 15, 40],
  ];

  public colorsRet=[{
    backgroundColor:['#6857E6','#009FEE','#F02059']
  }];

  
  message:DisplayMessage
  constructor(
    private http:UtilHttpService,
    private c:ConfigService
    ) { 
    this.message = new DisplayMessage();
  }

  ngOnInit(): void {
    this.cargarEstadistica();
  }

  cargarEstadistica(){
    this.data1=[];
    this.labels1=[];
    this.http.get(this.c.prop.urlEstadisticasEps)
    .subscribe((result:any)=>{      
      result.forEach(element => {
        this.labels1.push(element.nombre_eps);
        this.data1.push(element.total);        
      });
      this.colorsRet = [{
        backgroundColor:this.getColors()
      }];
    })
  }

  
  getColors():string[]{
    let colors=[];
    for (let i = 0; i < this.data1.length; i++) {
      const color = this.getRandomColor();
      colors.push(color);
    }   

    return colors;
  }

   getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

  onClick(value:number){

    switch(value){
      case 1:
      this.http.showBusy();
      let timerInterval = setInterval(() => {
        this.http.closeBusy();
        clearInterval(timerInterval);
      }, 3000);

      break;

      case 2:
      this.message.displayInfoMessage("OK", "Message", IconType.success, ButtonType.Ok);
      break;
      default:
        break;
    }


  }

}
