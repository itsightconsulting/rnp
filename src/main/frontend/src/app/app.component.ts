import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

import { filter } from 'rxjs/operators';

declare var gtag:Function;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    constructor(
        private router: Router
    ) {
        const navEndEvents$ = this.router.events
            .pipe(
                filter(event => event instanceof NavigationEnd)
            );

        navEndEvents$.subscribe((event: NavigationEnd) => {
            gtag('config', 'UA-138465622-1', {
                'page_path': event.urlAfterRedirects
            });
        });
    }
}
