__d(function(r,t,e,n){function i(r){var t=r.match(/\/api\/v\d\/?$/)||[],e=l(t,1),n=e[0];return n?r.slice(0,r.length-n.length):r}function a(r,t){var e="_size="+String(t),n=r.includes("?")?"&":"?";return""+String(r)+n+e}function o(r,t){var e=""+String(i(r))+String(t);return{imageUrl:e,thumbnailUrl:e,smallImageURL:a(e,"small"),mediumImageURL:a(e,"medium"),largeImageURL:a(e,"large"),originalImageURL:a(e,"original")}}Object.defineProperty(n,"__esModule",{value:!0});var l=function(){function r(r,t){var e=[],n=!0,i=!1,a=void 0;try{for(var o,l=r["function"==typeof Symbol?Symbol.iterator:"@@iterator"]();!(n=(o=l.next()).done)&&(e.push(o.value),!t||e.length!==t);n=!0);}catch(r){i=!0,a=r}finally{try{!n&&l.return&&l.return()}finally{if(i)throw a}}return e}return function(t,e){if(Array.isArray(t))return t;if(("function"==typeof Symbol?Symbol.iterator:"@@iterator")in Object(t))return r(t,e);throw new TypeError("Invalid attempt to destructure non-iterable instance")}}();n.default=o},2339);