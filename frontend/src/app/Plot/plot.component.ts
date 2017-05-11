import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { DataForPlotService } from '../_services';


@Component({
    selector: 'plot',
    templateUrl: 'plot.component.html',
    providers: [
        DataForPlotService
    ]
})

export class PlotComponent implements OnInit {
    errorMessage : any;
    
    // lineChart
    lineChartData:Array<any> /*= [
        {
            data: [65, 59, 80, 81, 56, 55, 40],
            label: 'Series A'
        },    
    ]*/
    lineChartLabels:Array<any> /*= [
            'January', 'February', 'March', 'April', 'May', 'June', 'July'
        ];*/
    lineChartOptions:any = {
        responsive: true
    };
    lineChartColors:Array<any> = [
        { // dark grey
            backgroundColor: 'rgba(77,83,96,0.2)',
            borderColor: 'rgba(77,83,96,1)',
            pointBackgroundColor: 'rgba(77,83,96,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(77,83,96,1)'
        }
    ];
    lineChartLegend:boolean = true;
    lineChartType:string = 'line';

    constructor(private dataForPlotService: DataForPlotService) {
    }

    ngOnInit(): void {
        this.setValuesForPlot();
    }

    setValuesForPlot(): void{
        this.dataForPlotService.getValues().subscribe(Values => {
                                                            this.lineChartData=Values.lineChartData;
                                                            this.lineChartLabels=Values.lineChartLabels;
                                                        },
                                                        error => this.errorMessage = <any> error);
    }
    
    public randomize():void {
        let _lineChartData:Array<any> = new Array(this.lineChartData.length);
        for (let i = 0; i < this.lineChartData.length; i++) {
            _lineChartData[i] = {data: new Array(this.lineChartData[i].data.length), label: this.lineChartData[i].label};
            for (let j = 0; j < this.lineChartData[i].data.length; j++) {
            _lineChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
            }
        }
        this.lineChartData = _lineChartData;
    }
    
    // events
    public chartClicked(e:any):void {
        console.log(e);
    }
    
    public chartHovered(e:any):void {
        console.log(e);
    }
    
}