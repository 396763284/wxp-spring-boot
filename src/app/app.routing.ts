import { Routes, RouterModule } from '@angular/router';
import { Task1Component } from './task-1/task1.component';
import { Task3Component } from './task-3/task3.component';

const routes: Routes = [

    { path: '', redirectTo: 'task1', pathMatch: 'full' },

    { path: 'task1', component: Task1Component },

    {
        path: 'task2', loadChildren: () =>
            new Promise(resolve =>
                (require as any).ensure([], () =>
                    resolve(require('./task-2/task2.module').Task2Module)
                )
            )
    },

    { path: 'task3', component: Task3Component }
];

export const routing = [
    RouterModule.forRoot(routes, { useHash: true })
];
