import { Component } from '@angular/core';
import {Hero} from './hero';

@Component({
    templateUrl: './task-1.html'
})
export class Task1Component {
    title: string;
    country: string;
    color: string;
    clickMessage='';
    values='';
    keyup_value = '';
    //  heroes = ['Windstorm', 'Bombasto', 'Magneta', 'Tornado'];
    onClickMe(){
      this.clickMessage="You click me !";
    };
    onKey(event:any){
      this.values+=event.target.value+'|';
    };

    peoples=['Windstorm', 'Bombasto', 'Magneta', 'Tornado'];
    addPeople(newPeople:string){
      if(newPeople){
        this.peoples.push(newPeople);
      }
    };



    // use class
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
