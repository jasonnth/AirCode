__d(function(e,t,r,i){"use strict";function a(e){return e.timeStamp||e.timestamp}function c(e){return{touchActive:!0,startPageX:e.pageX,startPageY:e.pageY,startTimeStamp:a(e),currentPageX:e.pageX,currentPageY:e.pageY,currentTimeStamp:a(e),previousPageX:e.pageX,previousPageY:e.pageY,previousTimeStamp:a(e)}}function u(e,t){e.touchActive=!0,e.startPageX=t.pageX,e.startPageY=t.pageY,e.startTimeStamp=a(t),e.currentPageX=t.pageX,e.currentPageY=t.pageY,e.currentTimeStamp=a(t),e.previousPageX=t.pageX,e.previousPageY=t.pageY,e.previousTimeStamp=a(t)}function n(e){var t=e.identifier;return p(null!=t,"Touch object is missing identifier."),h(t<=P,"Touch identifier %s is greater than maximum supported %s which causes performance issues backfilling array locations for all of the indices.",t,P),t}function o(e){var t=n(e),r=S[t];r?u(r,e):S[t]=c(e),X.mostRecentTimeStamp=a(e)}function s(e){var t=S[n(e)];t&&(t.touchActive=!0,t.previousPageX=t.currentPageX,t.previousPageY=t.currentPageY,t.previousTimeStamp=t.currentTimeStamp,t.currentPageX=e.pageX,t.currentPageY=e.pageY,t.currentTimeStamp=a(e),X.mostRecentTimeStamp=a(e))}function g(e){var t=S[n(e)];t&&(t.touchActive=!1,t.previousPageX=t.currentPageX,t.previousPageY=t.currentPageY,t.previousTimeStamp=t.currentTimeStamp,t.currentPageX=e.pageX,t.currentPageY=e.pageY,t.currentTimeStamp=a(e),X.mostRecentTimeStamp=a(e))}var m=t(130),p=t(18),h=t(22),v=m.isEndish,T=m.isMoveish,f=m.isStartish,P=20,S=[],X={touchBank:S,numberActiveTouches:0,indexOfSingleActiveTouch:-1,mostRecentTimeStamp:0},Y={recordTouchTrack:function(e,t){if(T(e))t.changedTouches.forEach(s);else if(f(e))t.changedTouches.forEach(o),X.numberActiveTouches=t.touches.length,1===X.numberActiveTouches&&(X.indexOfSingleActiveTouch=t.touches[0].identifier);else if(v(e)&&(t.changedTouches.forEach(g),X.numberActiveTouches=t.touches.length,1===X.numberActiveTouches))for(var r=0;r<S.length;r++){var i=S[r];if(null!=i&&i.touchActive){X.indexOfSingleActiveTouch=r;break}}},touchHistory:X};r.exports=Y},232);