'use strict';


customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">gott-pttk-app-front documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-toggle="collapse" ${ isNormalMode ?
                                'data-target="#modules-links"' : 'data-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link">AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-8b6001ffb60c31090a2cbbaee8d39021"' : 'data-target="#xs-components-links-module-AppModule-8b6001ffb60c31090a2cbbaee8d39021"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-8b6001ffb60c31090a2cbbaee8d39021"' :
                                            'id="xs-components-links-module-AppModule-8b6001ffb60c31090a2cbbaee8d39021"' }>
                                            <li class="link">
                                                <a href="components/AddRouteDialogComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">AddRouteDialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AddTripComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">AddTripComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AppComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/BackBarComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">BackBarComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ChoiceDialogComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ChoiceDialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DashboardComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">DashboardComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/EditTripComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">EditTripComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LoadingSpinnerComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">LoadingSpinnerComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ManageBadgesComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ManageBadgesComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ManageTripsComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ManageTripsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/NavBarComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">NavBarComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SendTripsForVerificationComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">SendTripsForVerificationComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SimpleErrorDialogComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">SimpleErrorDialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TableDialogComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">TableDialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/VerifyTripDetailsComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">VerifyTripDetailsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/VerifyTripsMainComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">VerifyTripsMainComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/WorkInProgressComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">WorkInProgressComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/YesNoDialogComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">YesNoDialogComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link">AppRoutingModule</a>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#classes-links"' :
                            'data-target="#xs-classes-links"' }>
                            <span class="icon ion-ios-paper"></span>
                            <span>Classes</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"' }>
                            <li class="link">
                                <a href="classes/AppPage.html" data-type="entity-link">AppPage</a>
                            </li>
                            <li class="link">
                                <a href="classes/Badge.html" data-type="entity-link">Badge</a>
                            </li>
                            <li class="link">
                                <a href="classes/MatPaginatorIntlPol.html" data-type="entity-link">MatPaginatorIntlPol</a>
                            </li>
                            <li class="link">
                                <a href="classes/MountainGroup.html" data-type="entity-link">MountainGroup</a>
                            </li>
                            <li class="link">
                                <a href="classes/MountainSubgroup.html" data-type="entity-link">MountainSubgroup</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseBadge.html" data-type="entity-link">ResponseBadge</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseBadgeInfo.html" data-type="entity-link">ResponseBadgeInfo</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseMountainGroup.html" data-type="entity-link">ResponseMountainGroup</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseMountainSubgroup.html" data-type="entity-link">ResponseMountainSubgroup</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseRoute.html" data-type="entity-link">ResponseRoute</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseTrip.html" data-type="entity-link">ResponseTrip</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseTripRoute.html" data-type="entity-link">ResponseTripRoute</a>
                            </li>
                            <li class="link">
                                <a href="classes/ResponseUser.html" data-type="entity-link">ResponseUser</a>
                            </li>
                            <li class="link">
                                <a href="classes/Route.html" data-type="entity-link">Route</a>
                            </li>
                            <li class="link">
                                <a href="classes/SendVerifyTrips.html" data-type="entity-link">SendVerifyTrips</a>
                            </li>
                            <li class="link">
                                <a href="classes/Trip.html" data-type="entity-link">Trip</a>
                            </li>
                            <li class="link">
                                <a href="classes/TripRoute.html" data-type="entity-link">TripRoute</a>
                            </li>
                            <li class="link">
                                <a href="classes/User.html" data-type="entity-link">User</a>
                            </li>
                            <li class="link">
                                <a href="classes/VerifyTrip.html" data-type="entity-link">VerifyTrip</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#injectables-links"' :
                                'data-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/BadgeInfoService.html" data-type="entity-link">BadgeInfoService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/BadgeService.html" data-type="entity-link">BadgeService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/MountainGroupService.html" data-type="entity-link">MountainGroupService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/MountainSubgroupService.html" data-type="entity-link">MountainSubgroupService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/RouteService.html" data-type="entity-link">RouteService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/TripRouteService.html" data-type="entity-link">TripRouteService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/TripService.html" data-type="entity-link">TripService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UserService.html" data-type="entity-link">UserService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#miscellaneous-links"'
                            : 'data-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});