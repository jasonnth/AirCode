__d(function(e,r,t,n){"use strict";function u(e,r,t){var n=void 0,u=void 0,a=void 0,i=void 0;return t?(n=e.currentPageX,u=e.currentPageY,a=r.currentPageX,i=r.currentPageY):(n=e.previousPageX,u=e.previousPageY,a=r.previousPageX,i=r.previousPageY),Math.sqrt(Math.pow(n-a,2)+Math.pow(u-i,2))}function a(e,r){for(var t=0,n=0;n<e.length-1;n++)for(var a=n+1;a<e.length;a++){var i=u(e[n],e[a],r);i>t&&(t=i)}return t}function i(e,r,t){var n=e.touchBank;if(e.numberActiveTouches>1){return a(n.filter(function(e){return e&&e.currentTimeStamp>=r}),t)}}Object.defineProperty(n,"__esModule",{value:!0}),n.distance=u,n.maxDistance=a,n.pinchDistance=i},2368);