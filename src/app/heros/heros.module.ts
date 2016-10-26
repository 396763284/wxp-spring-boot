import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HerosComponent } from './heros.component';
import { HeroDetailComponent } from './hero-detail.component';
import { FormsModule }   from '@angular/forms';

@NgModule({
    imports: [CommonModule,FormsModule ],
    declarations: [HerosComponent,HeroDetailComponent],
    exports: [HerosComponent]
})
export class HerosModule {}
