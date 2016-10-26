import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { Task1Module } from './task-1/task1.module';
import { Task3Module } from './task-3/task3.module';
import { HerosModule } from './heros/heros.module';

@NgModule({
    imports: [
        BrowserModule,
        Task1Module,
        Task3Module,
        HerosModule,
        routing
    ],
    declarations: [AppComponent],
    bootstrap: [AppComponent]
})
export class AppModule { }
