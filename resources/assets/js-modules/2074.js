__d(function(n,l,t,a){function e(n){if(!n.length)return null;var l={sw:{},ne:{}};return n.forEach(function(n){var t=n.lat,a=n.lng;l.sw.lat=l.sw.lat?Math.min(l.sw.lat,t):t,l.sw.lng=l.sw.lng?Math.min(l.sw.lng,a):a,l.ne.lat=l.ne.lat?Math.max(l.ne.lat,t):t,l.ne.lng=l.ne.lng?Math.max(l.ne.lng,a):a}),l}function g(n){if(!n.length)return null;var l=e(n),t=l.ne.lat-l.sw.lat+.2,a=l.ne.lng-l.sw.lng+.2;return{latitude:l.sw.lat+(l.ne.lat-l.sw.lat)/2,longitude:l.sw.lng+(l.ne.lng-l.sw.lng)/2,latitudeDelta:t,longitudeDelta:a}}Object.defineProperty(a,"__esModule",{value:!0}),a.calcMapBoundsOfLocations=e,a.calcMapInitialRegion=g},2074);