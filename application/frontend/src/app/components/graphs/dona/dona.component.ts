import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MultiDataSet, Label, Color } from 'ng2-charts';

@Component({
  selector: 'app-dona',
  templateUrl: './dona.component.html',
  styleUrls: ['./dona.component.css']
})
export class DonaComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {  
  }

  @Input() title: string = 'DONA';
  @Input('labels') doughnutChartLabels: Label[] = ['Label1', 'Label2', 'Label2'];
  @Input('data') doughnutChartData: MultiDataSet = [[350, 450, 100]];
  @Input('colors') colors: Color[] = [{backgroundColor: [ '#6857E6','#009FEE','#F02059' ]}];

}
