import { Component, OnInit } from '@angular/core';
import { DataForPlotService } from '../_services'

@Component({
    selector: 'plot',
    templateUrl: 'plot.component.html',
    providers: [
        DataForPlotService
    ]
})

export class PlotComponent implements OnInit {
  values :any[]

  view: any[] = [700, 400];

  // options
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'x';
  showYAxisLabel = true;
  yAxisLabel = 'y';

  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };

  // line, area
  autoScale = true;
  
  setValuesForPlot(): void{
      this.dataForPlotService.getValues().then(values => this.values=values)
  }

  ngOnInit(): void {
      this.setValuesForPlot();
  }

  constructor(private dataForPlotService: DataForPlotService) {
  }


}