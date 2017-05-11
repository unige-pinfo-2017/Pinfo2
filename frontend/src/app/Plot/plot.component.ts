import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { DataForPlotService } from '../_services';
import { ValueForPlot } from '../_models/value-for-plot';

@Component({
    selector: 'plot',
    templateUrl: 'plot.component.html',
    providers: [
        DataForPlotService
    ]
})

export class PlotComponent implements OnInit {
    errorMessage: any;
  values: ValueForPlot[];
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
  plot: PlotComponent;

  setValuesForPlot(): void{
      this.dataForPlotService.getValues().subscribe(values => {
                                                        this.values=values;
                                                        console.log("Depuis plot.component.ts setValuesForPlot");
                                                        console.log(this.values[0].value);
                                                        },
                                                    error => this.errorMessage = <any> error);
  }

  ngOnInit(): void {
      this.setValuesForPlot();
      console.log(this.values.length)
  }

  constructor(private dataForPlotService: DataForPlotService) {
  }

}
