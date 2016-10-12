import { Component } from '@angular/core';
import {Hero} from './hero';
//
// @Component({
// // selector:"ez-app",
// // properties:["name","country"],
// template:`<div>aaaaa</div>`
//
// })


@Component({
    templateUrl: './task-1.html'
})
export class Task1Component {
    title: string;
    country: string;
    color: string;
    //  heroes = ['Windstorm', 'Bombasto', 'Magneta', 'Tornado'];
    heroes = [
        new Hero(1, 'Windstorm'),
        new Hero(13, 'Bombasto'),
        new Hero(15, 'Magneta'),
        new Hero(20, 'Tornado')
    ];

    constructor() {



        this.title = 'Tour of Heroes';
        this.country = 'Windstorm';


        this.color = "red";
        setInterval(() => {
            this.color = '#' + (Math.random() * 0xffffff << 0).toString(16);
        }, 1000);
    }

}
