__d(function(e,n,t,l){function o(e){var n=arguments.length>1&&void 0!==arguments[1]&&arguments[1];babelHelpers.extends(H,e),u(),a(),(0,A.default)(H.systemAirbnbLocale||"en"),n||S.isLoggedIn()||(0,O.default)().dispatch((0,I.logout)())}function u(){d.default.init(babelHelpers.extends({},C.default,{locale:H.account.locale||H.systemAirbnbLocale||H.localeLanguage,country:H.localeCountry,tld_country:H.localeCountry}))}function a(){g.default.locale(H.account.locale||H.systemAirbnbLocale||H.localeLanguage)}function r(e){T.emitter.addListener("airbnb.accountUpdated",e)}Object.defineProperty(l,"__esModule",{value:!0}),l.onAccountUpdated=r;var c=n(44),i=n(42),s=babelHelpers.interopRequireDefault(i),f=n(377),d=babelHelpers.interopRequireDefault(f),b=n(379),g=babelHelpers.interopRequireDefault(b),p=n(410),L=babelHelpers.interopRequireDefault(p),y=n(561),A=babelHelpers.interopRequireDefault(y),m=n(648),C=babelHelpers.interopRequireDefault(m),I=n(649),R=n(651),O=babelHelpers.interopRequireDefault(R),T=s.default.module({moduleName:"AccountBridge",isEventEmitter:!0,mock:{initialAccount:null,localeLanguage:null,localeCountry:null,initialCurrencies:null,presentIdentityFlow:function(){return Promise.resolve(!0)},logout:function(){}}}),_={BOOK:"Book",CONTACT_HOST:"ContactHost",LYS:"LYS",REGISTRATION:"Registration",MAGICAL_TRIPS_BOOKING:"MagicalTripsBooking",MAGICAL_TRIP_GUEST:"MagicalTripsGuest"},H={account:{},currencies:[],localeLanguage:null,localeCountry:null,systemAirbnbLocale:null};r(o),o({account:T.initialAccount||H.account,localeLanguage:T.localeLanguage,localeCountry:T.localeCountry,systemAirbnbLocale:T.systemAirbnbLocale},!0);var S={VERIFICATION_FLOW:_,isLoggedIn:function(){return null!=H.account.user},current:function(){return H.account},user:function(){return H.account.user},locale:function(){return H.account.locale},localeLanguage:function(){return H.localeLanguage||"en"},localeCountry:function(){return H.localeCountry||"US"},presentIdentityFlow:function(e){return c.Platform.select({ios:function(){return T.presentIdentityFlow(e)},android:function(){return L.default.push("Verifications",{verificationFlow:e}).then(function(e){return e.code===p.RESULT_OK})}})()},logout:function(){"android"===c.Platform.OS&&T.logout()}};l.default=S},376);