import { Component ,OnInit  } from '@angular/core';
import { HeroService } from './hero.service';
import { Hero } from './hero';

@Component({
    // selector:"task2",
    templateUrl: './heros.html',
    styleUrls: ['./heros.css'],
    providers: [HeroService]
})



export class HerosComponent implements OnInit {

    title = 'Tour of Heros';

    heroes: Hero[];
    selectedHero: Hero;
    constructor(private heroService: HeroService) { }

    getHeroes(): void {
        this.heroService.getHeroes().then(heroes => this.heroes = heroes);
    };
    ngOnInit(): void {
        this.getHeroes();
    };
    onSelect(hero: Hero): void {
        this.selectedHero = hero;
    };


}
