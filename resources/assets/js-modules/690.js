__d(function(n,o,e,t){"use strict";function u(){if(null===y){var o=n.document&&n.document.body&&n.document.body.className;y=/\bdevelopment\b/.test(String(o))}return y}function i(){return!u()}function c(){return!1}function l(){return!1}function r(){return!1}function s(){return null===m&&(m="undefined"!=typeof document),m}function d(){return!s()}function f(){return!1}function a(){"undefined"!=typeof console&&console.log&&Function.apply.call(console.log,console,arguments)}function p(){"undefined"!=typeof console&&console.warn&&Function.apply.call(console.warn,console,arguments)}Object.defineProperty(t,"__esModule",{value:!0}),t.isDev=u,t.isProd=i,t.isAdmin=c,t.isIOS=l,t.isAndroid=r,t.isBrowser=s,t.isMystique=d,t.isTest=f,t.log=a,t.warn=p;var y=null,m=null},690);