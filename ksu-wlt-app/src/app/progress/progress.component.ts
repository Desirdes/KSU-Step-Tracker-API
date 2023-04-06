import { Component, OnInit } from '@angular/core';
import { Chart, ChartDataset, ChartOptions } from 'chart.js';
import { NgChartsModule } from 'ng2-charts/public_api';

@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.css']
})

export class ProgressComponent implements OnInit{
  ngOnInit(): void {
    this.updateProgressBar();
    this.createBarChart();
  }

  public updateProgressBar() {

    var progressBar = document.getElementById('circular-progress-bar');
    var value = +progressBar.getAttribute('data-value');
    var left = document.getElementById('left-progress-bar');
    var right = document.getElementById('right-progress-bar');
    
    if (value > 0) {
      if (value <= 50) {
        right.style.setProperty('transform', 'rotate(' + this.percentageToDegrees(value) + 'deg)');
      } else {
        right.style.setProperty('transform', 'rotate(180deg)');
        left.style.setProperty('transform', 'rotate(' + this.percentageToDegrees(value - 50) + 'deg)');
      }
    }
    
    };

    public percentageToDegrees(percentage) {
    
      return percentage / 100 * 360
    
    }
  
  //Chart.JS stuff
  public barChart: any; 

  //Line Chart
 createBarChart(){ 
  const labels = ['2023-04-02', '2023-04-03', '2023-04-04', '2023-04-05',
   '2023-04-06', '2023-04-07', '2023-04-08'];
  const datapoints = [100, 200, 300, 400, 500, 600, 700];
  const datapoints2 = [100, 200, 300, 400, 500, 600, 700];
  this.barChart = new Chart("MyBarChart", {
    type: 'bar', //this denotes tha type of chart
    
      data: {// values on X-Axis
        labels: labels,
	      datasets: [
          {
            label: "My Step Count",
            data: datapoints,
            backgroundColor: 'blue'
          },
          {
            label: "Target Step Count",
            data: datapoints2,
            backgroundColor: 'limegreen'
          }  
        ]
      },
      options: {
        aspectRatio:2.5
      }
    
  });
//filterData() function here
}
}
