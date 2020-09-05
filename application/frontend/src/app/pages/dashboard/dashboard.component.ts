import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { DisplayMessage, IconType, ButtonType } from 'src/app/utils/messageSweet';
import { UtilHttpService } from 'src/app/services/util-http.service';

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
  message:DisplayMessage
  constructor(private http:UtilHttpService) { 
    this.message = new DisplayMessage();
  }

  ngOnInit(): void {
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
