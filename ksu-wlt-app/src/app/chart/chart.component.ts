import { Component, OnInit } from '@angular/core';
import { Chart, ChartDataset, ChartOptions } from 'chart.js';
import { NgChartsModule } from 'ng2-charts/public_api';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit{
 public lineChart: any;
 public donutChart: any;

 //Line Chart
 createLineChart(){
  
  this.lineChart = new Chart("MyChart", {
    type: 'line', //this denotes tha type of chart

    data: {// values on X-Axis
      labels: ['January', 'February', 'March','April',
               'May', 'June', 'July','August'], 
       datasets: [
        {
          label: "My Step Count",
          data: ['10000','21000', '572', '30000', '12345',
               '5145', '4321', '6185'],
          backgroundColor: 'red'
        },
        {
          label: "Target Step Count",
          data: ['10535', '11652', '8566', '100563', '500321',
                 '89045', '78906', '93468'],
          backgroundColor: 'lightblue'
        }  
      ]
    },
    options: {
      aspectRatio:2.5
    }
    
  });
}

//Donut Chart
createDonutChart(){
  
  this.lineChart = new Chart("MyDonutChart", {
    type: 'doughnut',
    data: {
      labels: [
        'Target Step Count',
        'Current Step Count'
      ],
      datasets: [{
        label: 'My First Dataset',
        data: [10756, 9465],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
        ],
        hoverOffset: 4
      }]
    }
  }
)}

ngOnInit(): void {
  this.createLineChart();
  this.createDonutChart();
}


}
