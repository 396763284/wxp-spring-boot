import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

if (process.env.ENV === 'production') {
    enableProdMode();
}
//渲染组件
platformBrowserDynamic().bootstrapModule(AppModule);
